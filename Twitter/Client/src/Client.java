import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client implements Serializable, ActionListener {
    static Socket socket;
    static ObjectInputStream objectInputStream;
    static ObjectOutputStream objectOutputStream;
    static LoginFrame LoginFrame=new LoginFrame();
    static SignUpFrame SignUpFrame=new SignUpFrame();
    static MainMenuFrame MainMenuFrame=new MainMenuFrame();
    static String username;
    static String password;
    static ImageIcon imageIcon=new ImageIcon("images.png");
    static user loggedinUser;
    static ArrayList<Tweet> allTweets= new ArrayList<>();
    static int index=0;
    Scanner scanner = new Scanner(System.in);
    Random random = new Random(new Date().getTime());


    public static void main  (String[] args) {
        Client client = new Client("localhost", 1010);
    }


    Client(String address,int port) {
        try {
            socket = new Socket(address, port);

                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());

                frameMaker();
                tryLogin();
        } catch (SocketException ex){
            System.out.println("Connection Failed.");
            String[] ConnectionLostdialogButtons = {"Reconnect", "Exit"};
            int ClientResponse = JOptionPane.showOptionDialog(null
                    , "Connection Failed."
                    , "Connection Failed."
                    , JOptionPane.YES_NO_OPTION
                    , JOptionPane.ERROR_MESSAGE
                    , null
                    , ConnectionLostdialogButtons
                    , 0);

            if (ClientResponse == -1 || ClientResponse == 1) {
                exit(0);
            } else {
                String[] args={"A"};
                Client.main(args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void tryLogin() {
        LoginFrame.setVisible(true);
        LoginFrame.addLoginPageListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ///////////////////////////LOGIN BUTTON
                if (e.getSource() == LoginFrame.loginbutton) {
                    password= LoginFrame.passwordField.getText();
                    username= LoginFrame.usernametextfield.getText();
                    try {
                        objectOutputStream.writeUTF("loginCheck");
                        objectOutputStream.flush();
                        objectOutputStream.writeUTF(username);
                        objectOutputStream.flush();
                        objectOutputStream.writeUTF(password);
                        objectOutputStream.flush();
                        if (objectInputStream.readUTF().equals("found")) {
                            Gson gson=new Gson();
                            loggedinUser= gson.fromJson(objectInputStream.readUTF(),user.class);
                            Type tweetListType = new TypeToken<ArrayList<Tweet>>() {}.getType();
                              allTweets.addAll(gson.fromJson(objectInputStream.readUTF(),tweetListType));
                            JOptionPane.showMessageDialog(null, "Logged in successfully" +
                                            SignUpFrame.nametextfield.getText()
                                    , "", JOptionPane.INFORMATION_MESSAGE,null);
                            mainMenu();

                            return;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "No account was found with this username and password",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                else if (e.getSource() == LoginFrame.signupbutton) {
                   LoginFrame.setVisible(false);
                    SignUpFrame.setVisible(true);
                }
            }
        });



            SignUpFrame.addLoginPageListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == SignUpFrame.loginbutton) {
                        LoginFrame.setVisible(true);
                        SignUpFrame.setVisible(false);
                    } else if (e.getSource() == SignUpFrame.signupbutton)
                        createAccount();
                }

            });

    }

    public static void mainMenu() throws IOException {
        setMainMenu();
    }

    public static void setMainMenu() throws IOException {

        LoginFrame.setVisible(false);
        SignUpFrame.setVisible(false);
        MainMenuFrame.setVisible(true);
        MainMenuFrame.followingField.setForeground(Color.WHITE);
        MainMenuFrame.followersField.setForeground(Color.WHITE);
        MainMenuFrame.likedPostsField.setForeground(Color.WHITE);
        MainMenuFrame.savedPostsField.setForeground(Color.WHITE);
        MainMenuFrame.myPostsField.setForeground(Color.WHITE);

        MainMenuFrame.followingField.setText(String.valueOf(loggedinUser.getFollowings().size()));
        MainMenuFrame.followersField.setText(String.valueOf(loggedinUser.getFollowers().size()));
        MainMenuFrame.likedPostsField.setText(String.valueOf(loggedinUser.getLikedPosts().size()));
        MainMenuFrame.savedPostsField.setText(String.valueOf(loggedinUser.getSavedPosts().size()));
        MainMenuFrame.myPostsField.setText(String.valueOf(loggedinUser.getAllPost().size()));

            MainMenuFrame.getUpdate();

    }

    private static void createAccount() {

        try {
            objectOutputStream.writeUTF("register");
            objectOutputStream.flush();
            objectOutputStream.writeUTF( SignUpFrame.nametextfield.getText());
            objectOutputStream.flush();
            objectOutputStream.writeUTF( SignUpFrame.usernametextfield.getText());
            objectOutputStream.flush();
            objectOutputStream.writeUTF( SignUpFrame.passwordField.getText());
            objectOutputStream.flush();
            objectOutputStream.writeUTF( SignUpFrame.phonenumbertextfield.getText());
            objectOutputStream.flush();
            objectOutputStream.writeUTF( SignUpFrame.emailtextfield.getText());
            objectOutputStream.flush();
            objectOutputStream.writeUTF( SignUpFrame.birthDateTextField.getText());
            objectOutputStream.flush();
            objectOutputStream.writeUTF( SignUpFrame.biotextarea.getText());
            objectOutputStream.flush();
            String ServerResponse=objectInputStream.readUTF();
               if(ServerResponse.equals("registered")){
                  JOptionPane.showMessageDialog(null, "Signedup Sucessfully, Welcome " + SignUpFrame.nametextfield.getText(), "", JOptionPane.INFORMATION_MESSAGE,null);
                  LoginFrame.setVisible(true);
                  SignUpFrame.setVisible(false);;
               }
               else {
                       JOptionPane.showMessageDialog(null,
                               ServerResponse,
                               "Error",
                               JOptionPane.ERROR_MESSAGE);

               }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void frameMaker(){
        SwingUtilities.invokeLater(LoginFrame:: new);
        SwingUtilities.invokeLater(SignUpFrame:: new);
        SwingUtilities.invokeLater(MainMenuFrame::new);
        MainMenuFrame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                exit(0);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }


        });
        LoginFrame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                exit(0);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }


        });
        SignUpFrame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                exit(0);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}