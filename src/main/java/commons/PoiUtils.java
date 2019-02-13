package commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiUtils {
	
	public static void main(String[] args) {
		String path = "E:\\software\\date.xlsx";
		File file = new File(path);
		try {
			InputStream in = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(in);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(0);
			Cell cell0 = row.getCell(0);
			Cell cell1 = row.getCell(1);
			Cell cell2 = row.getCell(2);
			Cell cell3 = row.getCell(3);
			Cell cell4 = row.getCell(4);
			System.out.println(cell0.getCellStyle().getDataFormat());
			System.out.println(cell1.getCellStyle().getDataFormat());
			System.out.println(cell2.getCellStyle().getDataFormat());
			System.out.println(cell3.getCellStyle().getDataFormat());
//			System.out.println(cell0.getCellType());
//			System.out.println(cell1.getCellType());
//			System.out.println(cell2.getCellType());
//			System.out.println(cell3.getCellType());
//			System.out.println(cell4 == null ? null : cell4.getCellType());
			
//			System.out.println(cell0.getNumericCellValue());
//			System.out.println(cell1.getDateCellValue());
//			System.out.println(cell3.getNumericCellValue());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
