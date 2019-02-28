import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
import dataLayer.dal.IUserDAO;
import dataLayer.UserDAOimpls185124;
import TechnicalService.dto.ERoles;
import TechnicalService.dto.UserDTO;

import java.util.List;

public class test {
    public static void main(String[] args) {
        /*try {
            UserDAOimpls185124 dao = new UserDAOimpls185124();

            List<UserDTO> list = dao.getUserList();
            System.out.println("User list:");
            for (UserDTO user : list) {
                System.out.println(user);
            }

        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }*/

        if ("tlmm9ll".matches("(0123456789)")) { //[.-_+!?=]
            System.out.println("true");
        } else
            System.out.println("false");
    }
}
