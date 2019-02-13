package commons;

import java.util.Date;
import java.util.UUID;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		final JedisPool pool;
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置池配置项值
		config.setMaxTotal(1024);
		config.setMaxIdle(200);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(false);
		config.setTestOnReturn(false);
		// 根据配置实例化jedis池
		pool = new JedisPool(config, "127.0.0.1", 6379);
		System.out.println("Connection to server sucessfully");
		final String lockName = UUID.randomUUID().toString();
		// 查看服务是否运行
		for (int i = 0; i < 6; i++) {
			Thread threadA = new Thread(new Runnable() {

				public void run() {
					Jedis jedis = pool.getResource();
					String name = Thread.currentThread().getName();
					if (acquireSemaphore(jedis, lockName, name, 5, 1000)) {
						System.out.println(name + "  执行任务");
						try {
							Thread.sleep(100L);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(name + "  释放信号量");
						releaseSemaphore(jedis, lockName, name);
					} else {
						System.out.println(name + " 获取信号量失败");
					}
				}
			});

			threadA.start();
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取信号量
	 * 
	 * @author: mmj
	 * @param lockName
	 * @param uid
	 * @param limit
	 * @param timeOut
	 * @return
	 */
	public static boolean acquireSemaphore(Jedis jedis, String lockName, String uid, long limit, long timeOut) {
		try {
			long val = jedis.incr("incr"+lockName);
			long start = System.currentTimeMillis();
			
			while(val != 1) {
				val = jedis.incr("incr"+lockName);
				if (System.currentTimeMillis() - start > 1000) {
					System.out.println(uid + "获取锁超时");
				}
			}
			
			System.out.println(uid + "当前活跃数：" + jedis.zcard(lockName));
			// 将过期的信号量清除掉
			jedis.zremrangeByScore(lockName, 0, new Date().getTime() - timeOut);
			System.out.println(uid + "清除过期后的当前活跃数：" + jedis.zcard(lockName));
			// 将新的信号量插入
			jedis.zadd(lockName, new Date().getTime(), uid);
			// 判断是否超出限制
			if (jedis.zrank(lockName, uid) <= limit) {
				return true;
			}
			// 移除本次插入
			jedis.zrem(lockName, uid);
			System.out.println(uid + "解除后的当前活跃数：" + jedis.zcard(lockName));
			jedis.del("incr"+lockName);
			return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 释放信号量
	 * 
	 * @author: mmj
	 * @param lockName
	 * @param uid
	 */
	public static void releaseSemaphore(Jedis jedis, String lockName, String uid) {
		try {
			jedis.zrem(lockName, uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
