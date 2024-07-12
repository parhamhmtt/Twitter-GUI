import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {
    public String loginPanelUserName;
    public String loginPanelPassword;
    private JPanel LoginPanel;
    public JTextField usernametextfield;
    public JPasswordField passwordField;
    public JButton loginbutton;
    public JButton signupbutton;

    public LoginFrame() {
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
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
        setIconImage(new ImageIcon("images.png").getImage());

        setVisible(true);
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
        setLayout(null);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setResizable(false);
        getContentPane().setBackground(new Color(1, 13, 80));

        LoginPanel = new JPanel(null);
        LoginPanel.setOpaque(true);
        LoginPanel.setBackground(Color.WHITE);
        LoginPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));
        int panelWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3;
        int panelHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
        LoginPanel.setBounds(
                (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4,
                panelWidth,
                panelHeight
        );

        JLabel Welcomeback = new JLabel("Welcome Back");
        Welcomeback.setOpaque(true);
        Welcomeback.setForeground(Color.WHITE);
        Welcomeback.setBackground(Color.BLACK);
        Welcomeback.setFont(new Font("Tahoma", Font.BOLD, 20));
        Welcomeback.setBounds(panelWidth / 2 - 75, panelHeight / 2 - 170, 200, 20);
        LoginPanel.add(Welcomeback);

        JLabel usernamelabel = new JLabel("Username");
        usernamelabel.setOpaque(true);
        usernamelabel.setForeground(Color.WHITE);
        usernamelabel.setBackground(Color.BLACK);
        usernamelabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        usernamelabel.setBounds(panelWidth / 2 - 200, panelHeight / 2 - 100, 100, 20);
        LoginPanel.add(usernamelabel);

        usernametextfield = new JTextField();
        usernametextfield.setOpaque(true);
        usernametextfield.setForeground(Color.BLACK);
        usernametextfield.setBounds(panelWidth / 2 - 200, panelHeight / 2 - 70, 400, 40);
        usernametextfield.setFont(new Font("Tahoma", Font.PLAIN, 15));
        usernametextfield.setFocusable(true);
        usernametextfield.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginbutton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        LoginPanel.add(usernametextfield);

        JLabel passwordlabel = new JLabel("Password");
        passwordlabel.setOpaque(true);
        passwordlabel.setForeground(Color.WHITE);
        passwordlabel.setBackground(Color.BLACK);
        passwordlabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordlabel.setBounds(panelWidth / 2 - 200, panelHeight / 2 - 10, 100, 20);
        LoginPanel.add(passwordlabel);

        passwordField = new JPasswordField();
        passwordField.setOpaque(true);
        passwordField.setForeground(Color.BLACK);
        passwordField.setBounds(panelWidth / 2 - 200, panelHeight / 2 + 20, 400, 40);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordField.setFocusable(true);
        passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginbutton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        LoginPanel.add(passwordField);

        loginbutton = new JButton("Login");
        loginbutton.setOpaque(true);
        loginbutton.setForeground(Color.WHITE);
        loginbutton.setBackground(Color.BLACK);
        loginbutton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        loginbutton.setFocusable(false);
        loginbutton.addActionListener(this);
        loginbutton.setBounds(panelWidth / 2 - 200, panelHeight / 2 + 75, 400, 40);
        loginbutton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
        loginbutton.addActionListener(e -> {
        });
        LoginPanel.add(loginbutton);

        JLabel Signuplabel = new JLabel("Don't have an account?", SwingConstants.CENTER);
        Signuplabel.setOpaque(true);
        Signuplabel.setForeground(Color.WHITE);
        Signuplabel.setBackground(Color.BLACK);
        Signuplabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Signuplabel.setBounds(panelWidth / 2 - 200, panelHeight / 2 + 150, 200, 20);
        LoginPanel.add(Signuplabel);

        signupbutton = new JButton("Sign up");
        signupbutton.setOpaque(true);
        signupbutton.setForeground(Color.WHITE);
        signupbutton.setBackground(Color.BLACK);
        signupbutton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        signupbutton.setFocusable(false);
        signupbutton.setBounds(panelWidth / 2, panelHeight / 2 + 150, 100, 20);
        signupbutton.addActionListener(e -> {
        });
        LoginPanel.add(signupbutton);

        JLabel labelX = new JLabel(new ImageIcon("images.png"));
        labelX.setOpaque(true);
        labelX.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        labelX.setBounds(0, 0, panelWidth, panelHeight);
        LoginPanel.add(labelX);

        add(LoginPanel);
        setVisible(false);
    }

    public void addLoginPageListener(ActionListener listener) {
        loginbutton.addActionListener(listener);
        signupbutton.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginbutton){
            loginPanelPassword=passwordField.getText();
            loginPanelUserName=usernametextfield.getText();
        }

    }

}
