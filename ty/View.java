package com.ty;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class View {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Controller d = new Controller();
		System.out.println("welcome to Student app");
		do {
			System.out.println("enter 1 to create student");
			System.out.println("enter 2 to update student");
			System.out.println("enter 3 to delete student");
			System.out.println("enter 4 to fetch student");
			System.out.println("enter 5 to  fetch all student");

			switch (sc.nextInt()) {
			case 1: {
				Student s = new Student();
				System.out.println("enter id , name, phone");
				s.setId(sc.nextInt());
				s.setName(sc.next());
				s.setPhone(sc.nextLong());
				d.saveStudent(s);
				System.out.println("saveds");
			}
				break;
			case 2: {
				System.out.println("enter id and new phone");
				d.updateStudentPhone(sc.nextInt(), sc.nextLong());
				System.out.println("updated");
			}
				break;
			case 3: {
				System.out.println("enter id to delete");
				d.deleteStudentById(sc.nextInt());
				System.out.println("deleted");
			}
				break;
			case 4: {
				System.out.println("enter id to fetch student");
				Student s = d.fetchStudentById(sc.nextInt());
				System.out.println(s.getId());
				System.out.println(s.getName());
				System.out.println(s.getPhone());
			}
				break;
			case 5: {
				List<Student> li = d.fetchAll();
				for (Student s : li) {
					System.out.println(s.getId());
					System.out.println(s.getName());
					System.out.println(s.getPhone());
					System.out.println("-------------");
				}
			}
				break;

			default: {
				System.out.println("choice is wrong");
			}
			}
			System.out.println("enter y to continue");
		} while ("Y".equalsIgnoreCase(sc.next()));

	}
}
