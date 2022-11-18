import java.util.ArrayList;
import java.util.HashMap;

public class Message {
    private String username;
    private String time;
    private String context;
    private String recipientID;

    private HashMap<ArrayList<Profile>,Integer> likes; //hashmap gia likes. exei mesa arraylist me xristes pou exoun
                                                        // kanei like kai int me posa like exei

    //constructor se periptwsi pou to minima einai gia olous-diladi post.
    public Message(String userID,String time,String context){
        this.context=context;
        this.username=userID;
        this.time=time;
        this.likes = new HashMap<>(); //arxikopoiei ena keno arraylist gia like
    }

    //constructor se periptwsi pou to minima einai gia ena memonomeno atomo.
    public Message(String userID,String time,String context,String recipientID){
        this.context=context;
        this.username=userID;
        this.time=time;
        this.recipientID=recipientID;
        this.likes = new HashMap<>(); //auto mporei kai na einai custom, an einai prepei na perastei ena arraylist san orisma
    }

    //getters
    public String getUsername() {
        return username;
    }

    public String getContext() {
        return context;
    }

    public String getTime() {
        return time;
    }

    public String getRecipientID() {
        return recipientID;
    }

    public HashMap<ArrayList<Profile>, Integer> getLikes() {
        return likes;
    }

    public void setLikes(HashMap<ArrayList<Profile>, Integer> likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Message{" +
                "username='" + username + '\'' +
                ", time='" + time + '\'' +
                ", context='" + context + '\'' +
                ", recipientID='" + recipientID + '\'' +
                '}';
    }
}
