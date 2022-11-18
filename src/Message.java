public class Message {
    private String username;
    private String time;
    private String context;
    private String recipientID;

    //constructor se periptwsi pou to minima einai gia olous-diladi post.
    public Message(String userID,String time,String context){
        this.context=context;
        this.username=userID;
        this.time=time;
    }

    //constructor se periptwsi pou to minima einai gia ena memonomeno atomo.
    public Message(String userID,String time,String context,String recipientID){
        this.context=context;
        this.username=userID;
        this.time=time;
        this.recipientID=recipientID;
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
