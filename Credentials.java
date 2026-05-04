package farm.manager;

public class Credentials {
    
    private static final String USERNAME = "vb";
    private static final String PASSWORD = "vb";

    public static boolean checkLogin(String username, String password) {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }

 }