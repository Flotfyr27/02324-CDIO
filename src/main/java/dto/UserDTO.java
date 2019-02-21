package dto;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDTO implements Serializable{

    private static final long serialVersionUID = 4545864587995944260L;
    private int	userId;
    private String userName;
    private String ini;
    private boolean[] roles;
    private int cpr;
    private String password;

    public UserDTO(int id, String userName, String ini, int cpr, String password, int ... roles) {
        userId = id;
        this.userName = userName;
        this.ini = ini;
        this.cpr = cpr;
        this.password = password;

        this.roles = new boolean[4];
        for (int i = 0; i < this.roles.length; i++) {
            this.roles[i] = false;
        }
        for (int i = 0; i < roles.length; i++) {
            this.roles[roles[i]] = true;
        }
    }

    public UserDTO(int id, String userName, String ini, int cpr, String password, Boolean ... roles) {
        userId = id;
        this.userName = userName;
        this.ini = ini;
        this.cpr = cpr;
        this.password = password;

        this.roles = new boolean[4];
        for (int i = 0; i < this.roles.length; i++) {
            this.roles[i] = false;
        }
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getIni() {
        return ini;
    }
    public void setIni(String ini) {
        this.ini = ini;
    }

    public boolean[] getRoles() {
        return roles;
    }

    //TODO delete this function?
    public void setRoles(boolean[] roles) {
        this.roles = roles;
    }

    public int getCpr() {
        return cpr;
    }
    public void setCpr(int cpr) {
        this.cpr = cpr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role, boolean hasRole){
        roles[role] = hasRole;
    }


    @Override
    public String toString() {
        return "dto.UserDTO [userId=" + userId + ", userName=" + userName + ", ini=" + ini + ", roles=" + rolesToString() + "]";
    }

    private String rolesToString () {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < roles.length; i++) {
            if (roles[i]) {
                str.append(ERoles.values()[i].name());
                str.append(", ");
            }
        }
        return str.toString();
    }



}