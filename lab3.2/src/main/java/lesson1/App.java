package lesson1;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class App {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Employee> employees = new ArrayList<Employee>();
		System.out.println("Nhap so nhan vien");
		int size =Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < size; i++) {
			System.out.println("Nhap ma nhan vien");
			String code = scanner.nextLine();

			System.out.println("Nhap ho ten");
			String name = scanner.nextLine();

			System.out.println("Nhap gioi tinh ");
			String sex = scanner.nextLine();

			System.out.println("Nhap nam sinh ");
			String birthday = scanner.nextLine();

			System.out.println("Nhap que quan");
			String hometown = scanner.nextLine();

			System.out.println("Nhap phong ban");
			String department = scanner.nextLine();

			System.out.println("Nhap luong");

			String salary = scanner.nextLine();
			Employee employee = new Employee(code, name, sex, hometown, department, Integer.parseInt(birthday),
					Double.parseDouble(salary));

			employees.add(employee);
		}
		writeToExcel(employees);
	}

	private static void writeToExcel(ArrayList<Employee> listEmpolyee) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("employees");
		Row titleRow = sheet.createRow(0);
		Cell cell = titleRow.createCell(0);
		cell.setCellValue("ID");
		cell = titleRow.createCell(1);
		cell.setCellValue("Ho va ten");
		cell = titleRow.createCell(2);
		cell.setCellValue("Gioi tinh");

		int indexRow = 1;
		for (int i = 0; i < listEmpolyee.size(); i++) {
			Row contentRow = sheet.createRow(indexRow);
			Cell codeCell = contentRow.createCell(0);
			codeCell.setCellValue(listEmpolyee.get(i).getMa());

			Cell nameCell = contentRow.createCell(1);
			nameCell.setCellValue(listEmpolyee.get(i).getHoTen());

			Cell birthdayCell = contentRow.createCell(2);
			birthdayCell.setCellValue(listEmpolyee.get(i).getGioiTinh());
			indexRow++;
		}

		try {

			FileOutputStream outputStream = new FileOutputStream(new File("D:/Funix/DSNhanVien.xlsx"));
			workbook.write(outputStream);
			outputStream.close();
			System.out.println("Write file success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
