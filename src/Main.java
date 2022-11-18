public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");

        String username =  "antonis";
        String password = "An@123";

        CreateAccount createAccount = new CreateAccount(username,password);
        System.out.println(createAccount.create(username,password));
    }
}