package com.cloudfoundry.vmc.swing.frame;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.CloudFoundryException;
import org.springframework.web.client.ResourceAccessException;

import com.cloudfoundry.vmc.CloudConstants;
import com.cloudfoundry.vmc.common.Icon;
import com.cloudfoundry.vmc.common.Session;
import com.cloudfoundry.vmc.common.Validation;
import com.cloudfoundry.vmc.core.model.account.User;
import com.cloudfoundry.vmc.swing.component.InputComboBox;
import com.cloudfoundry.vmc.swing.component.Panel;

public class Login extends BaseFrame {
    private static final long serialVersionUID = -8607149290281591829L;

    private JPanel jpLoginPanel;
    private JLabel labTarget;
    private JComboBox comTarget;
    private JCheckBox chkSave;
    private JLabel labEmail;
    private JLabel labPassword;
    private JTextField txtEmail;
    private JPasswordField txtPasswd;

    private JButton btnLogin;
    private JButton btnExit;

    private void initResource() {
        jpLoginPanel = new Panel(CloudConstants.icon.LOGIN);
        jpLoginPanel.setBackground(Color.white);
        labTarget = new JLabel("Target", JLabel.RIGHT);
        comTarget = new JComboBox(new String[] { "https://api.cloudfoundry.com" });
        labEmail = new JLabel("Email", JLabel.RIGHT);
        labPassword = new JLabel("Password", JLabel.RIGHT);
        txtEmail = new JTextField("kai.du@mangocity.com", 10);
        txtPasswd = new JPasswordField("123456", 10);

        chkSave = new JCheckBox("Save", true);
        chkSave.setBackground(Color.white);
        btnLogin = new JButton("Login");
        btnExit = new JButton("Quit");

        initInputComboBox();
    }

    private void initInputComboBox() {
        comTarget.setEditable(true);
        JTextComponent editor = (JTextComponent) comTarget.getEditor().getEditorComponent();
        editor.setDocument(new InputComboBox(comTarget.getModel()));
    }

    private void setBound() {
        jpLoginPanel.setLayout(null);
        jpLoginPanel.setBounds(0, 0, 451, 292);
        labTarget.setBounds(new Rectangle(60, 115, 75, 22));
        comTarget.setBounds(new Rectangle(145, 115, 200, 22));
        labEmail.setBounds(new Rectangle(60, 150, 75, 22));
        txtEmail.setBounds(new Rectangle(145, 150, 200, 22));
        labPassword.setBounds(new Rectangle(60, 185, 75, 22));
        txtPasswd.setBounds(new Rectangle(145, 185, 200, 22));

        chkSave.setBounds(new Rectangle(125, 225, 55, 22));
        btnLogin.setBounds(new Rectangle(180, 225, 70, 25));
        btnExit.setBounds(new Rectangle(260, 225, 70, 25));
    }

    private void setToolTips() {
        txtEmail.setToolTipText("Please input your email");
        txtPasswd.setToolTipText("Please input your password");
    }

    private void initGUI() {
        this.setLayout(null);
        initResource();
        this.setBound();
        this.setToolTips();

        this.add(jpLoginPanel);
        jpLoginPanel.add(labTarget);
        jpLoginPanel.add(comTarget);
        jpLoginPanel.add(labEmail);
        jpLoginPanel.add(txtEmail);
        jpLoginPanel.add(labPassword);
        jpLoginPanel.add(txtPasswd);
        jpLoginPanel.add(chkSave);
        jpLoginPanel.add(btnLogin);
        jpLoginPanel.add(btnExit);

        this.setTitle("Desktop for cloudfoundry vmc");
        this.setIconImage(Icon.IM(CloudConstants.icon.DESKTOP));
        this.setSize(451, 320);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

    }

    private void initAction() {
        btnLogin.addActionListener(this);
        btnExit.addActionListener(this);

        txtPasswd.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick();
                }
            }
        });
    }

    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }

    /**
     * ActionPerformed
     */
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == btnLogin) {
            this.buttonClick();
        } else if (obj == btnExit) {
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, "System Error.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buttonClick() {
        User user = new User(comTarget.getSelectedItem().toString(), txtEmail.getText().trim(), 
                new String(txtPasswd.getPassword()).trim());
        if (Validation.validLogin(this, user)) {
            CloudFoundryClient client = null;
            try {
                Session.getInstance().put(CloudConstants.session.USER, user);
                client = new CloudFoundryClient(
                        new CloudCredentials(user.getEmail(), user.getPasswd()), new URL(user.getTarget()));
                client.login();
            } catch (MalformedURLException e) {
                Validation.showMsg(this, "Bad target, shoud be full URL", "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (ResourceAccessException e) {
                Validation.showMsg(this, "Bad target, unkonwn URL", "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (CloudFoundryException e) {
                Validation.showMsg(this, "Sorry, invalid email or password.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            if (!Validation.isNull(client.getCloudInfo().getUser())) {
                this.setVisible(false);
                mainFrame.invoke();

                Session.setClient(client);
                Session.getInstance().put(CloudConstants.session.EXPIRY, System.currentTimeMillis());
            }
        }
    }

    /***********************************************************/
    public static Login getInstance() {
        if (null == loginFrame) {
            loginFrame = new Login();
        }
        return loginFrame;
    }

    public Login() {
        new Thread(new Runnable() {
            public void run() {
                mainFrame = new Main();
            }
        }).start();

        this.initGUI();
        this.initAction();
    }

    public String getEmail() {
        return txtEmail.getText().trim();
    }

}
