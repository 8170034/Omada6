import java.util.ArrayList;

public class Login {
    public Login() {
    }

    String newUsername;
    String newPassword;
    ArrayList<Profile>users = new ArrayList<>;

    public Login(String newUsername, String newPassword, ArrayList<Profile>users){
        this.newUsername = newUsername;
        this.newPassword = newPassword;
        this.user = user;

  public Boolean checkLogin(newUsername, newPassword, users) {
    for (int i=0; i<user.length; i++) {
        if (newUsername || newPassword == user[i]) {
               return true;
         System.out.println("Welcome"); } else {
            return false;
        } Systerm.out.println("Wrong Username or password");
       }
  }
    }
}
