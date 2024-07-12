import java.io.Serializable;
import java.util.ArrayList;

public class user implements Serializable {
    private String name;
    private String username;
    private  String password;
    private  String number;

    public user(String name, String username, String password, String number, String email, String birthDate, String bio) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.number = number;
        this.email = email;
        this.birthDate = birthDate;
        this.bio = bio;
        this.searchedPeople=new ArrayList<>();
        this.followers=new ArrayList<>();
        this.followings=new ArrayList<>();
        this.myPosts=new ArrayList<>();
        this.likedPosts=new ArrayList<>();
        this.savedPosts=new ArrayList<>();
    }

    private String email;
    private String birthDate;
    private String bio;
    private  ArrayList<String> followers;
    private  ArrayList<String> followings;
    private  ArrayList<String> searchedPeople;
    private  ArrayList<Tweet> savedPosts;
    private  ArrayList<Tweet> likedPosts;
    public ArrayList<Tweet> myPosts=new ArrayList<>();

    public ArrayList<String> getSearchedPeople() {
        return searchedPeople;
    }

    public void setSearchedPeople(String searchedPeople) {
        this.searchedPeople.add(searchedPeople);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean checkPassword(String password) {
        return (this.password.equals(password));
    }
    public String getPassword() {
        return this.password;
    }

    public ArrayList<Tweet> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(Tweet savedPosts) {
        this.savedPosts.add(savedPosts);
    }
    public void removeSavedPosts(int id) {
        for (int i = 0; i <this.savedPosts.size(); i++) {
            if(id==savedPosts.get(i).getId()) {
                savedPosts.remove(i);
                break;
            }
        }
    }
    public void removeSearchedPeople(String name) {
        for (int i = 0; i <this.searchedPeople.size(); i++) {
            if(this.searchedPeople.get(i).equals(name)){
                searchedPeople.remove(i);
                break;
            }
        }
    }
    public ArrayList<Tweet> getLikedPosts() {
        return likedPosts;
    }
    public void setLikedPosts(Tweet likedPosts) {
        this.likedPosts.add(likedPosts);
    }
    public void removeLikedPosts(int id) {
        for (int i = 0; i <this.likedPosts.size(); i++) {
            if(id==likedPosts.get(i).getId()) {
                likedPosts.remove(i);
                break;
            }
        }

    }
    public void removePosts(Tweet removePosts) {
        this.myPosts.remove(removePosts);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ArrayList<String> getFollowings() {
        return followings;
    }

    public void setFollowings(String following) {
        this.followings.add(following);
    }

    public void removeFollowings(String following) {
        this.followings.remove(following);
    }
    public ArrayList<String> getFollowers() {
        return followers;
    }

    public void addFollowers(String follower) {
        this.followers.add(follower);
    }
    public void removeFollowers(String follower) {
        this.followers.remove(follower);
    }
    public Tweet getPost(int i ){
        return myPosts.get(i);
    }

    public ArrayList<Tweet> getAllPost(){
        return myPosts;
    }

    public void setPosts(Tweet post) {
        this.myPosts.add(post);
    }

}
