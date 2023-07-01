package basic.bai3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class MainScreen {

	public static void main(String[] args) {
		ArrayList<Student> students = new ArrayList<Student>();
		Scanner scanner = new Scanner(System.in);
		float totalScore = 0;
		String continues = "Y";
		while (continues.equals("N")== false) {
			Student student = new Student();
			student.inputInfomation(scanner);
			students.add(student);
			System.out.println("Do you want to continue (Y/N)?");
			continues = scanner.nextLine().toUpperCase().toString();
		}
		
		for (Student student : students) {
			student.showInfomation();
			totalScore+= student.getFinalGrade();
		}
		System.out.println("Xe diem hoc luc");
		for (Student student : students) {
			student.showFinalGrade();
		}
		
		System.out.println("Tong diem trung binh :"+ totalScore/ students.size());
	}

}
