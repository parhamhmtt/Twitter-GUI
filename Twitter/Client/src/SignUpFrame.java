import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SignUpFrame extends JFrame implements ActionListener {
    private JPanel SignUpPanel;
    public JTextField nametextfield;
    public JTextField birthDateTextField;
    public JTextField usernametextfield;
    public JPasswordField passwordField;
    public JTextField emailtextfield;
    public JTextField phonenumbertextfield;
    public JTextArea biotextarea;
    public JButton signupbutton;
    public JButton loginbutton;


    public SignUpFrame() {
        setIconImage(new ImageIcon("images.png").getImage());
        setTitle("Twitter");

        setVisible(false);
        getRootPane().setBorder(BorderFactory.createLineBorder(new Color(1, 13, 80), 5, true)); // تنظیم کادر به رنگ زمینه صفحه
        setLayout(null);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setResizable(false);
        getContentPane().setBackground(new Color(1, 13, 80));

        SignUpPanel = new JPanel(null);
        SignUpPanel.setOpaque(true);
        SignUpPanel.setBackground(Color.WHITE);
        SignUpPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));
        int panelWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3;
        int panelHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
        SignUpPanel.setBounds(
                (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 8,
                panelWidth,
                panelHeight
        );

        JLabel signupLabel = new JLabel("Sign Up");
        signupLabel.setOpaque(true);
        signupLabel.setForeground(Color.WHITE); // تغییر رنگ متن به سفید
        signupLabel.setBackground(Color.BLACK); // تغییر رنگ پس‌زمینه به مشکی
        signupLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        signupLabel.setHorizontalAlignment(SwingConstants.CENTER);
        signupLabel.setBounds(panelWidth / 2 - 100, 20, 200, 30);
        SignUpPanel.add(signupLabel);

        JLabel namelabel = new JLabel("Name");
        namelabel.setForeground(Color.WHITE); // تغییر رنگ متن به سفید
        namelabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        namelabel.setBounds(50, 70, 100, 30);
        SignUpPanel.add(namelabel);

        nametextfield = new JTextField();
        nametextfield.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nametextfield.setBounds(150, 70, 200, 30);
        SignUpPanel.add(nametextfield);

        JLabel usernamelabel = new JLabel("Username");
        usernamelabel.setForeground(Color.WHITE); // تغییر رنگ متن به سفید
        usernamelabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        usernamelabel.setBounds(50, 120, 100, 30);
        SignUpPanel.add(usernamelabel);

        usernametextfield = new JTextField();
        usernametextfield.setFont(new Font("Tahoma", Font.PLAIN, 15));
        usernametextfield.setBounds(150, 120, 200, 30);
        SignUpPanel.add(usernametextfield);

        JLabel passwordlabel = new JLabel("Password");
        passwordlabel.setForeground(Color.WHITE); // تغییر رنگ متن به سفید
        passwordlabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordlabel.setBounds(50, 170, 100, 30);
        SignUpPanel.add(passwordlabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordField.setBounds(150, 170, 200, 30);
        SignUpPanel.add(passwordField);

        JLabel emaillabel = new JLabel("Email");
        emaillabel.setForeground(Color.WHITE); // تغییر رنگ متن به سفید
        emaillabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        emaillabel.setBounds(50, 220, 100, 30);
        SignUpPanel.add(emaillabel);

        emailtextfield = new JTextField();
        emailtextfield.setFont(new Font("Tahoma", Font.PLAIN, 15));
        emailtextfield.setBounds(150, 220, 200, 30);
        SignUpPanel.add(emailtextfield);

        JLabel phonelabel = new JLabel("Phone");
        phonelabel.setForeground(Color.WHITE); // تغییر رنگ متن به سفید
        phonelabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        phonelabel.setBounds(50, 270, 100, 30);
        SignUpPanel.add(phonelabel);

        phonenumbertextfield = new JTextField();
        phonenumbertextfield.setFont(new Font("Tahoma", Font.PLAIN, 15));
        phonenumbertextfield.setBounds(150, 270, 200, 30);
        SignUpPanel.add(phonenumbertextfield);

        JLabel birthDateLabel = new JLabel("Birth-Date");
        birthDateLabel.setForeground(Color.WHITE); // تغییر رنگ متن به سفید
        birthDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        birthDateLabel.setBounds(50, 320, 100, 30);
        SignUpPanel.add(birthDateLabel);

        birthDateTextField = new JTextField();
        birthDateTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        birthDateTextField.setBounds(150, 320, 200, 30);
        SignUpPanel.add(birthDateTextField);


        JLabel biolabel = new JLabel("Biography");
        biolabel.setForeground(Color.WHITE); // تغییر رنگ متن به سفید
        biolabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        biolabel.setBounds(50, 370, 200, 50);
        SignUpPanel.add(biolabel);

        biotextarea = new JTextArea();
        biotextarea.setFont(new Font("Tahoma", Font.PLAIN, 15));
        biotextarea.setLineWrap(true);
        biotextarea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(biotextarea);
        scrollPane.setBounds(150, 370, 200, 50 ) ;
        SignUpPanel.add(scrollPane);

        signupbutton = new JButton("Sign up");
        signupbutton.setForeground(Color.WHITE);
        signupbutton.setBackground(Color.BLACK);
        signupbutton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        signupbutton.setBounds(panelWidth / 2+120, panelHeight - 40, 100, 30);
        signupbutton.setFocusable(false);
        signupbutton.addActionListener(e -> {
        });
        SignUpPanel.add(signupbutton);

        loginbutton = new JButton("Log in");
        loginbutton.setForeground(Color.WHITE);
        loginbutton.setBackground(Color.BLACK);
        loginbutton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        loginbutton.setBounds(panelWidth / 2+120, panelHeight - 80, 100, 30);
        loginbutton.setFocusable(false);
        loginbutton.addActionListener(e -> {
        });
        SignUpPanel.add(loginbutton);

        JLabel labelX = new JLabel(new ImageIcon("images.png"));
        labelX.setOpaque(true);
        labelX.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        labelX.setBounds(0, 0, panelWidth, panelHeight);
        SignUpPanel.add(labelX);

        add(SignUpPanel);
        setVisible(false);
    }
    public void addLoginPageListener(ActionListener listener) {
        loginbutton.addActionListener(listener);
        signupbutton.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}

