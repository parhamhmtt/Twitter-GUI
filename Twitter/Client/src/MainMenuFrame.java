import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.in;
import static javax.swing.JColorChooser.showDialog;
import static javax.swing.JOptionPane.*;

class MainMenuFrame extends JFrame implements Serializable {
    private static final int BORDER_SIZE = 5;
    private static final int SWITCH_BUTTON_WIDTH = 200;
    private static final int SWITCH_BUTTON_HEIGHT = 40;
    private static final int INPUT_FIELD_HEIGHT = 30;
    private static final int SEND_BUTTON_WIDTH = 100;
    private static final int SEND_BUTTON_HEIGHT = 30;
    public JPanel profilePanel;
    public JPanel profilePanelDes;
    public JTextField userNameFieldDes;
    public JPanel explorePanel;
    public JPanel savedAndLikedPanelDes;
    public JPanel MYpPanel;
    public JPanel MYpPanelFF;
    public JButton switchButtonExplore;
    public JButton switchButtonProfile;
    public JButton FollowingExploreButton;
    private JPanel postsPanel;
    private JPanel postsPanelDes;
    private JScrollPane scrollPaneDes;
     private JPanel postsPanelP;
    private JPanel postsPanelPFF;
    public JTextField bioFieldDes;
    public JTextField nameField;
    public JTextField nameFieldDes;
    JButton profileButton=new JButton();
    JButton searchBox=new JButton();
    JButton profileButtonDes=new JButton();
    JButton profileButtonPFF=new JButton();
    Gson gson = new Gson();
    private ArrayList<JPanel> postPanels = new ArrayList<>();
    private ArrayList<JPanel> postPanelsDes = new ArrayList<>();
    private ArrayList<JPanel> postPanelsP = new ArrayList<>();
    private ArrayList<JPanel> postPanelsPFF = new ArrayList<>();
    public JTextField inputFieldP;
    public JScrollPane scrollPane;
    public JScrollPane scrollPaneP;
    public JScrollPane scrollPanePFF;
    public JButton sendButton;
    public JButton reloadButton;
    public JLabel followingField;
    public JLabel followingFieldDes;
    public JButton viewFollowingButton;
    public JLabel followersField;
    public JButton viewFollowersButton;
    public JLabel myPostsField;
    public JButton viewMyPostsButton;
    public JLabel savedPostsField;
    public JButton viewSavedPostsButton;
    public JLabel likedPostsField;
    public JButton viewLikedPostsButton;
    public JButton viewFollowingButtonDes;
    public JLabel followersFieldDes;
    public JButton viewFollowersButtonDes;
    public JLabel myPostsFieldDes;
    public JButton viewMyPostsButtonDes;
    public JButton viewFollowOptionDes;

    public JTextField usernameField;
    public  JTextField bioField;
    public MainMenuFrame() {
        initializeFrame();
        initializeProfilePanel();
        initializeProfilePanelDes();
        initializeMyPostsPanel();
        initializeExplorePanel();
        initializeSaveLikedPanel();
        initializeFFpanel();
        initializeSwitchButton();

        add(explorePanel);
        setVisible(false);
    }

