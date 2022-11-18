import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount {

    String newUsername;
    String newPassword;

    public CreateAccount(){}

    public CreateAccount(String newUsername, String newPassword){
        this.newUsername = newUsername;
        this.newPassword =newPassword;
    }

    public boolean create(String username, String password){
        Profile newProfile;
        if(checkPassword(password)){
            newProfile = new Profile(username,password);
            return true;
        }
        return false;
    }

    public boolean checkPassword(String password){
        if(password.length()>=8){
            Pattern letter = Pattern.compile("[a-zA-z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

            Matcher hasLetter = letter.matcher(password);
            Matcher hasDigit = digit.matcher(password);
            Matcher hasSpecial = special.matcher(password);

            return hasLetter.find() && hasDigit.find() && hasSpecial.find();
        }

        return false;

    }
}
