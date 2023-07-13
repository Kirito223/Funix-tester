package basic.bai1;

import java.util.Scanner;

public class MyMainClass {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CalculateUtils calculateUtils = new CalculateUtils();
		System.out.println("Nhap x:");
		double x = Double.parseDouble(scanner.nextLine());
		System.out.println("Nhap y:");
		double y = Double.parseDouble(scanner.nextLine());
		System.out.println("Nhap hanh dong(cong, tru, nhan, chia):");
		String action = scanner.nextLine();
		switch (action.toUpperCase()) {
		case "CONG":
			System.out.println(calculateUtils.tinhCong(x, y));
			break;
		case "TRU":
			System.out.println(calculateUtils.tinhTru(x, y));
			break;
		case "NHAN":
			System.out.println(calculateUtils.tinhNhan(x, y));
			break;
		case "CHIA":
			System.out.println(calculateUtils.tinhChia(x, y));
			break;
		default:
			System.out.println("Gia tri action khong hop le");
			break;
		}
		scanner.close();
	}
}
