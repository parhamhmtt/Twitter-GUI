import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.tools.javac.Main;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

import static javax.swing.JOptionPane.showMessageDialog;
import static sun.net.www.protocol.http.AuthCacheValue.Type.Server;

public class Server implements Serializable {
    static int loggedInUserIndex;
    static ArrayList<user> users = new ArrayList<>();
    static ArrayList<Tweet> allTweets = new ArrayList<>();
    Gson gson=new Gson();
    final static Type tweetListType = new TypeToken<ArrayList<Tweet>>() {}.getType();
    final static Type userListType = new TypeToken<ArrayList<user>>() {}.getType();
    final static int port = 1010;


    public static void main(String[] args) throws IOException {
        loadUsers();
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = server.accept();
                new Thread(() -> new Server(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("IOException\n" + e.getMessage());
        }
    }

    public  Server(Socket clientSocket) {

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {
            loadUsers();
            while (clientSocket.isConnected()) {
                String ClientRequest;
                Switch:
                switch (ClientRequest = objectInputStream.readUTF().trim()) {
                    case "loginCheck":
                        Boolean userFound=false;
                        String username=objectInputStream.readUTF();
                        String password=objectInputStream.readUTF();

                        for (int i = 0; i < users.size(); i++) {
                            if (users.get(i).getUsername().equals(username)) {
                                if (users.get(i).checkPassword(password)) {
                                    loggedInUserIndex=i;
                                    userFound=true;
                                    break;
                                }
                            }
                        }
                        if(userFound){
                            objectOutputStream.writeUTF("found");
                            objectOutputStream.flush();
                            objectOutputStream.writeUTF(gson.toJson(users.get(loggedInUserIndex)));
                            objectOutputStream.flush();
                            objectOutputStream.writeUTF(gson.toJson(allTweets));
                            objectOutputStream.flush();
                        } else {
                            objectOutputStream.writeUTF("notfound");
                            objectOutputStream.flush();
                        }


                        break;
                    case "register":
                        String name=objectInputStream.readUTF();
                        username=objectInputStream.readUTF();
                        password=objectInputStream.readUTF();
                        String phoneNumber=objectInputStream.readUTF();
                        String email=objectInputStream.readUTF();
                        String birthDate=objectInputStream.readUTF();
                        String bio=objectInputStream.readUTF();

//                        if((name.isBlank()||username.isBlank()||password.isBlank()||email.isBlank()||birthDate.isBlank()||phoneNumber.isBlank())){
//                            objectOutputStream.writeUTF("only biography can be blank");
//                            objectOutputStream.flush();
//                        }
//                         else if (!Pattern.compile(".*[a-zA-Z].*").matcher(name).matches()){
//                             objectOutputStream.writeUTF("name most only contain letters");
//                             objectOutputStream.flush();
//                         } else if (!Pattern.compile(".*[a-zA-Z].*[0-9].*").matcher(username).matches()) {
//                             objectOutputStream.writeUTF("username most only contain letters and numbers");
//                             objectOutputStream.flush();
//                         } else if (!Pattern.compile("^09\\d{9}$").matcher(phoneNumber).matches()) {
//                             objectOutputStream.writeUTF("phone number isnt either 11 letters or doesnt start with 09");
//                             objectOutputStream.flush();
//                         }
//                         else if (!Pattern.compile("^\\\\d{4}/\\\\d{2}/\\\\d{2}$").matcher(phoneNumber).matches()) {
//                             objectOutputStream.writeUTF("The correct format of birthdate yyyy/mm/dd");
//                             objectOutputStream.flush();
//                         }
//                         else if (!Pattern.compile("^[a-zA-Z0-9._%+-]+@gmail\\.com$").matcher(email).matches()) {
//                             objectOutputStream.writeUTF("example@gmail.com");
//                             objectOutputStream.flush();
//                         }
//                         else if(!(Pattern.compile(".*[a-zA-Z].*[0-9].*").matcher(password).matches()&& password.length()>=8)){
//                             objectOutputStream.writeUTF("password must only contains letters and numbers and at least 8 charachters");
//                             objectOutputStream.flush();
//                         }
//                        else {
//                            boolean b=false;
//                            for (int i = 0; i <users.size() ; i++) {
//                                if(users.get(i).getUsername()==username){
//                                    b=true;
//                                    break;
//                                }
//
//                            }
//                            if(b) {
//                                objectOutputStream.writeUTF("user name is taken before");
//                                objectOutputStream.flush();
//                            }
//                            else {
                                users.add(new user(name, username, password, phoneNumber, email, birthDate, bio));
                                saveUsers();
                                objectOutputStream.writeUTF("registered");
                                objectOutputStream.flush();
                           // }
                        //}
                        break ;
                    case "update":
                        users.set(loggedInUserIndex,gson.fromJson(objectInputStream.readUTF(),user.class));
                        allTweets.clear();
                        allTweets.addAll(gson.fromJson(objectInputStream.readUTF(),tweetListType));
                        saveUsers();
                        break;
                    case "getUpdate":
                        objectOutputStream.writeUTF(gson.toJson(users.get(loggedInUserIndex)));
                        objectOutputStream.flush();
                        objectOutputStream.writeUTF(gson.toJson(allTweets));
                        objectOutputStream.flush();
                        break;
                    case "UpdateLike":
                        int TweetId=objectInputStream.readInt();
                        int userLikeIndex = 0;
                        Boolean likedBefore=false;
                        for (int i = 0; i <users.get(loggedInUserIndex).getLikedPosts().size(); i++) {
                            if(users.get(loggedInUserIndex).getLikedPosts().get(i).getId()==TweetId) {
                                likedBefore = true;
                                break;
                            }
                        }

                        here:
                        for (int i = 0; i <allTweets.size(); i++) {
                            if(allTweets.get(i).getId()==TweetId) {
                                for (int j = 0; j <users.size(); j++) {
                                    if(users.get(j).getUsername().equals(allTweets.get(i).getUsername())){
                                        userLikeIndex=j;
                                        break here;
                                    }
                                }
                            }
                        }
                        ///explore and me
                        for (int i = 0; i <allTweets.size(); i++) {
                            if(allTweets.get(i).getId()==TweetId) {
                                if(!likedBefore) {
                                    allTweets.get(i).addLike(users.get(loggedInUserIndex).getUsername());
                                    users.get(loggedInUserIndex).setLikedPosts(allTweets.get(i));
                                }
                                else if(likedBefore){
                                    allTweets.get(i).removeLike(users.get(loggedInUserIndex).getUsername());
                                    users.get(loggedInUserIndex).removeLikedPosts(allTweets.get(i).getId());
                                }
                                break;
                            }
                        }
                        /////////////Destination user
                        for (int i=0; i<users.get(userLikeIndex).getAllPost().size();i++){
                            if(users.get(userLikeIndex).getPost(i).getId()==TweetId){
                                if(!likedBefore)
                                    users.get(userLikeIndex).getPost(i).addLike(users.get(loggedInUserIndex).getUsername());
                                else if(likedBefore)
                                    users.get(userLikeIndex).getPost(i).removeLike(users.get(loggedInUserIndex).getUsername());
                                break ;
                            }
                        }

                        saveUsers();
                        break;
                    case "UpdateSave":
                        TweetId=objectInputStream.readInt();
                        String desUserName ="";
                        for (int i = 0; i < allTweets.size(); i++) {
                            if (allTweets.get(i).getId() == TweetId) {
                                desUserName=allTweets.get(i).getUsername();
                                break;
                            }
                        }
                        user desUser=null;
                        for (int i=0; i<users.size();i++){
                            if(users.get(i).getUsername().equals(desUserName)){
                                desUser=users.get(i);
                                break;
                            }
                        }

                        Boolean savedBefore=false;
                        for (int i = 0; i <users.get(loggedInUserIndex).getSavedPosts().size(); i++) {
                            if(users.get(loggedInUserIndex).getSavedPosts().get(i).getId()==TweetId) {
                                savedBefore = true;
                                break;
                            }
                        }
                        Boolean responseForPublicSaveAble=true;
                        Boolean temp=false;
                        for (int i = 0; i <desUser.getFollowers().size(); i++) {
                            if (desUser.getFollowers().get(i).equals(users.get(loggedInUserIndex).getUsername())){
                                temp=true;
                                break ;
                            }
                        }

                        for (int i = 0; i <allTweets.size(); i++) {
                            if(allTweets.get(i).getId()==TweetId) {
                                if (temp || allTweets.get(i).isSaveAbleByAll()) {
                                    temp=allTweets.get(i).isSaveAbleByAll() || temp;
                                    if (!savedBefore) {
                                        users.get(loggedInUserIndex).setSavedPosts(allTweets.get(i));
                                    } else {
                                        users.get(loggedInUserIndex).removeSavedPosts(allTweets.get(i).getId());
                                    }
                                    break;
                                }
                            }
                        }
                        objectOutputStream.writeBoolean(temp);
                        objectOutputStream.flush();
                        saveUsers();
                        break;
                    case "getDesAccount":
                        int tweetId=objectInputStream.readInt();
                         desUserName = "";
                        for (int i = 0; i < allTweets.size(); i++) {
                            if (allTweets.get(i).getId() == tweetId) {
                                desUserName=allTweets.get(i).getUsername();
                                break;
                            }
                        }
                         desUser=null;
                        for (int i=0; i<users.size();i++){
                            if(users.get(i).getUsername().equals(desUserName)){
                                desUser=users.get(i);
                                break;
                            }
                        }
                        objectOutputStream.writeUTF(gson.toJson(desUser));
                        objectOutputStream.flush();
                        break;
                    case "getDesAccountUser":
                         desUserName = objectInputStream.readUTF().trim();
                         desUser=null;
                        for (int i=0; i<users.size();i++){
                            if(users.get(i).getUsername().equals(desUserName)){
                                desUser=users.get(i);
                                break;
                            }
                        }
                        objectOutputStream.writeUTF(gson.toJson(desUser));
                        objectOutputStream.flush();
                        break;
                    case "ifFollowed":
                        desUserName=objectInputStream.readUTF();
                        Boolean found=false;
                        for(int i=0; i<users.get(loggedInUserIndex).getFollowings().size(); i++){
                            if(users.get(loggedInUserIndex).getFollowings().get(i).equals(desUserName)){
                                found=true;
                                break;
                            }
                        }
                        if(found){
                            objectOutputStream.writeUTF("UnFollow");
                            objectOutputStream.flush();
                        }
                        else {
                            objectOutputStream.writeUTF("Follow");
                            objectOutputStream.flush();
                        }
                        break;
                    case "UnFollow":
                        desUserName=objectInputStream.readUTF();
                        for(int i=0; i<users.size(); i++){
                            if(users.get(i).getUsername().equals(desUserName) && !(desUserName.equals(users.get(loggedInUserIndex).getUsername()))){
                                users.get(loggedInUserIndex).removeFollowings(users.get(i).getUsername());
                                users.get(i).removeFollowers(users.get(loggedInUserIndex).getUsername());
                                break;
                            }
                        }
                        saveUsers();
                        break;
                    case "Follow":
                        desUserName=objectInputStream.readUTF();
                        for(int i=0; i<users.size(); i++){
                            if(users.get(i).getUsername().equals(desUserName) && !(desUserName.equals(users.get(loggedInUserIndex).getUsername()))){
                                users.get(loggedInUserIndex).setFollowings(users.get(i).getUsername());
                                users.get(i).addFollowers(users.get(loggedInUserIndex).getUsername());
                                break;
                            }
                        }
                        saveUsers();
                        break;
                    case "removeFollower":
                        desUserName=objectInputStream.readUTF();
                        for(int i=0; i<users.size(); i++){
                            if(users.get(i).getUsername().equals(desUserName) && !(desUserName.equals(users.get(loggedInUserIndex).getUsername()))){
                                users.get(loggedInUserIndex).removeFollowers(users.get(i).getUsername());
                                users.get(i).removeFollowings(users.get(loggedInUserIndex).getUsername());
                                break;
                            }
                        }
                        saveUsers();
                        break;
                    case "isThisMyAcc":
                        tweetId=objectInputStream.readInt();
                         temp = false;
                        for (int i = 0; i < allTweets.size(); i++) {
                            if (allTweets.get(i).getId() == tweetId) {
                                if(allTweets.get(i).getUsername().equals(users.get(loggedInUserIndex).getUsername()))
                                    temp=true;
                                break ;
                            }
                        }
                        objectOutputStream.writeBoolean(temp);
                        objectOutputStream.flush();
                        break ;
                    case "isThisMyAccUser":
                        username=objectInputStream.readUTF().trim();
                         temp = false;
                        if(username.equals(users.get(loggedInUserIndex).getUsername()))
                            temp=true;
                        objectOutputStream.writeBoolean(temp);
                        objectOutputStream.flush();
                        break ;
                    case "newTweet":
                        Tweet madeTweet=gson.fromJson(objectInputStream.readUTF(),Tweet.class);
                        allTweets.add(madeTweet);
                        users.get(loggedInUserIndex).setPosts(madeTweet);
                        saveUsers();
                        break ;
                    case "editPost":
                        tweetId=objectInputStream.readInt();
                        String newPostText=objectInputStream.readUTF();
                        if (newPostText != null && !newPostText.isEmpty()) {
                            for (int i = 0; i < allTweets.size(); i++){
                                if (allTweets.get(i).getId() == tweetId) {
                                    allTweets.get(i).setBody(newPostText + "\n\n");
                                    break;
                                }
                            }
                            for (int i = 0; i <users.get(loggedInUserIndex).getAllPost().size(); i++) {
                                if (users.get(loggedInUserIndex).getPost(i).getId() == tweetId) {
                                    users.get(loggedInUserIndex).getPost(i).setBody(newPostText + "\n\n");
                                    break;
                                }
                            }
                        }
                        saveUsers();
                    case "addComment":
                        int index=0;
                        tweetId=objectInputStream.readInt();
                        for (int i = 0; i < allTweets.size(); i++) {
                            if (allTweets.get(i).getId() == tweetId) {
                                index=i;
                                break;
                            }
                        }
                        objectOutputStream.writeBoolean(allTweets.get(index).getCommentable());
                        objectOutputStream.flush();
                        if(!allTweets.get(index).getCommentable())
                            break Switch;
                        String comment=objectInputStream.readUTF();
                        if(comment == null || comment.isEmpty()) {
                            objectOutputStream.writeUTF("null");
                            objectOutputStream.flush();
                            break Switch;
                        }

                        desUserName=null;
                        if (comment != null && !comment.isEmpty()) {
                            objectOutputStream.writeUTF("notNull");
                            objectOutputStream.flush();
                            for (int i = 0; i < allTweets.size(); i++) {
                                if (allTweets.get(i).getId() == tweetId) {
                                    allTweets.get(i).setBody(allTweets.get(i).getBody() + "\n" + users.get(loggedInUserIndex).getUsername() + " : " + comment);
                                    if(!allTweets.get(i).getUsername().equals(users.get(loggedInUserIndex).getUsername()))
                                        desUserName=allTweets.get(i).getUsername();
                                    break;
                                }
                            }
                            for (int i = 0; i < users.get(loggedInUserIndex).getAllPost().size(); i++) {
                                if (users.get(loggedInUserIndex).getPost(i).getId() == tweetId) {
                                    users.get(loggedInUserIndex).getPost(i).setBody(users.get(loggedInUserIndex).getPost(i).getBody()
                                            + "\n" + users.get(loggedInUserIndex).getUsername() + " : " + comment);
                                    break;
                                }
                            }
                            label:
                            if(desUserName!=null){
                                for (int i = 0; i<users.size(); i++) {
                                    if(users.get(i).getUsername().equals(desUserName)){
                                        for (int j = 0; j <users.get(i).getAllPost().size(); j++) {
                                            if(users.get(i).getPost(j).getId()==tweetId){
                                                users.get(i).getPost(j).setBody(users.get(loggedInUserIndex).getPost(i).getBody()
                                                        + "\n" + users.get(loggedInUserIndex).getUsername() + " : " + comment);
                                                break label;
                                            }

                                        }
                                    }

                                }
                            }
                        }
                        saveUsers();
                        break ;
                    case "deletePost":
                        tweetId=objectInputStream.readInt();
                        for (int k = 0; k < allTweets.size(); k++) {
                            if (allTweets.get(k).getId() == tweetId) {
                                allTweets.get(k).setBody("");
                                break;
                            }
                        }

                        for (int j = 0; j < users.get(loggedInUserIndex).getAllPost().size(); j++) {
                            if (users.get(loggedInUserIndex).getPost(j).getId() == tweetId) {
                                users.get(loggedInUserIndex).getAllPost().remove(j);
                                break;
                            }
                        }
                        saveUsers();
                    case "setCommentAble":
                        tweetId=objectInputStream.readInt();
                        Boolean commentable=objectInputStream.readBoolean();
                        for (int i = 0; i <allTweets.size(); i++) {
                            if (allTweets.get(i).getId() == tweetId) {
                                allTweets.get(i).setCommentable(commentable);
                                break;
                            }
                        }

                        for (int i = 0; i <users.get(loggedInUserIndex).getAllPost().size(); i++) {
                            if (users.get(loggedInUserIndex).getPost(i).getId() == tweetId) {
                                users.get(loggedInUserIndex).getPost(i).setCommentable(commentable);
                                break;
                            }
                        }
                        saveUsers();
                        break ;
                    case "addSearch":
                        username=objectInputStream.readUTF();
                        temp=false;
                        for (int i = 0; i <users.size(); i++) {
                            if(users.get(i).getUsername().equals(username)){
                                users.get(loggedInUserIndex).setSearchedPeople(username);
                                temp=true;
                                break;
                            }

                        }
                        objectOutputStream.writeBoolean(temp);
                        objectOutputStream.flush();
                        saveUsers();
                        break ;
                    case "deleteFromSearch":
                        username=objectInputStream.readUTF();
                        users.get(loggedInUserIndex).removeSearchedPeople(username);
                        saveUsers();
                        break ;

//                    case "deleteAccount":
//                        AccountNumber = objectInputStream.readInt();
//                        Password = objectInputStream.readUTF();
//                        bankAccount = findAccount(AccountNumber);
//                        if (bankAccount.checkPassword(Password)) {
//                            bankAccount.deleteBankAccount();
//                            objectOutputStream.writeBoolean(true);
//                            objectOutputStream.flush();
//                        } else {
//                            objectOutputStream.writeBoolean(false);
//                            objectOutputStream.flush();
//                        }
//                        saveAccounts();
//                        break;
                    default:
                        System.out.println(ClientRequest);
                        break;
                }
            }


        } catch (IOException e) {
            System.out.println("client page closed");
        }

    }

    private static void loadUsers() {
        try {
            File myFile = new File("Users.txt");
            Gson gson = new Gson();
            if (myFile.exists()) {
                FileReader myFileReader2 = new FileReader(myFile);
                users.clear();
                users.addAll(gson.fromJson(myFileReader2,userListType));
            }
            File myFile2 = new File("allTweets.txt");
            if (myFile2.exists()) {
                FileReader myFileReader2 = new FileReader(myFile2);
                allTweets.clear();
                allTweets.addAll(gson.fromJson(myFileReader2,tweetListType));
                myFileReader2.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException, " + e.getMessage());
        }
    }
    private static void saveUsers() {
        Gson gson = new Gson();
        try {
            File myFile = new File("Users.txt");
            FileWriter myFileWriter = new FileWriter(myFile);
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
            myFileWriter.write(gson.toJson(users,userListType));
            myFileWriter.close();

            myFileWriter.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {


            File myFile = new File("allTweets.txt");
            FileWriter myFileWriter = new FileWriter(myFile);
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
            myFileWriter.write(gson.toJson(allTweets,tweetListType));
            myFileWriter.close();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }



    }

}

