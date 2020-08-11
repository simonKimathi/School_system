package javaSE.project.view;

import javaSE.project.logic.StudentLogic;
import javaSE.project.logic.StudentLogicI;
import javaSE.project.logic.TeacherLogic;
import javaSE.project.logic.TeacherLogicI;
import javaSE.project.model.Student;
import javaSE.project.model.Teacher;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TeacherView implements TeacherViewI {
    Scanner scanner;
    TeacherLogicI Teacherlogici;
    public TeacherView() throws SQLException, ClassNotFoundException  {
        scanner = new Scanner(System.in);
        Teacherlogici = new TeacherLogic();
    }

    private void register() throws SQLException{
        Teacher teacher = new Teacher();
        System.out.println("Enter name:");
        teacher.setName(scanner.nextLine());
        System.out.println("Enter id #:");
        teacher.setId(scanner.nextLong());
        System.out.println("Enter course:");
        teacher.setCourse(scanner.nextLine());
        System.out.println("Enter StaffNo #:");
        teacher.setStaffNo(scanner.nextLine());
        System.out.println("You are about to register the following details:\n" + teacher.toStringRow() + "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice == 1) {
            Teacherlogici.add(teacher);
            System.out.println("student added successfully");
        }
    }

    private void show() throws SQLException{
        System.out.println("List of students from the DB");
        List<Teacher>teacher= Teacherlogici.findAll();
        Iterator iterator = teacher.iterator();
        while(iterator.hasNext()){
            System.out.println(((Teacher) iterator.next()).toStringRow());
        }
    }
    private void delete() throws SQLException {
        Teacher teacher = new Teacher();
        System.out.println("enter the id number of teacher you want to delete");
        //print list of teachers for reference
        show();
        teacher.setId(scanner.nextLong());
        System.out.println("You are about to delete the following student? \n");
        //print details of the student you want to delete
        Teacherlogici.find(teacher.getId());
        System.out.println(teacher.toStringRow()+"\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice == 1) {
            Teacherlogici.delete(teacher);
            System.out.println("Teacher deleted successfully");
        }

    }
    private void edit() throws SQLException {
        Teacher teacher = new Teacher();
        System.out.println("enter the registration number of student of student you want to edit");
        //print list of students for reference
        show();
        teacher.setId(scanner.nextLong());
        System.out.println("You are about to edit the following student? \n");
        //print details of the Teacher you want to delete
        Teacherlogici.find(teacher.getId());
        System.out.println(teacher.toStringRow()+"\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice == 1) {
            Teacherlogici.update(teacher);
            System.out.println("student updated successfully");
        }

    }

    @Override
    public void menu() throws SQLException {
        int option;
        do {
            System.out.println("Welcome to Teachers Module. \n" +
                    "Please select an option: \n" +
                    "1. Register a teacher \n" +
                    "2. Edit a teacher \n" +
                    "3. Delete a teacher \n" +
                    "4. Show list of teacher \n" +
                    "0. Back to main menu \n");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1:
                    this.register();
                    break;
                case 2:
                    edit();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    this.show();
                    break;
                case 0:
                    break;
            }
        } while(option != 0);
        Teacherlogici = null;
    }



}

