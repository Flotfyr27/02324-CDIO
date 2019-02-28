package Domain;

import TechnicalService.dto.UserDTO;
import dataLayer.UserDAOimpls185124;
import dataLayer.dal.IUserDAO;

import java.util.List;

public class MainController implements IUserDAO {
    private IUserDAO dataAcces = new UserDAOimpls185124();


    //-- Methods with no Checks --//
    @Override
    public UserDTO getUser(int userId) throws DALException {
        return dataAcces.getUser(userId);
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return dataAcces.getUserList();
    }


    //-- Methods with extra Checks --//
    @Override
    public void createUser(UserDTO user) throws DALException {
        if ( checkPassword( user.getPassword() ) )
            throw new DALException("Password doesn't live up to security standards");

    }

    private boolean checkPassword(String password) {
        if (password.length() < 6 || password.length() > 50)
            return false;

        int categoryCount = 0;
        Character character = ' ';
        for (int i = 'a'; i <= 'z'; i++) {
            if (password.indexOf(Character.toChars(i)[0]) >= 0) {
                categoryCount++;
                break;
            }
        }

        for (int i = 'A'; i <= 'Z'; i++) {
            if (password.indexOf(Character.toChars(i)[0]) >= 0) {
                categoryCount++;
                break;
            }
        }

        for (int i = '0'; i <= '9'; i++) {
            if (password.indexOf(Character.toChars(i)[0]) >= 0) {
                categoryCount++;
                break;
            }
        }

        if (password.matches(".*[.-_+!?=]*.")) {
            categoryCount++;
        }
        return true;
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {

    }

    @Override
    public void deleteUser(int userId) throws DALException {

    }
}
