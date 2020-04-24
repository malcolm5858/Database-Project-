
public class Login {
    public static User checkLogin(String username, String password) {
        User user = new User(username);
        if (!user.getId().equals("0") && user.getPassword().equals(password)) {
            user.setLoginTime();
            return user;
        } else {
            return null;
        }
    }

    public static void logout(User user) {
        user.setLogoutTime();
    }
}