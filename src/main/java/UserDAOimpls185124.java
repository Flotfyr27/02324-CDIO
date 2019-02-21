import dal.IUserDAO;
import dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOimpls185124 implements IUserDAO {

    private Connection connection;

    UserDAOimpls185124 (){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185124?"
                    + "user=s185124&password=B1sEkOxqS0fTXLM2yJDCQ");
            System.out.println("Successfully connected to server");
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database");
            e.printStackTrace();
        }
    }


    @Override
    public UserDTO getUser(int userId) throws DALException {
        try {
            String query =
                    "SELECT * " +
                    "FROM cdio1_users " +
                    "WHERE userID = ?";
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setInt(1, userId);
            ResultSet resultSet = prepStatement.executeQuery();

            //UserDTO user = new UserDTO(resultSet.ro);

        } catch (SQLException e) {
            throw new DALException("An SQLException occurred", e);
        }
        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        try {
            Statement queryUser = connection.createStatement();
            ResultSet userSet = queryUser.executeQuery(
                        "SELECT *" +
                            "FROM cdio1_users " +
                            "JOIN cdio1_roles ON cdio1_users.userID = cdio1_roles.userID " +
                            "ORDER BY cdio1_users.userID;");


            List<UserDTO> userList = new ArrayList<>();
            while (userSet.next()) {
                List<Boolean> rolesList = new ArrayList<>();
                Boolean[] rolesArr;
                try {
                    for (int i = 7; true ; i++) {
                        rolesList.add(userSet.getBoolean(i));
                    }
                } catch (SQLException e) {
                    System.out.println("last column reached");
                }
                rolesArr = rolesList.toArray(new Boolean[1]);

                userList.add(new UserDTO(
                        userSet.getInt(1),
                        userSet.getString(2),
                        userSet.getString(3),
                        userSet.getInt(4),
                        userSet.getString(5),
                        rolesArr
                ));

            }

            return userList;
        } catch (SQLException e) {
            throw new DALException("An SQLException occurred", e);
        }
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        try {
            String query = "INSERT INTO cdio1_users" + " VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setInt(1, user.getUserId());
            prepStatement.setString(2, user.getUserName());
            prepStatement.setString(3, user.getIni());
            prepStatement.setInt(4, user.getCpr());
            prepStatement.setString(5, user.getPassword());
            prepStatement.execute();

            String rolesQuery = "INSERT INTO cdio1_roles" + " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement rolesStatement = connection.prepareStatement(rolesQuery);
            for (int i = 0; i < user.getRoles().length; i++) {
                rolesStatement.setBoolean(i + 2, user.getRoles()[i]); //+2 because of coversion between index 1/0 and because of first row being ignored
            }
            rolesStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {

    }

    @Override
    public void deleteUser(int userId) throws DALException {

    }
}
