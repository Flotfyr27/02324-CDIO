package UI.TUI;
import TechnicalService.dto.ERoles;
import TechnicalService.dto.UserDTO;
import dataLayer.UserDAOimpls185124;
import dataLayer.dal.IUserDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TUI {
    Scanner scan = new Scanner(System.in);
    IUserDAO userDAOimpl = new UserDAOimpls185124();




    public void runCase() throws IUserDAO.DALException, SQLException {
        while(true) {
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
                    List<UserDTO> list = userDAOimpl.getUserList();
                    System.out.println("User list:");
                    for (UserDTO user : list) {
                        System.out.println(user);
                    }
                    break;

                case 2:
                    System.out.println("Choose the role of the user");
                    System.out.println("Type 0 for return to main ");
                    System.out.println("Type 1 for the role Admin");
                    System.out.println("Type 2 for the role Operator");
                    System.out.println("Type 3 for the role Foreman");
                    System.out.println("Type 4 for the role Pharmacist");

                        int create = scan.nextInt();
                        switch (create) {

                            case 0:
                            break;

                            case 1:
                                System.out.println("To create a new user enter the  the following separated by space: \nID, Name, Ini, Cpr, and Password");
                                UserDTO userDTO1 = new UserDTO(scan.nextInt(), scan.next(), scan.next(), scan.nextInt(), scan.next(), ERoles.Admin);
                                userDAOimpl.createUser(userDTO1);
                            break;
                            case 2:
                                System.out.println("Type the following separated by space : \nID, Name, Ini, CPR and Password");
                                UserDTO userDTO2 = new UserDTO(scan.nextInt(), scan.next(), scan.next(), scan.nextInt(), scan.next(), ERoles.Operator);
                                userDAOimpl.createUser(userDTO2);
                            break;
                            case 3:
                                System.out.println("Type the following separated by space : \nID, Name, Ini, CPR and  Password");
                                UserDTO userDTO3 = new UserDTO(scan.nextInt(), scan.next(), scan.next(), scan.nextInt(), scan.next(), ERoles.Foreman);
                                userDAOimpl.createUser(userDTO3);
                            break;
                            case 4:
                                System.out.println("Type the following separated by space : \nID, Name, Ini, CPR, and Password");
                                UserDTO userDTO4 = new UserDTO(scan.nextInt(), scan.next(), scan.next(), scan.nextInt(), scan.next(), ERoles.Pharmacist);
                                userDAOimpl.createUser(userDTO4);
                            break;
                            default:
                                System.out.println("Not a valid number");
                                break;
                        }

                   case 3: //TODO finish update

                   UserDTO userDTO = new UserDTO(scan.nextInt(), scan.next(), scan.next(), scan.nextInt(), scan.next(), ERoles.Pharmacist);
                    userDAOimpl.updateUser(userDTO);
                    break;

                    case 4:
                        System.out.println("Enter the ID of the user");
                        userDAOimpl.getUser(scan.nextInt());
                    break;

                case 5:
                    System.out.println("Type the ID you want to delete");
                   userDAOimpl.deleteUser(scan.nextInt());
                    break;

                default:
                    System.out.println("Please enter a valid number");




            }
        }
    }



    }


