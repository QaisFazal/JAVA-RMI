package ProjectClient.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import stress.access.loginAccess;
import stress.db.dbConnection;

public class Login extends JFrame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2437303348140158940L;
	Connection con=null;
	ResultSet rs=null;
	PreparedStatement ps=null;

	private loginAccess lg=new loginAccess(); 
	private setgetid uid=new setgetid();
	
	private JPanel contentPane;
	private JTextField txtEnterUsername;
	private JPasswordField passwordField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	@SuppressWarnings("deprecation")
	public Login() {
		setTitle("Log In");
		
		con=dbConnection.db();
		
		this.setUndecorated(true); // for dont get frame buttons
		Border border = BorderFactory.createLineBorder((new Color(205,183,158)), 1); // for textfield border
		
		setBackground(SystemColor.text);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(470, 250, 740, 532);
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
		
		JLabel label = new JLabel("Stress ");
		label.setForeground(new Color(255, 250, 250));
		label.setFont(new Font("Freestyle Script", Font.BOLD, 59));
		label.setBounds(10, 55, 254, 71);
		panel.add(label);
		
		JLabel label_2 = new JLabel("Calculator");
		label_2.setForeground(new Color(255, 250, 250));
		label_2.setFont(new Font("Freestyle Script", Font.BOLD, 59));
		label_2.setBounds(87, 142, 316, 71);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(Login.class.getResource("/images/g.gif")));
		label_3.setBounds(56, 270, 246, 208);
		panel.add(label_3);
		
		JLabel lblEmail = new JLabel("USERNAME");
		lblEmail.setBounds(415, 41, 80, 14);
		contentPane.add(lblEmail);
		
		txtEnterUsername = new JTextField();
		txtEnterUsername.setForeground(new Color(222, 184, 135));
		txtEnterUsername.setText("Enter username");
		txtEnterUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtEnterUsername.getText().equals("Enter username")) {
					txtEnterUsername.setText("");
				}
				txtEnterUsername.setForeground(Color.black);
			}		
			
		});
		txtEnterUsername.setBounds(415, 57, 246, 26);
		txtEnterUsername.setBorder(border);
		contentPane.add(txtEnterUsername);
		txtEnterUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(415, 107, 97, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(415, 124, 246, 26);
		passwordField.setBorder(border);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("  LogIn");
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int val=-1;
				String username=txtEnterUsername.getText();
				@SuppressWarnings("deprecation")
				String password=passwordField.getText();
				String occupation=textField_1.getText();
				try {
					
					val=lg.loginExt(username, password, occupation);
					
					if(val>=1) {
						uid.setid(val);
						UIManager.put("OptionPane.minimumSize",new Dimension(200, 50));
						JOptionPane.showMessageDialog(null,"Success");
						LandingPage lp = new LandingPage();
						lp.setVisible(true);
						lp.setLocationRelativeTo(null);
						dispose();
						
					}
					else {
						
						JOptionPane.showMessageDialog(null,"Invalid Login! Try Again!");
					}
				
				
				
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		btnLogin.setForeground(new Color(255,250,250));
		btnLogin.setBackground(new Color(205,183,158));
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		btnLogin.setBorder(border);
		btnLogin.setBounds(415, 290, 246, 38);
		contentPane.add(btnLogin);
		
		JLabel label_1 = new JLabel(" X");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(713, 0, 17, 27);
		contentPane.add(label_1);
		
		JLabel lblNewLabel = new JLabel("Register to check your stress now!");
		lblNewLabel.setForeground(new Color(0, 0, 205));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(453, 384, 188, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnSignUp = new JButton("  Sign Up");
		btnSignUp.setIcon(new ImageIcon(Login.class.getResource("/images/login2.png")));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Home hm = new Home();
				hm.setVisible(true);
				hm.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnSignUp.setForeground(new Color(255, 250, 250));
		btnSignUp.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSignUp.setBackground(new Color(205, 183, 158));
		btnSignUp.setBorder(border);
		btnSignUp.setBounds(415, 409, 246, 38);
		contentPane.add(btnSignUp);
		
		JLabel lblNewLabel_1 = new JLabel("OCCUPATION");
		lblNewLabel_1.setBounds(415, 174, 97, 14);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = (String)comboBox.getSelectedItem();
				textField_1.setText(value);
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose", "Student", "Undergraduate", "Employee"}));
		comboBox.setBorder(border);
		comboBox.setBackground(new Color(255,250,250));
		comboBox.setBounds(415, 199, 246, 26);
		contentPane.add(comboBox);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.hide();
		textField_1.setBounds(346, 25, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		

		JLabel lblNewLabel_3 = new JLabel("Date:");
		lblNewLabel_3.setForeground(new Color(0, 0, 205));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setBounds(354, 0, 46, 14);
		contentPane.add(lblNewLabel_3);
		
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
		




		JLabel lblConnectedFrom = new JLabel("Connected from:");
		lblConnectedFrom.setForeground(Color.BLUE);
		lblConnectedFrom.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblConnectedFrom.setBounds(526, 0, 90, 14);
		contentPane.add(lblConnectedFrom);
		
		/**
		 * This block is a simple GET request to request JSON data from a web API and retrieves the location of the user.
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
		
		JLabel lblNewLabel_2 = new JLabel("A Project By: Nadheem, Nasik & Qais");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(542, 518, 188, 14);
		contentPane.add(lblNewLabel_2);
		} catch (java.io.IOException e) {
				e.printStackTrace();
		}	

	}
}
