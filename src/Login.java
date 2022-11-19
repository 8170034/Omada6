import java.util.ArrayList;

public class Login {
    public boolean b1 = true;
    public boolean b2 = false;

    public Login() {
    }

    String newUsername;
    String newPassword;
    ArrayList<Profile>users = new ArrayList<Profile>;

    public Login(String newUsername, String newPassword, ArrayList<Profile>users){
        this.newUsername = newUsername;
        this.newPassword = newPassword;
        this.user = user;

    public Boolean checkuser(String newUsername, Arraylist<Profile> user) {
        for (int i = 0; i < user.length; i++) {
            if (newUsername == user.get[i].username) {
                return b1;
            } else {
                return b2;
            }
        }
    }

    }

    public boolean checkPassword(String newPassword, Arraylist<Profile> user) {
        for (int i = 0; i < user.length; i++) {
            if (newPassword == user.get[i].password) {
                return b1;

            } else {
                return b2;
            }

        }

    }

}
