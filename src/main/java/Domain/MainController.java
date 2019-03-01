package Domain;

import TechnicalService.dto.UserDTO;
import dataLayer.DataAccess;
import dataLayer.dal.IUserDAO;

import java.util.List;

public class MainController implements IUserDAO {
    private IUserDAO dataAcces = new DataAccess();


    //-- Methods with no Checks --//
    @Override
    public UserDTO getUser(int userId) throws DALException {
        return dataAcces.getUser(userId);
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return dataAcces.getUserList();
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        dataAcces.deleteUser(userId);
    }


    //-- Methods with extra Checks --//
    @Override
    public void createUser(UserDTO user) throws DALException {
        if (! checkIni(user.getIni()) )
            throw new DALException("Initials don't fall within constraints");
        if (! checkPassword( user.getPassword() ) )
            throw new DALException("Password doesn't live up to security standards");
        dataAcces.createUser(user);

    }



    @Override
    public void updateUser(UserDTO user) throws DALException {
        UserDTO userAsIs = dataAcces.getUser(user.getUserId());

        //check all variables, for whether or not they are updated and live up to the requirements
        if (user.getUserName() == null)
            user.setUserName(userAsIs.getUserName());

        if (user.getIni() == null)
            user.setIni(userAsIs.getIni());
        else
            if (!checkIni(user.getIni()))
                throw new DALException("Invalid initials");

        if (user.getCpr() == -1)
            user.setCpr(userAsIs.getCpr());

        if (user.getPassword() == null)
            user.setPassword(userAsIs.getPassword());
        else if (!checkPassword(user.getPassword()))
            throw new DALException("Invalid password");

        if (user.getRoles() == null)
            user.setRoles(userAsIs.getRoles());


        dataAcces.updateUser(user);
    }





    /**
     * Checks if the initials live up to the requirements
     * @param ini The initials to check
     * @return true if all requirements are met, false otherwise
     */
    private boolean checkIni(String ini) {
        if (! (ini.length() >= 2 && ini.length() <= 4) )
            return false;

        return true;
    }


    /**
     * Checks if the given password lives up to the requirements
     * @param password The user-chosen password
     * @return true if all requirements are met, false otherwise
     */
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

        for (int i = 0; i < password.length(); i++) {
            if (
                    password.indexOf('.') >= 0 ||
                    password.indexOf('_') >= 0 ||
                    password.indexOf('+') >= 0 ||
                    password.indexOf('!') >= 0 ||
                    password.indexOf('?') >= 0 ||
                    password.indexOf('=') >= 0 ||
                    password.indexOf('-') >= 0
            ) {
                categoryCount++;
                break;
            }
        }

        if (categoryCount < 3)
            return false;

        return true;
    }
}
