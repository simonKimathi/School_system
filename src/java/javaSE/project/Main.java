package javaSE.project;

import javaSE.project.view.StudentView;
import javaSE.project.view.TeacherView;
import javaSE.project.view.UnitView;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int option;
        StudentView studentView = new StudentView();
        TeacherView teacherView = new TeacherView();
        UnitView uitView = new UnitView();
        do {
            System.out.println("Welcome to School System:\n Please select an option:\n1. Manage Students\n2. Manage Teachers\n2. Manage units\n4. Exit");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1:
                    studentView.menu();
                    break;
                case 2:
                    teacherView.menu();
                    break;
                case 3:

            }
        } while(option != 3);
        studentView = null;
    }
}
