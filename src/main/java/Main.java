import Domain.MainController;
import UI.TUI.TUI;
import dataLayer.dal.IUserDAO;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        try {
            IUserDAO dataAccess = new MainController();
            TUI tui = new TUI(dataAccess);
            tui.runCase();


        } catch (IUserDAO.DALException | SQLException e) {
            e.printStackTrace();
        }
    }
}