    private void initializeFrame() {
        setIconImage(new ImageIcon("images.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setBorder(BorderFactory.createLineBorder(new Color(1, 13, 80), BORDER_SIZE, true));
        setLayout(null);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setResizable(false);
        getContentPane().setBackground(new Color(1, 13, 80));
    }

    private void initializeProfilePanel() {
        profilePanel = new JPanel(null);
        profilePanel.setOpaque(true);
        profilePanel.setBounds(0, 0, getWidth(), getHeight());
        usernameField=new JTextField();
        usernameField.setBounds(50,10,500,30);
        usernameField.setEditable(false);
        usernameField.setFont(new Font("Tahoma",Font.BOLD,15));
        usernameField.setBackground(new Color(1, 13, 80));
        usernameField.setEditable(false);
        usernameField.setFocusable(false);
        usernameField.setForeground(Color.GREEN);
        usernameField.setBackground(Color.black);
        profilePanel.add(usernameField,LEFT_ALIGNMENT);

        bioField=new JTextField();
        bioField.setBounds(700,10,500,30);
        bioField.setEditable(false);
        bioField.setFont(new Font("Tahoma",Font.BOLD,15));
        bioField.setBackground(new Color(1, 13, 80));
        bioField.setEditable(false);
        bioField.setFocusable(false);
        bioField.setForeground(Color.GREEN);
        bioField.setBackground(Color.BLACK);
        profilePanel.add(bioField,LEFT_ALIGNMENT);

        nameField=new JTextField();
        nameField.setBounds(700,60,500,30);
        nameField.setEditable(false);
        nameField.setFont(new Font("Tahoma",Font.BOLD,15));
        nameField.setBackground(new Color(1, 13, 80));
        nameField.setEditable(false);
        nameField.setFocusable(false);
        nameField.setForeground(Color.GREEN);
        nameField.setBackground(Color.BLACK);
        profilePanel.add(nameField,LEFT_ALIGNMENT);

        followersField = new JLabel();
        followersField.setBounds(250, 50, 100, 30);
        profilePanel.add(followersField);

        followingField = new JLabel();
        followingField.setBounds(250, 100, 100, 30);
        profilePanel.add(followingField);

        myPostsField = new JLabel();
        myPostsField.setBounds(250, 150, 100, 30);
        profilePanel.add(myPostsField);

        savedPostsField = new JLabel();
        savedPostsField.setBounds(250, 200, 100, 30);
        profilePanel.add(savedPostsField);

        likedPostsField = new JLabel();
        likedPostsField.setBounds(250, 250, 100, 30);
        profilePanel.add(likedPostsField);

        viewFollowersButton = new JButton("Followers");
        viewFollowersButton.setBounds(50, 50, 150, 30);
        viewFollowersButton.setFocusable(false);
        viewFollowersButton.setForeground(Color.GREEN);
        viewFollowersButton.setBackground(Color.black);
        profilePanel.add(viewFollowersButton);

        viewFollowingButton = new JButton("Following");
        viewFollowingButton.setBounds(50, 100, 150, 30);
        viewFollowingButton.setFocusable(false);
        viewFollowingButton.setForeground(Color.GREEN);
        viewFollowingButton.setBackground(Color.black);
        profilePanel.add(viewFollowingButton);

        viewMyPostsButton = new JButton("My Posts");
        viewMyPostsButton.setBounds(50, 150, 150, 30);
        viewMyPostsButton.setFocusable(false);
        viewMyPostsButton.setForeground(Color.GREEN);
        viewMyPostsButton.setBackground(Color.black);
        profilePanel.add(viewMyPostsButton);

        viewSavedPostsButton = new JButton("Saved Posts");
        viewSavedPostsButton.setBounds(50, 200, 150, 30);
        viewSavedPostsButton.setFocusable(false);
        viewSavedPostsButton.setForeground(Color.GREEN);
        viewSavedPostsButton.setBackground(Color.black);
        profilePanel.add(viewSavedPostsButton);

        viewLikedPostsButton = new JButton("Liked Posts");
        viewLikedPostsButton.setBounds(50, 250, 150, 30);
        viewLikedPostsButton.setFocusable(false);
        viewLikedPostsButton.setForeground(Color.GREEN);
        viewLikedPostsButton.setBackground(Color.black);
        profilePanel.add(viewLikedPostsButton);
        profilePanel.setBackground(new Color(1, 13, 80));

         searchBox=new JButton("searchBox");
        searchBox = new JButton("searchBox");
        searchBox.setBounds((getWidth() - SWITCH_BUTTON_WIDTH) / 2 +390-SWITCH_BUTTON_WIDTH, getHeight() - 95, SWITCH_BUTTON_WIDTH, SWITCH_BUTTON_HEIGHT);
        searchBox.setForeground(Color.GREEN);
        searchBox.setFocusable(false);
        searchBox.setBackground(Color.BLACK);
        searchBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
        profilePanel.add(searchBox);
        
        JButton logout=new JButton("LOG OUT");
        logout.setBounds((getWidth() - SWITCH_BUTTON_WIDTH) / 2 +380-2*SWITCH_BUTTON_WIDTH, getHeight() - 95, SWITCH_BUTTON_WIDTH, SWITCH_BUTTON_HEIGHT);
        logout.setForeground(Color.GREEN);
        logout.setFocusable(false);
        logout.setBackground(Color.BLACK);
        logout.setFont(new Font("Tahoma", Font.PLAIN, 15));
        profilePanel.add(searchBox);
        profilePanel.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Client.tryLogin();
            }
        });
        JButton finalSearchBox = searchBox;
        searchBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=-1; i<Client.loggedinUser.getSearchedPeople().size();i++){
                    if(i!=-1)
                        SavedAndLikedPage(Client.loggedinUser.getSearchedPeople().get(i),"SEARCHED");
                    else
                        SavedAndLikedPage("SEARCHED","SEARCHED");
                }
                JButton searchUser=new JButton("SearchUser");
                searchUser.setBounds((getWidth() - SWITCH_BUTTON_WIDTH) / 2 +390-SWITCH_BUTTON_WIDTH, getHeight() - 95, SWITCH_BUTTON_WIDTH, SWITCH_BUTTON_HEIGHT);
                searchUser.setForeground(Color.GREEN);
                searchUser.setFocusable(false);
                searchUser.setBackground(Color.BLACK);
                searchUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
                searchUser.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String  SearchAddList= showInputDialog("Searched person username", "");
                        try {
                            Client.objectOutputStream.writeUTF("addSearch");
                            Client.objectOutputStream.flush();
                            Client.objectOutputStream.writeUTF(SearchAddList);
                            Client.objectOutputStream.flush();
                            if(Client.objectInputStream.readBoolean()){
                                showMessageDialog(null,"User Found");
                            }
                            else
                                showMessageDialog(null,"User notFound");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                savedAndLikedPanelDes.add(searchUser);
                savedAndLikedPanelDes.add(reloadButton);
                savedAndLikedPanelDes.add(switchButtonExplore);
                savedAndLikedPanelDes.add(switchButtonProfile);
                profilePanel.setVisible(false);
                explorePanel.setVisible(false);
                profilePanelDes.setVisible(false);
                MYpPanel.setVisible(false);
                MYpPanelFF.setVisible(false);
                savedAndLikedPanelDes.setVisible(true);
                add(savedAndLikedPanelDes);
                remove(explorePanel);
                remove(profilePanel);
                remove(MYpPanel);
                revalidate();
                repaint();
            }
        });
    }

    private void initializeProfilePanelDes() {
        profilePanelDes = new JPanel(null);
        profilePanelDes.setOpaque(true);
        profilePanelDes.setBounds(0, 0, getWidth(), getHeight());

        userNameFieldDes=new JTextField();
        userNameFieldDes.setBounds(50,10,500,30);
        userNameFieldDes.setEditable(false);
        userNameFieldDes.setFont(new Font("Tahoma",Font.BOLD,15));
        userNameFieldDes.setBackground(new Color(1, 13, 80));
        userNameFieldDes.setEditable(false);
        userNameFieldDes.setFocusable(false);
        userNameFieldDes.setForeground(Color.GREEN);
        userNameFieldDes.setBackground(Color.black);
        profilePanelDes.add(userNameFieldDes,LEFT_ALIGNMENT);

        bioFieldDes=new JTextField();
        bioFieldDes.setBounds(700,10,500,30);
        bioFieldDes.setEditable(false);
        bioFieldDes.setFont(new Font("Tahoma",Font.BOLD,15));
        bioFieldDes.setBackground(new Color(1, 13, 80));
        bioFieldDes.setEditable(false);
        bioFieldDes.setFocusable(false);
        bioFieldDes.setForeground(Color.GREEN);
        bioFieldDes.setBackground(Color.black);
        profilePanelDes.add(bioFieldDes,LEFT_ALIGNMENT);

        nameFieldDes=new JTextField();
        nameFieldDes.setBounds(700,60,500,30);
        nameFieldDes.setEditable(false);
        nameFieldDes.setFont(new Font("Tahoma",Font.BOLD,15));
        nameFieldDes.setBackground(new Color(1, 13, 80));
        nameFieldDes.setEditable(false);
        nameFieldDes.setFocusable(false);
        nameFieldDes.setForeground(Color.GREEN);
        nameFieldDes.setBackground(Color.black);
        profilePanelDes.add(nameFieldDes,LEFT_ALIGNMENT);

        followersFieldDes = new JLabel();
        followersFieldDes.setBounds(250, 50, 100, 30);
        profilePanelDes.add(followersFieldDes);

        followingFieldDes = new JLabel();
        followingFieldDes.setBounds(250, 100, 100, 30);
        profilePanelDes.add(followingFieldDes);

        myPostsFieldDes = new JLabel();
        myPostsFieldDes.setBounds(250, 150, 100, 30);
        profilePanelDes.add(myPostsFieldDes);

        viewFollowersButtonDes = new JButton("Followers");
        viewFollowersButtonDes.setBounds(50, 50, 150, 30);
        viewFollowersButtonDes.setFocusable(false);
        viewFollowersButtonDes.setForeground(Color.GREEN);
        viewFollowersButtonDes.setBackground(Color.black);
        profilePanelDes.add(viewFollowersButtonDes);

        viewFollowingButtonDes = new JButton("Following");
        viewFollowingButtonDes.setBounds(50, 100, 150, 30);
        viewFollowingButtonDes.setFocusable(false);
        viewFollowingButtonDes.setForeground(Color.GREEN);
        viewFollowingButtonDes.setBackground(Color.black);
        profilePanelDes.add(viewFollowingButtonDes);

        viewMyPostsButtonDes = new JButton("Posts");
        viewMyPostsButtonDes.setBounds(50, 150, 150, 30);
        viewMyPostsButtonDes.setFocusable(false);
        viewMyPostsButtonDes.setForeground(Color.GREEN);
        viewMyPostsButtonDes.setBackground(Color.black);
        profilePanelDes.add(viewMyPostsButtonDes);

        viewFollowOptionDes = new JButton();
        viewFollowOptionDes.setText("Follow");
        viewFollowOptionDes.setBounds(50, 200, 150, 30);
        viewFollowOptionDes.setFocusable(false);
        viewFollowOptionDes.setForeground(Color.GREEN);
        viewFollowOptionDes.setBackground(Color.black);
        profilePanelDes.add(viewFollowOptionDes);

        profilePanelDes.setBackground(new Color(1, 13, 80));
    }
    private void initializeExplorePanel() {
        explorePanel = new JPanel(null);
        explorePanel.setOpaque(true);
        explorePanel.setBackground(new Color(1, 13, 80));
        explorePanel.setBounds(0, 0, getWidth(), getHeight());

        postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        postsPanel.setBackground(new Color(1, 13, 80));
        scrollPane = new JScrollPane(postsPanel);
        scrollPane.setBounds(50, 50, getWidth() - 100, getHeight() - 200);
        explorePanel.add(scrollPane);

        reloadButton = new JButton("Reload");
        reloadButton.setBounds(getWidth() - 230, getHeight() - 130, SEND_BUTTON_WIDTH + 5, SEND_BUTTON_HEIGHT);
        reloadButton.addActionListener(e -> {
               getUpdate();
        });
         FollowingExploreButton= new JButton("My-Ex");
        FollowingExploreButton.setBounds((getWidth() - SWITCH_BUTTON_WIDTH) / 2 + 195, getHeight() - 95, SWITCH_BUTTON_WIDTH, SWITCH_BUTTON_HEIGHT);
        FollowingExploreButton.setForeground(Color.GREEN);
        FollowingExploreButton.setFocusable(false);
        FollowingExploreButton.setBackground(Color.BLACK);
        FollowingExploreButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        FollowingExploreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postsPanel.removeAll();
                for (int i = 0; i < Client.allTweets.size(); i++) {
                    boolean temp=false;
                    for (int j = 0; j <Client.loggedinUser.getFollowings().size(); j++) {
                        if(Client.loggedinUser.getFollowings().get(j).equals(Client.allTweets.get(i).getUsername())){
                            temp=true;
                            break;
                        }
                    }
                    if(temp){
                    Tweet tweet = Client.allTweets.get(i);
                    if (tweet != null && !tweet.getBody().equals("") && tweet.isExplorable()) {
                        String text = "Tweet id : " + tweet.getId() + "        Likes : " + tweet.getLike().size() + "\n"+"Full name: "+tweet.getName()+"\n"+tweet.getUsername() + " : " + tweet.getBody() + "\n\n";
                        ReloadPosts(text);
                     }
                    }
                }
                postsPanel.revalidate();
               postsPanel.repaint();
            }
        });
        explorePanel.add(FollowingExploreButton);




        explorePanel.add(reloadButton);

        JLabel labelX = new JLabel(new ImageIcon("images.png"));
        labelX.setOpaque(true);
        labelX.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        labelX.setBounds(scrollPane.getWidth() + 100, 100, 500, 500);
        explorePanel.add(labelX);
    }
    private void initializeSaveLikedPanel() {
        savedAndLikedPanelDes = new JPanel(null);
        savedAndLikedPanelDes.setOpaque(true);
        savedAndLikedPanelDes.setBackground(new Color(1, 13, 80));
        savedAndLikedPanelDes.setBounds(0, 0, getWidth(), getHeight());

        postsPanelDes = new JPanel();
        postsPanelDes.setLayout(new BoxLayout(postsPanelDes, BoxLayout.Y_AXIS));
        postsPanelDes.setBackground(new Color(1, 13, 80));
        scrollPaneDes = new JScrollPane(postsPanelDes);
        scrollPaneDes.setBounds(50, 50, getWidth() - 100, getHeight() - 200);
        savedAndLikedPanelDes.add(scrollPaneDes);
    }
    private void initializeFFpanel() {
        MYpPanelFF = new JPanel(null);
        MYpPanelFF.setOpaque(true);
        MYpPanelFF.setBackground(new Color(1, 13, 80));
        MYpPanelFF.setBounds(0, 0, getWidth(), getHeight());

        postsPanelPFF = new JPanel();
        postsPanelPFF.setLayout(new BoxLayout(postsPanelPFF, BoxLayout.Y_AXIS));
        postsPanelPFF.setBackground(new Color(1, 13, 80));
        scrollPanePFF = new JScrollPane(postsPanelPFF);
        scrollPanePFF.setBounds(50, 50, getWidth() - 100, getHeight() - 200);
        MYpPanelFF.add(scrollPanePFF);

        JLabel labelX = new JLabel(new ImageIcon("images.png"));
        labelX.setOpaque(true);
        labelX.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        labelX.setBounds(scrollPanePFF.getWidth() + 100, 100, 500, 500);
        MYpPanelFF.add(labelX);
    }
    private void initializeMyPostsPanel() {
        MYpPanel = new JPanel(null);
        MYpPanel.setOpaque(true);
        MYpPanel.setBackground(new Color(1, 13, 80));
        MYpPanel.setBounds(0, 0, getWidth(), getHeight());

        postsPanelP = new JPanel();
        postsPanelP.setLayout(new BoxLayout(postsPanelP, BoxLayout.Y_AXIS));
        postsPanelP.setBackground(new Color(1, 13, 80));
        scrollPaneP = new JScrollPane(postsPanelP);
        scrollPaneP.setBounds(50, 50, getWidth() - 100, getHeight() - 200);
        MYpPanel.add(scrollPaneP);

        inputFieldP = new JTextField();
        inputFieldP.setBounds(50, getHeight() - 130, getWidth() - 300, INPUT_FIELD_HEIGHT);
        inputFieldP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendButton.doClick();
                }
            }
        });
        MYpPanel.add(inputFieldP);

        sendButton = new JButton("send");
        sendButton.setBounds(getWidth() - 230, getHeight() - 130, SEND_BUTTON_WIDTH + 5, SEND_BUTTON_HEIGHT);
        sendButton.addActionListener(e -> {
            String text = inputFieldP.getText();
            if (text.isEmpty()|| text==null || text.trim().equals("")){
                JOptionPane.showMessageDialog(null, "Body cannot be empty");
                return;
            }
            Tweet madeTweet;
                boolean exploreable=false;
            String[] ConnectionLostdialogButtons = {"Yes", "No"};
            int ClientResponse = JOptionPane.showOptionDialog(null
                    , "Do you want to make this post public?"
                    , "Public or Private Post "
                    , JOptionPane.YES_NO_OPTION
                    , INFORMATION_MESSAGE
                    , null
                    , ConnectionLostdialogButtons
                    , 0);

            if (ClientResponse == JOptionPane.YES_OPTION) {
               exploreable=true;
            }
            boolean saveAbleByAll=false;
             ClientResponse = JOptionPane.showOptionDialog(null
                    , "Do you want to make this post saveAble for public?"
                    , "Public or Private saving Option "
                    , JOptionPane.YES_NO_OPTION
                    , INFORMATION_MESSAGE
                    , null
                    , ConnectionLostdialogButtons
                    , 0);

            if (ClientResponse == JOptionPane.YES_OPTION) {
                saveAbleByAll=true;
            }
                madeTweet=new Tweet(Client.allTweets.size(),text,Client.loggedinUser.getUsername(),Client.loggedinUser.getName(),true,exploreable,saveAbleByAll);
                    madeTweet.setBody(text);
            try {
                Client.objectOutputStream.writeUTF("newTweet");
                Client.objectOutputStream.flush();
                Client.objectOutputStream.writeUTF(gson.toJson(madeTweet));
                Client.objectOutputStream.flush();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            };
                    inputFieldP.setText("");
                    getUpdate();
                    JOptionPane.showMessageDialog(null,"Post Added successfully");
        });
        MYpPanel.add(sendButton);
        JLabel labelX = new JLabel(new ImageIcon("images.png"));
        labelX.setOpaque(true);
        labelX.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        labelX.setBounds(scrollPaneP.getWidth() + 100, 100, 500, 500);
        MYpPanel.add(labelX);
    }

    public void addToMyPosts(String text, boolean isCommentable) {
        JPanel postPanelP = new JPanel();
        postPanelP.setLayout(new BorderLayout());
        postPanelP.setBackground(Color.black);

        JPanel buttonPanelP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanelP.setBackground(new Color(1, 13, 80));

        JButton editButtonP = new JButton("Edit");
        JButton deleteButtonP = new JButton("Delete");
        JButton commentableButtonP = new JButton("Commentable");
        commentableButtonP.setText(isCommentable ? "Commentable" : "Uncommentable");
        JTextArea textAreaP = new JTextArea(text);
        textAreaP.setBackground(Color.BLACK);
        textAreaP.setForeground(Color.GREEN);
        textAreaP.setFont(new Font("Tahoma", Font.BOLD, 15));
        textAreaP.setEditable(false);
        postPanelP.add(textAreaP, BorderLayout.CENTER);
        editButtonP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPostText = showInputDialog("Write your new Text", "");
                try {
                    Client.objectOutputStream.writeUTF("editPost");
                    Client.objectOutputStream.flush();
                    Client.objectOutputStream.writeInt(TweetId(textAreaP.getText()));
                    Client.objectOutputStream.flush();
                    Client.objectOutputStream.writeUTF(newPostText);
                    Client.objectOutputStream.flush();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                getUpdate();
                switchButtonExplore.doClick();

            }
        });

        deleteButtonP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int clientResponse = showConfirmDialog(null,
                        "Are you sure to delete this post?",
                        "Post Deletion Confirmation",
                        YES_NO_OPTION,
                        ERROR_MESSAGE);

                if (clientResponse == YES_OPTION) {
                    try {
                        Client.objectOutputStream.writeUTF("deletePost");
                        Client.objectOutputStream.flush();
                        Client.objectOutputStream.writeInt(TweetId(textAreaP.getText()));
                        Client.objectOutputStream.flush();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    getUpdate();
                    showMessageDialog(null, "Post deleted successfully");
                }
            }
        });

        commentableButtonP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean commentable=isCommentable;
                commentable = !commentable;
                try {
                    Client.objectOutputStream.writeUTF("setCommentAble");
                    Client.objectOutputStream.flush();
                    Client.objectOutputStream.writeInt(TweetId(textAreaP.getText()));
                    Client.objectOutputStream.flush();
                    Client.objectOutputStream.writeBoolean(commentable);
                    Client.objectOutputStream.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                commentableButtonP.setText(commentable ? "Commentable" : "Uncommentable");
                getUpdate();
            }
        });


        buttonPanelP.add(editButtonP);
        buttonPanelP.add(deleteButtonP);
        buttonPanelP.add(commentableButtonP);
        buttonPanelP.setBackground(Color.black);

        postPanelP.add(buttonPanelP, BorderLayout.EAST);

        postPanelsP.add(postPanelP);
        postsPanelP.add(postPanelP);
        postsPanelP.revalidate();
        postsPanelP.repaint();
    }

    public void addToMyFF(String text,String title){
        JPanel postPanelPFF = new JPanel();
        postPanelPFF.setLayout(new BorderLayout());
        postPanelPFF.setBackground(Color.black);

        JPanel buttonPanelPFF = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanelPFF.setBackground(new Color(1, 13, 80));

         profileButtonPFF = new JButton("Profile");
        JButton deleteButtonPFF = new JButton("Delete");

        JTextArea textAreaPFF = new JTextArea(text);
        textAreaPFF.setBackground(Color.BLACK);
        textAreaPFF.setForeground(Color.GREEN);
        textAreaPFF.setFont(new Font("Tahoma", Font.BOLD, 15));
        textAreaPFF.setEditable(false);
        postPanelPFF.add(textAreaPFF, BorderLayout.CENTER);

        deleteButtonPFF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int clientResponse = showConfirmDialog(null,
                        "Are you sure to delete this user?"
                        ,title+" deletion ",
                        YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE);

                if (clientResponse == YES_OPTION) {
                    String tempStr;
                    if (title.equals("FOLLOWERS"))
                        tempStr="removeFollower";
                    else
                        tempStr="UnFollow";
                    try {
                        Client.objectOutputStream.writeUTF(tempStr);
                        Client.objectOutputStream.flush();
                        Client.objectOutputStream.writeUTF(textAreaPFF.getText());
                        Client.objectOutputStream.flush();
                        getUpdate();
                        revalidate();
                        repaint();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(null, "user removed successfully");
                }
            }
        });



        buttonPanelPFF.add(profileButtonPFF);
        buttonPanelPFF.add(deleteButtonPFF);
        buttonPanelPFF.setBackground(Color.black);

        profileButtonPFF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isThisMyAccUser(textAreaPFF.getText())){
                    user DestinationUser;
                    try {
                        MYpPanel.setVisible(false);
                        profilePanel.setVisible(false);
                        MYpPanelFF.setVisible(false);
                        explorePanel.setVisible(false);
                        profilePanelDes.setVisible(true);
                        profilePanelDes.add(switchButtonExplore);
                        profilePanelDes.add(switchButtonProfile);
                        add(profilePanelDes);
                        revalidate();
                        repaint();
                        Client.objectOutputStream.writeUTF("getDesAccountUser");
                        Client.objectOutputStream.flush();
                        Client.objectOutputStream.writeUTF(AccName(textAreaPFF.getText()));
                        Client.objectOutputStream.flush();
                        DestinationUser = gson.fromJson(Client.objectInputStream.readUTF(), user.class);
                        watchPeople(DestinationUser);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"Access your profile from the button below the page");
            }
        });
        if(!title.equals(text))
            postPanelPFF.add(buttonPanelPFF, BorderLayout.EAST);

        postPanelsPFF.add(postPanelPFF);
        postsPanelPFF.add(postPanelPFF);
        postsPanelPFF.revalidate();
        postsPanelPFF.repaint();
    }
    public void addToMyFFDes(String text,String title){
        JPanel postPanelPFF = new JPanel();
        postPanelPFF.setLayout(new BorderLayout());
        postPanelPFF.setBackground(Color.black);

        JPanel buttonPanelPFF = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanelPFF.setBackground(new Color(1, 13, 80));

        profileButtonPFF = new JButton("Profile");


        JTextArea textAreaPFF = new JTextArea(text);
        textAreaPFF.setBackground(Color.BLACK);
        textAreaPFF.setForeground(Color.GREEN);
        textAreaPFF.setFont(new Font("Tahoma", Font.BOLD, 15));
        textAreaPFF.setEditable(false);
        postPanelPFF.add(textAreaPFF, BorderLayout.CENTER);

        buttonPanelPFF.add(profileButtonPFF);
        buttonPanelPFF.setBackground(Color.black);
        profileButtonPFF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isThisMyAccUser(textAreaPFF.getText())){
                    user DestinationUser;
                    try {
                        MYpPanel.setVisible(false);
                        profilePanel.setVisible(false);
                        MYpPanelFF.setVisible(false);
                        explorePanel.setVisible(false);
                        profilePanelDes.setVisible(true);
                        profilePanelDes.add(switchButtonExplore);
                        profilePanelDes.add(switchButtonProfile);
                        add(profilePanelDes);
                        revalidate();
                        repaint();
                        Client.objectOutputStream.writeUTF("getDesAccountUser");
                        Client.objectOutputStream.flush();
                        Client.objectOutputStream.writeUTF(AccName(textAreaPFF.getText()));
                        Client.objectOutputStream.flush();
                        DestinationUser = gson.fromJson(Client.objectInputStream.readUTF(), user.class);
                        watchPeople(DestinationUser);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"Access your profile from the button below the page");
            }
        });
        if(!title.equals(text))
            postPanelPFF.add(buttonPanelPFF, BorderLayout.EAST);

        postPanelsPFF.add(postPanelPFF);
        postsPanelPFF.add(postPanelPFF);
        postsPanelPFF.revalidate();
        postsPanelPFF.repaint();
    }

    private String AccName(String text) {
        int i= text.indexOf('#');

        if(i!=0)
        return text.substring(0,i);

        else
            return text;
    }


    private void initializeSwitchButton() {

        switchButtonProfile = new JButton("Switch to Profile");
        switchButtonProfile.setBounds((getWidth() - SWITCH_BUTTON_WIDTH) / 2 + 400, getHeight() - 95, SWITCH_BUTTON_WIDTH, SWITCH_BUTTON_HEIGHT);
        switchButtonProfile.setForeground(Color.GREEN);
        switchButtonProfile.setFocusable(false);
        switchButtonProfile.setBackground(Color.BLACK);
        switchButtonProfile.setFont(new Font("Tahoma", Font.PLAIN, 15));
        explorePanel.add(switchButtonProfile);

        switchButtonExplore = new JButton("Switch to Explore");
        switchButtonExplore.setBounds((getWidth() - SWITCH_BUTTON_WIDTH) / 2 + SWITCH_BUTTON_WIDTH + 405, getHeight() - 95, SWITCH_BUTTON_WIDTH, SWITCH_BUTTON_HEIGHT);
        switchButtonExplore.setForeground(Color.GREEN);
        switchButtonExplore.setFocusable(false);
        switchButtonExplore.setBackground(Color.BLACK);
        switchButtonExplore.setFont(new Font("Tahoma", Font.PLAIN, 15));
        explorePanel.add(switchButtonExplore);
        addMainMenuListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == switchButtonExplore) {
                    explorePanel.add(reloadButton);
                    explorePanel.add(switchButtonExplore);
                    explorePanel.add(switchButtonProfile);
                    MYpPanel.setVisible(false);
                    profilePanelDes.setVisible(false);
                    profilePanel.setVisible(false);
                    MYpPanelFF.setVisible(false);
                    savedAndLikedPanelDes.setVisible(false);
                    explorePanel.setVisible(true);
                    add(explorePanel);
                    remove(MYpPanel);
                    remove(profilePanel);
                    getUpdate();

                    reloadButton.doClick();
                } else if (e.getSource() == switchButtonProfile) {
                    profilePanel.add(reloadButton);
                    profilePanel.add(switchButtonExplore);
                    profilePanel.add(switchButtonProfile);
                    MYpPanel.setVisible(false);
                    profilePanelDes.setVisible(false);
                    explorePanel.setVisible(false);
                    MYpPanelFF.setVisible(false);
                    savedAndLikedPanelDes.setVisible(false);
                    profilePanel.setVisible(true);
                    add(profilePanel);
                    remove(MYpPanel);
                    remove(explorePanel);
                    getUpdate();

                } else if (e.getSource() == viewMyPostsButton) {
                    MYpPanel.add(reloadButton);
                    MYpPanel.add(switchButtonExplore);
                    MYpPanel.add(switchButtonProfile);
                    profilePanel.setVisible(false);
                    explorePanel.setVisible(false);
                    profilePanelDes.setVisible(false);
                    MYpPanelFF.setVisible(false);
                    savedAndLikedPanelDes.setVisible(false);
                    MYpPanel.setVisible(true);
                    add(MYpPanel);
                    remove(explorePanel);
                    remove(profilePanel);
                }
                else if (e.getSource() == viewFollowersButton) {
                    getUpdate();
                    for(int i=-1; i<Client.loggedinUser.getFollowers().size();i++){
                        if(i!=-1)
                        addToMyFF(Client.loggedinUser.getFollowers().get(i),"FOLLOWERS");
                        else
                            addToMyFF("FOLLOWERS","FOLLOWERS");
                    }
                    MYpPanelFF.add(reloadButton);
                    MYpPanelFF.add(switchButtonExplore);
                    MYpPanelFF.add(switchButtonProfile);
                    profilePanel.setVisible(false);
                    explorePanel.setVisible(false);
                    MYpPanel.setVisible(false);
                    profilePanelDes.setVisible(false);
                    savedAndLikedPanelDes.setVisible(false);
                    MYpPanelFF.setVisible(true);
                    add(MYpPanelFF);
                    remove(explorePanel);
                    remove(profilePanel);
                    remove(MYpPanel);
                }
                else if (e.getSource() == viewFollowingButton) {
                    getUpdate();
                    for(int i=-1; i<Client.loggedinUser.getFollowings().size();i++){
                        if(i!=-1)
                        addToMyFF(Client.loggedinUser.getFollowings().get(i),"FOLLOWING");
                        else
                            addToMyFF("FOLLOWING","FOLLOWING");
                    }
                    MYpPanelFF.add(reloadButton);
                    MYpPanelFF.add(switchButtonExplore);
                    MYpPanelFF.add(switchButtonProfile);
                    profilePanel.setVisible(false);
                    explorePanel.setVisible(false);
                    profilePanelDes.setVisible(false);
                    savedAndLikedPanelDes.setVisible(false);
                    MYpPanel.setVisible(false);
                    MYpPanelFF.setVisible(true);
                    add(MYpPanelFF);
                    remove(explorePanel);
                    remove(profilePanel);
                    remove(MYpPanel);
                }
                else if (e.getSource() == viewSavedPostsButton) {

                    getUpdate();
                    for(int i=-1; i<Client.loggedinUser.getSavedPosts().size();i++){
                        if(i!=-1) {
                            String text = "Tweet id : " + Client.loggedinUser.getSavedPosts().get(i).getId()
                                    + "        Likes : " + Client.loggedinUser.getSavedPosts().get(i).getLike().size()+"\n"+
                               "Full name: "+Client.loggedinUser.getSavedPosts().get(i).getName()+"\n"+
                                    Client.loggedinUser.getSavedPosts().get(i).getUsername()
                                        + " : " + Client.loggedinUser.getSavedPosts().get(i).getBody() + "\n\n";
                            SavedAndLikedPage(text,"SAVED");
                        }

                        else {
                            SavedAndLikedPage("SAVED", "SAVED");

                        }
                    }
                    savedAndLikedPanelDes.add(reloadButton);
                    savedAndLikedPanelDes.add(switchButtonExplore);
                    savedAndLikedPanelDes.add(switchButtonProfile);
                    profilePanel.setVisible(false);
                    explorePanel.setVisible(false);
                    profilePanelDes.setVisible(false);
                    MYpPanel.setVisible(false);
                    MYpPanelFF.setVisible(false);
                    savedAndLikedPanelDes.setVisible(true);
                    add(savedAndLikedPanelDes);
                    remove(explorePanel);
                    remove(profilePanel);
                    remove(MYpPanel);
                }
                else if (e.getSource() == viewLikedPostsButton) {
                    getUpdate();
                    for(int i=-1; i<Client.loggedinUser.getLikedPosts().size();i++){
                        if(i!=-1) {
                            String text = "Tweet id : " + Client.loggedinUser.getLikedPosts().get(i).getId()
                                    + "        Likes : " + Client.loggedinUser.getLikedPosts().get(i).getLike().size()+"\n"+
                                    "Full name: "+Client.loggedinUser.getLikedPosts().get(i).getName()+"\n"+
                                      Client.loggedinUser.getLikedPosts().get(i).getUsername()
                                    + " : " + Client.loggedinUser.getLikedPosts().get(i).getBody() + "\n\n";
                            SavedAndLikedPage(text,"LIKED");
                        }

                        else {
                            SavedAndLikedPage("Liked", "Liked");

                        }
                    }
                    savedAndLikedPanelDes.add(reloadButton);
                    savedAndLikedPanelDes.add(switchButtonExplore);
                    savedAndLikedPanelDes.add(switchButtonProfile);
                    profilePanel.setVisible(false);
                    explorePanel.setVisible(false);
                    profilePanelDes.setVisible(false);
                    MYpPanel.setVisible(false);
                    MYpPanelFF.setVisible(false);
                    savedAndLikedPanelDes.setVisible(true);
                    add(savedAndLikedPanelDes);
                    remove(explorePanel);
                    remove(profilePanel);
                    remove(MYpPanel);
                }

                revalidate();
                repaint();
            }
        });
    }

    public void addMainMenuListener(ActionListener listener) {
        switchButtonExplore.addActionListener(listener);
        switchButtonProfile.addActionListener(listener);
        viewMyPostsButton.addActionListener(listener);
        viewFollowingButton.addActionListener(listener);
        viewFollowersButton.addActionListener(listener);
        viewLikedPostsButton.addActionListener(listener);
        viewSavedPostsButton.addActionListener(listener);
        profileButton.addActionListener(listener);
        profileButtonPFF.addActionListener(listener);
    }

    public void ReloadPosts(String text) {
        JPanel postPanel = new JPanel();
        postPanel.setLayout((new BorderLayout()));
        postPanel.setBackground(Color.black);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(1, 13, 80));

         profileButton = new JButton("Profile");
        JButton likeButton = new JButton("Like");
        JButton saveButton = new JButton("Save");
        JButton commentButton = new JButton("Comment");

        likeButton.setText("Like");

        JTextArea textArea = new JTextArea(text);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.GREEN);
        textArea.setFont(new Font("Tahoma", Font.BOLD, 15));
        textArea.setEditable(false);
        postPanel.add(textArea, BorderLayout.CENTER);
        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Client.objectOutputStream.writeUTF("addComment");
                    Client.objectOutputStream.flush();
                    Client.objectOutputStream.writeInt(TweetId(textArea.getText()));
                    Client.objectOutputStream.flush();
                    Boolean b=Client.objectInputStream.readBoolean();
                    if(!b) {
                        showMessageDialog(null,"this post cant be commented");
                        return;
                    }
                    String comment = showInputDialog("Write your comment");
                    Client.objectOutputStream.writeUTF(comment);
                    Client.objectOutputStream.flush();
                    String ServerResponse=Client.objectInputStream.readUTF();
                    if (ServerResponse.equals("null")) {
                        showMessageDialog(null,"comment cant be null or empty");
                        return;
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                getUpdate();
                switchButtonExplore.doClick();


            }
        });
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isThisMyAcc(textArea.getText())){
                    user DestinationUser;
                try {
                    MYpPanel.setVisible(false);
                    profilePanel.setVisible(false);
                    MYpPanelFF.setVisible(false);
                    explorePanel.setVisible(false);
                    profilePanelDes.setVisible(true);
                    profilePanelDes.add(switchButtonExplore);
                    profilePanelDes.add(switchButtonProfile);
                    add(profilePanelDes);
                    revalidate();
                    repaint();
                    Client.objectOutputStream.writeUTF("getDesAccount");
                    Client.objectOutputStream.flush();
                    Client.objectOutputStream.writeInt(TweetId(textArea.getText()));
                    Client.objectOutputStream.flush();
                    DestinationUser = gson.fromJson(Client.objectInputStream.readUTF(), user.class);
                    watchPeople(DestinationUser);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
              }
                else
                    JOptionPane.showMessageDialog(null,"Access your profile from the button below the page");
            }
        });
        likeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isThisMyAcc(textArea.getText())) {
                    try {
                        Client.objectOutputStream.writeUTF("UpdateLike");
                        Client.objectOutputStream.flush();
                        Client.objectOutputStream.writeInt(TweetId(textArea.getText()));
                        Client.objectOutputStream.flush();
                        getUpdate();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isThisMyAcc(textArea.getText())) {
                    try {
                        Client.objectOutputStream.writeUTF("UpdateSave");
                        Client.objectOutputStream.flush();
                        Client.objectOutputStream.writeInt(TweetId(textArea.getText()));
                        Client.objectOutputStream.flush();

                        boolean responseForSaveAbleForAll=true;
                        responseForSaveAbleForAll=Client.objectInputStream.readBoolean();
                        if(!responseForSaveAbleForAll){
                            showMessageDialog(null,"this post is restricted for being saved only for followers");
                        }
                        getUpdate();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        buttonPanel.add(profileButton);
        buttonPanel.add(likeButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(commentButton);
        buttonPanel.setBackground(Color.black);

        postPanel.add(buttonPanel, BorderLayout.EAST);

        postPanels.add(postPanel);
        postsPanel.add(postPanel);
        postsPanel.revalidate();
        postsPanel.repaint();
    }
    public void SavedAndLikedPage(String text,String text2) {
        JPanel postPanelDes = new JPanel();
        postPanelDes.setLayout((new BorderLayout()));
        postPanelDes.setBackground(Color.black);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(1, 13, 80));
        JButton deleteSLDes = null;
        if(text!=text2) {
            profileButton = new JButton("Profile");
             deleteSLDes = new JButton("Delete");
            buttonPanel.add(profileButton);
            buttonPanel.add(deleteSLDes);
        }
        JTextArea textAreaDes = new JTextArea(text);
        textAreaDes.setBackground(Color.BLACK);
        textAreaDes.setForeground(Color.GREEN);
        textAreaDes.setFont(new Font("Tahoma", Font.BOLD, 15));
        textAreaDes.setEditable(false);
        postsPanelDes.add(textAreaDes, BorderLayout.CENTER);
        if(text2.equals("SAVED") && !text.equals("SAVED")){
            deleteSLDes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isThisMyAcc(textAreaDes.getText())) {
                        try {
                            Client.objectOutputStream.writeUTF("UpdateSave");
                            Client.objectOutputStream.flush();
                            Client.objectOutputStream.writeInt(TweetId(textAreaDes.getText()));
                            Client.objectOutputStream.flush();
                            showMessageDialog(null,"post deleted from saved posts");
                            getUpdate();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
        }
        if(text2.equals("LIKED") && !text.equals("LIKED")){
            deleteSLDes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isThisMyAcc(textAreaDes.getText())) {
                        try {
                            Client.objectOutputStream.writeUTF("UpdateLike");
                            Client.objectOutputStream.flush();
                            Client.objectOutputStream.writeInt(TweetId(textAreaDes.getText()));
                            Client.objectOutputStream.flush();
                            showMessageDialog(null,"post deleted from liked posts");
                            getUpdate();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
        }
        if(text2.equals("SEARCHED") && !text.equals("SEARCHED")){
            deleteSLDes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isThisMyAccUser(textAreaDes.getText())) {
                        try {
                            Client.objectOutputStream.writeUTF("deleteFromSearch");
                            Client.objectOutputStream.flush();
                            Client.objectOutputStream.writeUTF(textAreaDes.getText().trim());
                            Client.objectOutputStream.flush();
                            showMessageDialog(null,"User successfully deleted from search History");
                            getUpdate();
                            searchBox.doClick();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
        }
        if(!text2.equals("SEARCHED")) {
            profileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!isThisMyAcc(textAreaDes.getText())) {
                        user DestinationUser;

                        try {
                            MYpPanel.setVisible(false);
                            profilePanel.setVisible(false);
                            MYpPanelFF.setVisible(false);
                            explorePanel.setVisible(false);
                            postsPanelDes.setVisible(false);
                            savedAndLikedPanelDes.setVisible(false);
                            profilePanelDes.setVisible(true);
                            profilePanelDes.add(switchButtonExplore);
                            profilePanelDes.add(switchButtonProfile);
                            add(profilePanelDes);
                            revalidate();
                            repaint();
                            Client.objectOutputStream.writeUTF("getDesAccount");
                            Client.objectOutputStream.flush();
                            Client.objectOutputStream.writeInt(TweetId(textAreaDes.getText()));
                            Client.objectOutputStream.flush();
                            DestinationUser = gson.fromJson(Client.objectInputStream.readUTF(), user.class);
                            watchPeople(DestinationUser);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Access your profile from the button below the page");
                }
            });
        }
        if(text2.equals("SEARCHED")) {
            profileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!isThisMyAccUser(textAreaDes.getText())) {
                        user DestinationUser;

                        try {
                            MYpPanel.setVisible(false);
                            profilePanel.setVisible(false);
                            MYpPanelFF.setVisible(false);
                            explorePanel.setVisible(false);
                            postsPanelDes.setVisible(false);
                            savedAndLikedPanelDes.setVisible(false);
                            profilePanelDes.setVisible(true);
                            profilePanelDes.add(switchButtonExplore);
                            profilePanelDes.add(switchButtonProfile);
                            add(profilePanelDes);
                            revalidate();
                            repaint();
                            Client.objectOutputStream.writeUTF("getDesAccountUser");
                            Client.objectOutputStream.flush();
                            Client.objectOutputStream.writeUTF(AccName(textAreaDes.getText().trim()));
                            Client.objectOutputStream.flush();
                            DestinationUser = gson.fromJson(Client.objectInputStream.readUTF(), user.class);
                            watchPeople(DestinationUser);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Access your profile from the button below the page");
                }
            });
        }

        buttonPanel.setBackground(Color.black);

        postsPanelDes.add(buttonPanel, BorderLayout.EAST);

        postsPanelDes.add(postPanelDes);
        postsPanelDes.add(postPanelDes);
        postsPanelDes.revalidate();
        postsPanelDes.repaint();
    }

    private Boolean isThisMyAcc(String text) {
        try {
            Client.objectOutputStream.writeUTF("isThisMyAcc");
            Client.objectOutputStream.flush();
            Client.objectOutputStream.writeInt(TweetId(text));
            Client.objectOutputStream.flush();
            return Client.objectInputStream.readBoolean();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private Boolean isThisMyAccUser(String text){
        try {
            Client.objectOutputStream.writeUTF("isThisMyAccUser");
            Client.objectOutputStream.flush();
            Client.objectOutputStream.writeUTF(AccName(text));
            Client.objectOutputStream.flush();
            return Client.objectInputStream.readBoolean();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void watchPeople(user DestinationUser) {
        addDesListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==viewFollowOptionDes){
                    String direction;
                    if(viewFollowOptionDes.getText().equals("Follow")){
                        viewFollowOptionDes.setText("UnFollow");
                        direction="Follow";
                    }
                    else {
                        viewFollowOptionDes.setText("Follow");
                        direction = "UnFollow";
                    }
                    try {
                        Client.objectOutputStream.writeUTF(direction);
                        Client.objectOutputStream.flush();
                        Client.objectOutputStream.writeUTF(DestinationUser.getUsername());
                        Client.objectOutputStream.flush();
                        getUpdate();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if (e.getSource() == viewFollowersButtonDes) {
                    postsPanelPFF.removeAll();
                    for(int i=-1; i<DestinationUser.getFollowers().size();i++){
                        if(i!=-1) {
                            boolean temp=false;
                            for (int j = 0; j < Client.loggedinUser.getFollowings().size(); j++) {
                               if(DestinationUser.getFollowers().get(i).equals(Client.loggedinUser.getFollowings().get(j))) {
                                   temp = true;
                                   break;
                               }
                            }
                            if (temp)
                            addToMyFFDes(DestinationUser.getFollowers().get(i)+"\t#Followed", "FOLLOWERS");
                            else
                                addToMyFFDes(DestinationUser.getFollowers().get(i)+"\t#Not-Followed", "FOLLOWERS");
                        }
                        else
                            addToMyFFDes("FOLLOWERS","FOLLOWERS");
                    }
                    MYpPanelFF.add(reloadButton);
                    MYpPanelFF.add(switchButtonExplore);
                    MYpPanelFF.add(switchButtonProfile);
                    profilePanel.setVisible(false);
                    explorePanel.setVisible(false);
                    MYpPanel.setVisible(false);
                    profilePanelDes.setVisible(false);
                    MYpPanelFF.setVisible(true);
                    add(MYpPanelFF);
                    remove(explorePanel);
                    remove(profilePanel);
                    remove(MYpPanel);
                }
                else if (e.getSource() == viewFollowingButtonDes) {
                    postsPanelPFF.removeAll();
                    for(int i=-1; i<DestinationUser.getFollowings().size();i++){
                        if(i!=-1) {
                            boolean temp=false;
                            for (int j = 0; j < Client.loggedinUser.getFollowings().size(); j++) {
                                if(DestinationUser.getFollowers().get(i).equals(Client.loggedinUser.getFollowings().get(j))) {
                                    temp = true;
                                    break;
                                }
                            }
                            if (temp)
                                addToMyFFDes(DestinationUser.getFollowings().get(i)+"\t#Followed", "FOLLOWING");
                            else
                                addToMyFFDes(DestinationUser.getFollowings().get(i)+"\t#Not-Followed", "FOLLOWING");
                        }
                        else
                            addToMyFFDes("FOLLOWING","FOLLOWING");
                    }
                    MYpPanelFF.add(reloadButton);
                    MYpPanelFF.add(switchButtonExplore);
                    MYpPanelFF.add(switchButtonProfile);
                    profilePanel.setVisible(false);
                    explorePanel.setVisible(false);
                    profilePanelDes.setVisible(false);
                    MYpPanel.setVisible(false);
                    MYpPanelFF.setVisible(true);
                    add(MYpPanelFF);
                    remove(explorePanel);
                    remove(profilePanel);
                    remove(MYpPanel);
                }

            }
        });

        try {
            Client.objectOutputStream.writeUTF("ifFollowed");
            Client.objectOutputStream.flush();
            Client.objectOutputStream.writeUTF(DestinationUser.getUsername());
            Client.objectOutputStream.flush();
           viewFollowOptionDes.setText(Client.objectInputStream.readUTF());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            userNameFieldDes.setText("USERNAME: "+DestinationUser.getUsername());
              bioFieldDes.setText("BIO: "+DestinationUser.getBio());
              nameFieldDes.setText("Full name: "+DestinationUser.getName());
               followingFieldDes.setText(String.valueOf(DestinationUser.getFollowings().size()));
              followersFieldDes.setText(String.valueOf(DestinationUser.getFollowers().size()));
              myPostsFieldDes.setText(String.valueOf(DestinationUser.getAllPost().size()));
              revalidate();
              repaint();


    }

    public void getUpdate() {
        postsPanelDes.removeAll();
        postsPanelP.removeAll();
        postsPanel.removeAll();
        postsPanelPFF.removeAll();
        try {
            Client.objectOutputStream.writeUTF("getUpdate");
            Client.objectOutputStream.flush();
            Client.loggedinUser=gson.fromJson( Client.objectInputStream.readUTF(),user.class);
            Client.allTweets.clear();
            java.lang.reflect.Type tweetListType = new TypeToken<ArrayList<Tweet>>() {}.getType();
            Client.allTweets.addAll(gson.fromJson(Client.objectInputStream.readUTF(),tweetListType));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < Client.loggedinUser.getAllPost().size(); i++) {
            Tweet post = Client.loggedinUser.getPost(i);
            if (!post.getBody().equals("")) {
                addToMyPosts("Tweet id : " + post.getId() + "        Likes : " + post.getLike().size() + "\n" +"Full name: "+post.getName()+"\n"+ post.getUsername() + " : " + post.getBody()+"\n\n", post.getCommentable());
            }
        }

        for (int i = 0; i < Client.allTweets.size(); i++) {
            Tweet tweet = Client.allTweets.get(i);
            if (tweet != null && !tweet.getBody().equals("") && tweet.isExplorable()) {
                String text ="Tweet id : " + tweet.getId() + "        Likes : " + tweet.getLike().size() + "\n"+"Full name: "+tweet.getName()+"\n"+ tweet.getUsername() + " : " + tweet.getBody()+"\n\n";
                ReloadPosts(text);
            }
        }
        usernameField.setText("USERNAME: "+Client.loggedinUser.getUsername());
        bioField.setText("BIO: "+Client.loggedinUser.getBio());
        nameField.setText("Full name: "+Client.loggedinUser.getName());
        followingField.setText(String.valueOf(Client.loggedinUser.getFollowings().size()));
        followersField.setText(String.valueOf(Client.loggedinUser.getFollowers().size()));
        likedPostsField.setText(String.valueOf(Client.loggedinUser.getLikedPosts().size()));
        savedPostsField.setText(String.valueOf(Client.loggedinUser.getSavedPosts().size()));
        myPostsField.setText(String.valueOf(Client.loggedinUser.getAllPost().size()));
    }
 public  void addDesListener(ActionListener listener){
        viewFollowersButtonDes.addActionListener(listener);
        viewFollowingButtonDes.addActionListener(listener);
        viewMyPostsButtonDes.addActionListener(listener);
        viewFollowOptionDes.addActionListener(listener);
 }
    private int TweetId(String str){
        str=str.substring(10,20);
        str=str.trim();
        return Integer.parseInt(str);
    }
}
