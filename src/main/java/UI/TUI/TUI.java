package UI.TUI;

import TechnicalService.dto.ERoles;
import TechnicalService.dto.UserDTO;
import dataLayer.dal.IUserDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TUI {
    IUserDAO dataAccess;

    public TUI (IUserDAO dataAccess) {
        this.dataAccess = dataAccess;
    }


    public void runCase() throws IUserDAO.DALException, SQLException {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose action");
            System.out.println("\t0\tclose to program");
            System.out.println("\t1\tsee the user list");
            System.out.println("\t2\tadd a user");
            System.out.println("\t3\tupdate the user list");
            System.out.println("\t4\tfind a specific user");
            System.out.println("\t5\tdelete a user");

            switch (scan.nextInt()) {
                case 0:
                    System.out.println("See you next time");
                    scan.close();
                    System.exit(0);
                    break;

                case 1:
                    List<UserDTO> list = dataAccess.getUserList();
                    System.out.println("User list:");
                    for (UserDTO user : list) {
                        System.out.println(user);
                    }
                    break;

                case 2:
                    createUser();
                break;
                case 3:
                    updateUser();
                    break;

                case 4:
                    System.out.println("Enter the ID of the user");
                    System.out.println(dataAccess.getUser(scan.nextInt()));
                    break;

                case 5:
                    delete();
                    break;
                default:
                    System.out.println("Please enter a valid number");


            }
        }
    }

    private void createUser() {
        Scanner scanInt = new Scanner(System.in);
        Scanner scanStr = new Scanner(System.in);
        System.out.println("To create a new user enter the the following each on their own line: \nID, Name, Initials, Cpr, and Password");

        int id = scanInt.nextInt();
        String name = scanStr.next();
        String ini = scanStr.next();
        int cpr = scanInt.nextInt();
        String password = scanStr.next();

        UserDTO userDTO1 = new UserDTO(id, name, ini, cpr, password);

        System.out.println(
                "Enter the roles of the new user separated by spaces" +
                "\n\t0\tno roles / no more roles" +
                "\n\t1\t" + ERoles.Admin.name() +
                "\n\t2\t" + ERoles.Pharmacist.name() +
                "\n\t3\t" + ERoles.Foreman.name() +
                "\n\t4\t" + ERoles.Operator.name()
        );

        boolean[] roles = new boolean[4];
        fillRolesArray(scanInt, roles);
        userDTO1.setRoles(roles);

        try {
            dataAccess.createUser(userDTO1);
            System.out.println("The user has been created");
        } catch (IUserDAO.DALException e) {
            System.out.println(e.getMessage());
            System.out.println("Try again");
        }

    }

    private void updateUser() throws IUserDAO.DALException {
        Scanner scanInt = new Scanner(System.in);
        Scanner scanStr = new Scanner(System.in);

        System.out.print("Enter the ID of the user you wish to update");
        UserDTO userUpdate = new UserDTO(scanInt.nextInt(), null, null, -1, null); //creates a user containing no values to be updated yet

        System.out.println("Enter the things you want to update followed by the new value" +
                "\n\t0\tCommit changes" +
                "\n\t1\tName" +
                "\n\t2\tInitials" +
                "\n\t3\tcpr" +
                "\n\t4\tPassword" +
                "\n\t5\tRoles");

        int selection;
        do {
            selection = scanInt.nextInt();

            switch (selection) {
                case 0:
                    break;
                case 1:
                    userUpdate.setUserName(scanStr.next());
                    break;
                case 2:
                    userUpdate.setIni(scanStr.next());
                    break;
                case 3:
                    userUpdate.setCpr(scanInt.nextInt());
                    break;
                case 4:
                    userUpdate.setPassword(scanStr.next());
                    break;
                case 5:
                    System.out.println("Enter all roles the user should have (also the ones they already have)" +
                            "\n\t0\tEnd list of roles" +
                            "\n\t1\t" + ERoles.Admin.name() +
                            "\n\t2\t" + ERoles.Pharmacist.name() +
                            "\n\t3\t" + ERoles.Foreman.name() +
                            "\n\t4\t" + ERoles.Operator.name()
                    );
                    boolean[] roles = new boolean[4];
                    fillRolesArray(scanInt, roles);
                    userUpdate.setRoles(roles);
                    break;
                default:
                    System.out.println("Please enter a valid option");

            }
        } while (selection != 0);

        dataAccess.updateUser(userUpdate);
        System.out.println("The user has been updated");

    }

    private void fillRolesArray(Scanner scanInt, boolean[] roles) {
        int input;
        do {
            input = scanInt.nextInt();
            switch (input) {
                case 0:
                    break;
                case 1:
                    roles[ERoles.Admin.ordinal()] = true;
                    break;
                case 2:
                    roles[ERoles.Pharmacist.ordinal()] = true;
                    break;
                case 3:
                    roles[ERoles.Foreman.ordinal()] = true;
                    break;
                case 4:
                    roles[ERoles.Operator.ordinal()] = true;
                    break;
                default:
                    System.out.println("Please enter a valid option");

            }
        } while (input != 0);
    }

    private void delete() throws IUserDAO.DALException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the ID of the user you want to delete");
        int selectedUser = scan.nextInt();

        System.out.println("Are you sure you want to delete the user:\n" +
                dataAccess.getUser(selectedUser));
        System.out.println("Delete user?" +
                "\n\t1\tDelete" +
                "\n\t2\tCancel");
        int selection;
        do {
            selection = scan.nextInt();
            switch (selection) {
                case 1:
                    dataAccess.deleteUser(selectedUser);
                    System.out.println("The user has been deleted");
                    break;
                case 2:
                    System.out.println("Deletion canceled");
                    break;
                default:
                    System.out.println("Please enter a valid option");
            }
        } while (selection != 1 && selection != 2);

    }

}


