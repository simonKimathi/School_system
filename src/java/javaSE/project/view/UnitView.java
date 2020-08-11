package javaSE.project.view;



import javaSE.project.logic.TeacherLogic;
import javaSE.project.logic.UnitViewLogic;
import javaSE.project.logic.UnitViewLogicI;
import javaSE.project.model.Teacher;
import javaSE.project.model.Unit;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UnitView implements UnitViewI {
    private Scanner scanner;
    private UnitViewLogicI UnitViewLogicI;

    public UnitView() throws SQLException, ClassNotFoundException {
        scanner = new Scanner(System.in);
        UnitViewLogicI = new UnitViewLogic();
    }

    //register new Unit
    private void register() throws SQLException, ClassNotFoundException {
        Unit Unit = new Unit();
        System.out.println("Enter unit id:");
        Unit.setId(scanner.nextInt());
        System.out.println("Enter name:");
        Unit.setName(scanner.nextLine());
        System.out.println("Enter Unit hours #:");
        Unit.setTime(scanner.nextDouble());
        scanner.nextLine();
        Teacher teacher;
        do {
            System.out.println("Enter Unit teacher staff Number:");
            teacher = new TeacherLogic().find(scanner.nextLine());
            if (teacher == null)
                System.err.println("Teacher not found");
            else Unit.setTeacher(teacher);
        } while (teacher != null);

        System.out.println("You are about to register the following Unit details:\n" + Unit.toString() + "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1)
            UnitViewLogicI.add(Unit);
    }

    //show registered Units
    private void show() throws SQLException, ClassNotFoundException {
        System.out.println("List of Units from the DB");
        List<Unit> Units = UnitViewLogicI.findAll();
        for (Unit Unit : Units) {
            System.out.println(Unit.toStringRow());
        }
    }

    /**/
    @Override
    public void menu() throws SQLException, ClassNotFoundException {
        int option;
        do {
            System.out.println("Welcome to Units Module. \n" +
                    "Please select an option: \n" +
                    "1. Register a Unit \n" +
                    "2. Edit a Unit \n" +
                    "3. Delete a Unit \n" +
                    "4. Show list of Units \n" +
                    "0. Back to main menu \n");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    this.register();
                    break;
                case 2:
                    // edit();
                    this.update();
                    break;
                case 3:
                    // delete();
                    this.delete();
                    break;
                case 4:
                    this.show();
                    break;
                case 0:
                    break;
            }
        } while (option != 0);
        UnitViewLogicI = null;
    }

    //update Units info
    private void update() throws SQLException, ClassNotFoundException {
        System.out.println("Update Units info");
        System.out.println("Enter Unit ID #:");
        int id = scanner.nextInt();
        Unit Unit = UnitViewLogicI.find(id);
        if (Unit == null) {
            System.out.println("Unit not registered");
            return;
        }
        System.out.println("Enter new id:");
        Unit.setId(scanner.nextInt());
        System.out.println("Enter Unit hours #:");
        Unit.setTime(scanner.nextDouble());
        scanner.nextLine();
        Teacher teacher;
        do {
            System.out.println("Enter Unit teacher staff Number:");
            teacher = new TeacherLogic().find(scanner.nextLine());
            if (teacher == null)
                System.err.println("Teacher not found");
            else Unit.setTeacher(teacher);
        } while (teacher != null);
        System.out.println("You are about to update the following Units' details:\n" + Unit.toString() + "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1)
            UnitViewLogicI.update(Unit);
    }

    //Remove Unit from DB
    private void delete() throws SQLException, ClassNotFoundException {
        System.out.println("Delete Unit and all related information");
        System.out.println("Enter Unit ID #:");
        int id = scanner.nextInt();
        Unit Unit = UnitViewLogicI.find(id);
        if (Unit == null) {
            System.out.println("Unit not registered");
            return;
        }
        System.out.println("You are about to remove the following Units' details:\n" + Unit.toStringRow() + "\n. Action cannot be reversed.Continue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1)
            UnitViewLogicI.delete(Unit);
    }
}
