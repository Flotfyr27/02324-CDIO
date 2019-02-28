import UI.TUI.TUI;
import dataLayer.dal.IUserDAO;
import java.sql.SQLException;


public class test {
    public static void main(String[] args) {
        try {
            TUI tui = new TUI();
           tui.runCase();


        } catch (IUserDAO.DALException | SQLException e) {
            e.printStackTrace();

        }
    }
}
