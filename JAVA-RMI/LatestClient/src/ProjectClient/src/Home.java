package ProjectClient.src;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.*;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import stress.access.loginAccess;


public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2021496328010412952L;
	private JPanel contentPane;
	private JTextField txtEnterName;
	private JTextField txtEnterPassword;
	private JComboBox comboBox_1;
	private JButton button;
	
	
	
	private loginAccess lg=new loginAccess(); 
	private JPasswordField passwordField;
	private JTextField textField;
	private JPasswordField repassword;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setUndecorated(true); // for dont get frame buttons
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setTitle("Sign Up");
		this.setUndecorated(true); // for dont get frame buttons
		Border border = BorderFactory.createLineBorder((new Color(205,183,158)), 1); // for textfield border
		
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(470, 250, 741, 560);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255,250,250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(205,183,158));
		panel.setBounds(0, 0, 336, 842);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Home.class.getResource("/images/login.png")));
		label.setBounds(-89, 0, 453, 466);
		panel.add(label);
		
		JButton btnSignup = new JButton("  SignUp");
		btnSignup.setIcon(new ImageIcon(Home.class.getResource("/images/login2.png")));
	//	button.setBackground(new Color(205,183,158));
		btnSignup.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			
			/**
			 * Form validation for sign up.
			 * 
			 */ 
			public void actionPerformed(ActionEvent arg0) {
				
				Pattern p = Pattern.compile("^[A-Za-z0-9-]+(\\-[A-Za-z0-9])*@"
		                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9])");
		        Matcher m = p.matcher(txtEnterPassword.getText());
		        
		        if(m.find()==false) {
		        	JOptionPane.showMessageDialog(null, "Incorrect Email. Try Again!", "", JOptionPane.ERROR_MESSAGE);
		        	txtEnterPassword.setText("");
		        }
		        
		        if(!passwordField.getText().equals(repassword.getText())) {
		        	JOptionPane.showMessageDialog(null, "The passwords you typed were incorrect! Try Again!", "", JOptionPane.ERROR_MESSAGE);
		        	passwordField.setText("");
		        	repassword.setText("");
		        	
		        }
		        if(txtEnterName.getText().equals("") ||txtEnterPassword.getText().equals("") || txtEnterName.getText().equals("") || textField.getText().equals("") || repassword.getText().equals(""))  {
					JOptionPane.showMessageDialog(null, "Please fill all the relavent information!","", JOptionPane.ERROR_MESSAGE);
				}
				else{
				//Login.textField_1.set
				boolean val=false;
				String username=txtEnterName.getText();
				String email=txtEnterPassword.getText();
				String password=passwordField.getText();
				String occupation=textField.getText();
				
				try {
					val=lg.signUpExt(username, email, password, occupation);
					
					if(val==true) {
						JOptionPane.showMessageDialog(null, "New User Added!");
					}
					else {
						JOptionPane.showMessageDialog(null,"Something went wrong during Sign Up!");
					}
					
				}
				catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			}}
		});
		btnSignup.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSignup.setForeground(new Color(255,250,250));
		btnSignup.setBackground(new Color(205,183,158));
		btnSignup.setBorder(border);
		btnSignup.setBounds(415, 395, 253, 38);
		contentPane.add(btnSignup);
	
		txtEnterName = new JTextField();
		/*
		txtEnterName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(Character.isDigit(ch)) {
					txtEnterName.setText("");
					JOptionPane.showMessageDialog(null, "WRONG!");
				}
				
			}
		});*/
		txtEnterName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtEnterName.getText().equals("Enter Name")) {
					txtEnterName.setText("");
				}
				txtEnterName.setForeground(Color.black);
			}		
			
		});
		//txtEnterName.setForeground(new Color(205,183,158));
		txtEnterName.setForeground(new Color(222, 184, 135));
		txtEnterName.setBackground(Color.WHITE);
		txtEnterName.setBorder(border);
        txtEnterName.setBounds(415, 67, 253, 27);
		contentPane.add(txtEnterName);
		txtEnterName.setColumns(10);
		
		JLabel lblUserName = new JLabel("USER NAME");
		lblUserName.setBounds(415, 49, 69, 14);
		contentPane.add(lblUserName);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(415, 120, 69, 14);
		contentPane.add(lblEmail);
		
		txtEnterPassword = new JTextField();
		txtEnterPassword.setForeground(new Color(222, 184, 135));
		txtEnterPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtEnterPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtEnterPassword.getText().equals("Enter Password")) {
					txtEnterPassword.setText("");
			}
			   txtEnterPassword.setForeground(Color.black);
			}
		});
		txtEnterPassword.setBackground(new Color(255, 255, 255));
		txtEnterPassword.setBorder(border);
		txtEnterPassword.setColumns(10);
		txtEnterPassword.setBounds(416, 134, 252, 27);
		contentPane.add(txtEnterPassword);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(415, 190, 69, 14);
		contentPane.add(lblPassword);
		
		JLabel lblRepeatPassword = new JLabel("OCCUPATION");
		lblRepeatPassword.setBounds(415, 309, 123, 14);
		contentPane.add(lblRepeatPassword);
		
		JLabel lblclose = new JLabel(" X");
		lblclose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lblclose.setForeground(new Color(255, 0, 0));
		lblclose.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblclose.setBounds(714, 0, 17, 27);
		contentPane.add(lblclose);
		
		JButton btnLogin = new JButton("  Login");
		btnLogin.setIcon(new ImageIcon(Home.class.getResource("/images/exit.png")));
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		btnLogin.setForeground(new Color(255,250,250));
		btnLogin.setBackground(new Color(205,183,158));
		btnLogin.setBorder(border);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Login lg = new Login();
				lg.setVisible(true);
				lg.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnLogin.setBounds(415, 482, 253, 38);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(416, 203, 252, 27);
		passwordField.setBorder(border);
		contentPane.add(passwordField);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String value = (String)comboBox_1.getSelectedItem();
				textField.setText(value);
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Choose", "Student", "Undergraduate", "Employee"}));
		comboBox_1.setBounds(416, 334, 252, 27);
		comboBox_1.setBackground(new Color(255,250,250));
		comboBox_1.setBorder(border);
		contentPane.add(comboBox_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setEnabled(false);
		textField.hide();
		textField.setBounds(497, 337, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Let's calculate your stress now!");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(472, 457, 156, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblReenterPassword = new JLabel("RE-ENTER PASSWORD");
		lblReenterPassword.setBounds(415, 253, 156, 14);
		contentPane.add(lblReenterPassword);
		
		repassword = new JPasswordField();
		repassword.setBounds(415, 271, 253, 27);
		contentPane.add(repassword);
		
		
		/**
		 * This block is a simple GET request to request JSON data from a web API and retrieves the date time.
		 *    	
		 */
		try (java.util.Scanner s = new java.util.Scanner(new java.net.URL("http://worldtimeapi.org/api/ip").openStream(), "UTF-8").useDelimiter("\\A")) {
		    JSONObject obj=new JSONObject(s.next());
		    String date=obj.getString("datetime");
		    JLabel lblDateGoesHere = new JLabel(date);
			lblDateGoesHere.setForeground(Color.BLUE);
			lblDateGoesHere.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblDateGoesHere.setBounds(387, 0,74, 14);
			contentPane.add(lblDateGoesHere);
		}	
		catch (java.io.IOException e) {
		    e.printStackTrace();
		}
		
		
	
	

		JLabel lblNewLabel_3 = new JLabel("Date:");
		lblNewLabel_3.setForeground(new Color(0, 0, 205));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setBounds(354, 0, 46, 14);
		contentPane.add(lblNewLabel_3);




		JLabel lblConnectedFrom = new JLabel("Connected from:");
		lblConnectedFrom.setForeground(Color.BLUE);
		lblConnectedFrom.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblConnectedFrom.setBounds(526, 0, 90, 14);
		contentPane.add(lblConnectedFrom);
		
		/**
		 * This block is a simple GET request to request JSON data from a web API and retrieves the location the user is connected from.
		 *    	
		 */
		try (java.util.Scanner s = new java.util.Scanner(new java.net.URL(" http://ip-api.com/json").openStream(), "UTF-8").useDelimiter("\\A")) {
			JSONObject obj=new JSONObject(s.next());
			String country=obj.getString("country");
    
			JLabel lblNewLabel_4 = new JLabel(country);
			lblNewLabel_4.setForeground(Color.BLUE);
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblNewLabel_4.setBounds(612, 0, 67, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_1 = new JLabel("A Project By: Nadeem, Nasik & Qais");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setBounds(549, 546, 182, 14);
		contentPane.add(lblNewLabel_1);
		} catch (java.io.IOException e) {
				e.printStackTrace();
		}	

		
		
		
	}
}
