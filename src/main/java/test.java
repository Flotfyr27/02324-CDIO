import dal.IUserDAO;
import dto.ERoles;
import dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public class test {
    public static void main(String[] args) {
        try {
            UserDAOimpls185124 dao = new UserDAOimpls185124();

            dao.createUser(new UserDTO(1, "Mads1606", "MMJ", 1606970000, "abc123", ERoles.Admin.ordinal()));
            System.out.println("added new user");

            List<UserDTO> list = dao.getUserList();
            System.out.println("User list:");
            for (UserDTO user : list) {
                System.out.println(user);
            }

        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
    }
}
