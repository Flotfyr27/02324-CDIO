package UI.TUI;
import Domain.MainController;
import TechnicalService.dto.ERoles;
import TechnicalService.dto.UserDTO;
import dataLayer.dal.IUserDAO;

import javax.management.relation.RoleStatus;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TUI {
    Scanner scan = new Scanner(System.in);
    IUserDAO dataAccess = new MainController();


    public void runCase() throws IUserDAO.DALException, SQLException {
        while (true) {
            System.out.println("\n Choose auction");
            System.out.println("Press 0 to close to program");
            System.out.println("Press 1 to see the user list");
            System.out.println("Press 2 to add a user");
            System.out.println("Press 3 to update the user list");
            System.out.println("Press 4 to find a specific user");
            System.out.println("Press 5 to delete a user");

            switch (scan.nextInt()) {
                case 0:
                    System.out.println("See you next time");
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
                case 3: //TODO finish update
                    System.out.println("Choose the role of the person");
                    System.out.println("Type 0 for return to main ");
                    System.out.println("Type 1 for the role Admin");
                    System.out.println("Type 2 for the role Operator");
                    System.out.println("Type 3 for the role Foreman");
                    System.out.println("Type 4 for the role Pharmacist");
                    System.out.println("Type 5 if user has no role");
                    int update = scan.nextInt();
                    runUpdateUser(update);

                    break;

                case 4:
                    System.out.println("Enter the ID of the user");
                    System.out.println(dataAccess.getUser(scan.nextInt()));
                    break;

                case 5:
                    regretDelete();
                    break;
                default:
                    System.out.println("Please enter a valid number");


            }
        }
    }

    private void createUser() throws IUserDAO.DALException {
        System.out.println("To create a new user enter the  the following separated by space: \nID, Name, Initials, Cpr, and Password");
        UserDTO userDTO1 = new UserDTO(scan.nextInt(), scan.next(), scan.next(), scan.nextInt(), scan.next());

        System.out.println(
                "Enter the roles of the new user separated by spaces" +
                "\n\t0\tno roles / no more roles" +
                "\n\t1\t" + ERoles.Admin.name() +
                "\n\t2\t" + ERoles.Pharmacist.name() +
                "\n\t3\t" + ERoles.Foreman.name() +
                "\n\t4\t" + ERoles.Operator.name()
        );

        Scanner scan = new Scanner(System.in);
        int input;
        do {
            input = scan.nextInt();
            boolean[] roles = new boolean[4];
            switch (input) {
                case 1:
                    roles[ERoles.Admin.ordinal()] = true;
            }
        } while (input != 0);

        dataAccess.createUser(userDTO1);
        System.out.println("The user has been created");
    }

    private void runUpdateUser(int update) throws IUserDAO.DALException {
        if (update == 1) {
            System.out.println("To update the  user enter the  the following separated by space: \nID, Name, Ini, Cpr, and Password");
            UserDTO userDTO1 = new UserDTO(scan.nextInt(), scan.next(), scan.next(), scan.nextInt(), scan.next(), ERoles.Admin);
            dataAccess.updateUser(userDTO1);
            System.out.println("The user has been updated");
        } if (update == 2) {
            System.out.println("Type the following separated by space : \nID, Name, Ini, CPR and Password");
            UserDTO userDTO2 = new UserDTO(scan.nextInt(), scan.next(), scan.next(), scan.nextInt(), scan.next(), ERoles.Operator);
            dataAccess.updateUser(userDTO2);
            System.out.println("The user has been updated");
        } if (update == 3) {
            System.out.println("Type the following separated by space : \nID, Name, Ini, CPR and  Password");
            UserDTO userDTO3 = new UserDTO(scan.nextInt(), scan.next(), scan.next(), scan.nextInt(), scan.next(), ERoles.Foreman);
            dataAccess.updateUser(userDTO3);
            System.out.println("The user has been updated");
        } if (update == 4) {
            System.out.println("Type the following separated by space : \nID, Name, Ini, CPR, and Password");
            UserDTO userDTO4 = new UserDTO(scan.nextInt(), scan.next(), scan.next(), scan.nextInt(), scan.next(), ERoles.Pharmacist);
            dataAccess.updateUser(userDTO4);
            System.out.println("The user has been updated");
        }  if (update == 5) {
            System.out.println("Type the following separated by space : \nID, Name, Ini, CPR, and Password");
            UserDTO userDTO5 = new UserDTO(scan.nextInt(), scan.next(), scan.next(), scan.nextInt(), scan.next(), false);
            dataAccess.updateUser(userDTO5);
            System.out.println("The user has been updated");
        } else if (update<0 || update>5)
            System.out.println("Not a valid number");

    }
    private void regretDelete() throws IUserDAO.DALException {
        System.out.println("Type 1 if you sure you want to delete a user\nType 2 for returning to main menu ");
        int delete = scan.nextInt();
        if (delete == 1) {
            System.out.println("Enter the ID of the user you want to delete");
            dataAccess.deleteUser(scan.nextInt());
            System.out.println("The user has been deleted");
        }
        if (delete == 2) {

        }
    }

}


