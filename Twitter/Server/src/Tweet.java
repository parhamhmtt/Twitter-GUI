import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tweet implements Serializable {
    private int Id;
    private String Body;
    private String Username;
    private String Name;
    private ArrayList <String> Likes = new ArrayList<>();
    private Boolean Commentable;
    private boolean explorable;
    private boolean saveAbleByAll;

    public Tweet(int id, String body, String username, String name, Boolean commentable,Boolean explorable,Boolean saveAbleByAll) {
        Id = id;
        Body = body;
        Username = username;
        Name = name;
        Likes = new ArrayList<>();
        Commentable = commentable;
        this.explorable= explorable;
        this.saveAbleByAll=saveAbleByAll;
    }

    public int getId() {
        return this.Id;
    }

    public ArrayList<String>  getLike() {
        return this.Likes;
    }
    public int likeCount(){
        return this.Likes.size();
    }

    public void addLike(String  like) {
        this.Likes.add(like);
    }
    public void removeLike(String  like) {
        Likes.remove(like);
    }

    public Boolean getCommentable() {
        return Commentable;
    }

    public void setCommentable(Boolean commentable) {
        Commentable = commentable;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getBody() {
        return Body;
    }

    public boolean isExplorable() {
        return explorable;
    }

    public boolean isSaveAbleByAll() {
        return saveAbleByAll;
    }

    public void setBody(String body) {
        this.Body = body;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getName() {
        return Name;
    }

    public void setExplorable(boolean explorable) {
        this.explorable = explorable;
    }

    public void setName(String name) {
        Name = name;
    }
}
