import UI.TUI.TUI;
import dataLayer.dal.IUserDAO;
import dataLayer.UserDAOimpls185124;
import TechnicalService.dto.ERoles;
import TechnicalService.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public class test {
    public static void main(String[] args) {
        try {
            UserDAOimpls185124 dao = new UserDAOimpls185124();
            TUI tui = new TUI();

           tui.runCase();


        } catch (IUserDAO.DALException | SQLException e) {
            e.printStackTrace();

        }
    }
}
