import dal.IUserDAO;
import dto.ERoles;
import dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public class test {
    public static void main(String[] args) {
        try {
            UserDAOimpls185124 dao = new UserDAOimpls185124();
            UserDTO newUser = new UserDTO(3, "SadSmurf", "SS", 1353545430, "qwerty", ERoles.Operator.ordinal());
            dao.createUser(newUser);
            System.out.println("added new user");

            dao.deleteUser(1);
            System.out.println("deleted user 1");

            List<UserDTO> list = dao.getUserList();
            System.out.println("User list:");
            for (UserDTO user : list) {
                System.out.println(user);
            }

            newUser.setPassword("123456");
            dao.updateUser(newUser);
            System.out.println();
            System.out.println("Get user 3:");
            System.out.println(dao.getUser(3));
            System.out.println("Get user 2:");
            System.out.println(dao.getUser(2));
            System.out.println();

        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
    }
}
