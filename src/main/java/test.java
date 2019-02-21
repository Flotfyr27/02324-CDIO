import dal.IUserDAO;
import dto.ERoles;
import dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public class test {
    public static void main(String[] args) {
        try {
            UserDAOimpls185124 dao = new UserDAOimpls185124();

            //dao.createUser(new UserDTO(2, "LukeSkyWalker", "LS", 11245430, "abc123", ERoles.Admin.ordinal()));
            //System.out.println("added new user");

            List<UserDTO> list = dao.getUserList();
            System.out.println("User list:");
            for (UserDTO user : list) {
                System.out.println(user);
            }
            System.out.println();
            System.out.println("select user 1:");
            dao.getUser(1);
            System.out.println("get user 2");
            dao.getUser(2);
            System.out.println();

        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
    }
}
