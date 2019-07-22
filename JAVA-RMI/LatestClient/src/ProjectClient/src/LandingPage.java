package ProjectClient.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.json.JSONObject;

import com.convertapi.Config;
import com.convertapi.ConvertApi;
import com.convertapi.Param;

import stress.access.questionsAccess;
import stress.access.submitSolutionAccess;
import stress.db.dbConnection;

//import java.awt.Graphics;

public class LandingPage extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	JLabel lblNewLabel;
	JProgressBar progressBar;
	Connection con=null;
	ResultSet rs=null;
	PreparedStatement ps=null;
	public static int result;
	// Setting the functions inside setgetid to the variable res
	private setgetid res=new setgetid();
	// Setting the functions inside questionsAccess to the variable qa
	private questionsAccess qa=new questionsAccess(); 
	// Setting the functions inside submitSolutionAccess to the variable sub
	private submitSolutionAccess sub=new submitSolutionAccess();
	// Assigning position as a global variable with value 1
	private int position=1;
	// Assigning questions as a global variable
	private String questions=null;
	// Assigning users as a global variable
	private String users=null;
	//Assigning occupation as a global variable
	private String occupation=null;
	//JPanel for the solutions
	private JPanel getPanel() throws SQLException {
		
		 // Getting the users id by calling the function getId in setgetid
		 int usersid=setgetid.getId();
		// Getting the occupation by calling the function getUserOccupationExt in questionsAccess(qa)
		 occupation=qa.getUserOccupationExt(usersid);
	     JPanel solpanel = new JPanel(new GridLayout(0, 1, 5, 5));
	     solpanel.setBounds(0, 0, 336, 523);
	     JLabel sol1 = new JLabel(sub.getSolutionsExt(occupation, "1" ));
	     JLabel sol2 = new JLabel(sub.getSolutionsExt(occupation, "2" ));
	     JLabel sol3 = new JLabel(sub.getSolutionsExt(occupation, "3" ));
	     JLabel sol4 = new JLabel(sub.getSolutionsExt(occupation, "4" ));
	     JLabel sol5 = new JLabel(sub.getSolutionsExt(occupation, "5" ));
	     JLabel sol6 = new JLabel(sub.getSolutionsExt(occupation, "6" ));
	     JLabel sol7 = new JLabel(sub.getSolutionsExt(occupation, "7" ));
	     JLabel sol8 = new JLabel(sub.getSolutionsExt(occupation, "8" ));
	     JLabel sol9 = new JLabel(sub.getSolutionsExt(occupation, "9" ));
	     JLabel sol10 = new JLabel(sub.getSolutionsExt(occupation, "10" ));
	     solpanel.add(sol1);
	     solpanel.add(sol2);
	     solpanel.add(sol3);
	     solpanel.add(sol4);
	     solpanel.add(sol5);
	     solpanel.add(sol6);
	     solpanel.add(sol7);
	     solpanel.add(sol8);
	     solpanel.add(sol9);
	     solpanel.add(sol10);

	     return solpanel;
	}
	private void displayGUI() throws HeadlessException, SQLException {
       JOptionPane.showMessageDialog(
           null, getPanel(), "Solutions : ",
               JOptionPane.INFORMATION_MESSAGE);
   }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LandingPage frame = new LandingPage();
					
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public LandingPage() throws SQLException {
		setTitle("Home");
		
		//lblNewLabel.setEnabled(false);
		this.setLocationRelativeTo(null);
		Border border = BorderFactory.createLineBorder((new Color(205,183,158)), 1); // for textfield border
		
		this.setUndecorated(true); // for dont get frame buttons
		Border borde = BorderFactory.createLineBorder((new Color(255,250,250)), 1); // for textfield border
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(470, 250, 740, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(255,250,250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(205,183,158));
		panel.setBounds(0, 0, 353, 633);
		int finalresult=setgetid.getresult();
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Display the final stress result of the user
		lblNewLabel = new JLabel(""+finalresult+"", SwingConstants.CENTER);
		lblNewLabel.setBounds(91, 69, 146, 110);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 46));
		lblNewLabel.setForeground(new Color(128,128,128));
		
		JLabel lblStressLevel = new JLabel("Stress Level", SwingConstants.CENTER);
		lblStressLevel.setBounds(49, 30, 235, 50);
		lblStressLevel.setForeground(new Color(255,250,250));
		panel.add(lblStressLevel);
		lblStressLevel.setFont(new Font("Freestyle Script", Font.BOLD, 35));
		
		// Display the result in a progress bar
		int progresult = finalresult;
		progressBar = new JProgressBar();
		progressBar.setMaximum(40);
		progressBar.setValue(progresult);
		progressBar.setBounds(77, 190, 197, 40);
		progressBar.setBorder(borde);
		progressBar.setBackground(new Color(255,250,250));
		panel.add(progressBar);
		
		// The below 3 labels show the boundaries in which each level of stress is defined
		JLabel lblNewLabel_1 = new JLabel("0 - 13  -> low perceived stress");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setForeground(new Color(255, 250, 250));
		lblNewLabel_1.setBounds(10, 315, 264, 14);
		panel.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("14 - 26  -> moderate perceived stress");
		label_1.setForeground(new Color(255, 250, 250));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_1.setBounds(10, 348, 316, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("27 - 40  -> high perceived stress");
		label_2.setForeground(new Color(255, 250, 250));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_2.setBounds(10, 383, 274, 19);
		panel.add(label_2);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(LandingPage.class.getResource("/images/check-mark.png")));
		lblNewLabel_2.setBounds(267, 305, 29, 30);
		panel.add(lblNewLabel_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(LandingPage.class.getResource("/images/check-mark (2).png")));
		label_3.setBounds(324, 335, 29, 40);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(LandingPage.class.getResource("/images/check-mark (1).png")));
		label_4.setBounds(278, 371, 36, 40);
		panel.add(label_4);
		
		
		//int finalresult=setgetid.getresult();
		
		JLabel label = new JLabel(" X");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(713, 0, 17, 27);
		contentPane.add(label);
		
		// The button for Questionnaire 
		int usersid=setgetid.getId();
		occupation=qa.getUserOccupationExt(usersid);
		JButton btnStartQuestionnaire = new JButton("  Start Questionnaire");
		btnStartQuestionnaire.setHorizontalAlignment(SwingConstants.LEFT);
		btnStartQuestionnaire.setIcon(new ImageIcon(LandingPage.class.getResource("/images/test.png")));
		// Event handler for the button on click even
		btnStartQuestionnaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
					Date date=new Date();
					String today=dateformat.format(date).toString();
					
					
					String filename=today+users+".txt";
					String dir="E:\\";
					String file=dir+filename;
					
					//generates a txt document as the user answers the questionnaire with dynamic naming for the txt document.
					FileWriter write=new FileWriter(new File(dir,filename));
					write.write("GENERATED DATE: " +today+"\t");
					write.write("USER NAME: "+users+"\t\t");
					write.write("OCCUPATION: "+occupation+"\t\n");
					
					write.write("=================================================================================\n\n");
					write.write("************ YOUR QUESTIONNAIRE REPORT ************ \n\n");
					
					
					System.out.println("occupation---"+occupation);
					if(e.getSource().equals(btnStartQuestionnaire)) {
						position=1;
						result=0;
						for (;position<=10;position++) {
							// Getting the questions by calling the function getQuestionsExt  by passing the position and the occupation in questionsAccess.java(qa)
							questions = qa.getQuestionsExt(Integer.toString(position), occupation);
							
							
							
							write.write(questions);
							write.write("\n");
							
							UIManager.put("OptionPane.minimumSize",new Dimension(650, 412));
							// The answers that will be shown to be selected in the questionnaire
							String[] choice = {"Never", "Almost Never ", "Sometimes", "Fairly Often", "Very Often"};
							// setting the index of the answer value to n
							int n = JOptionPane.showOptionDialog(null, 
									questions, "Question "+position+"/10 ?", JOptionPane.DEFAULT_OPTION, 
									JOptionPane.QUESTION_MESSAGE, null, choice, choice[0]);
							if (n==JOptionPane.CLOSED_OPTION) {
								UIManager.put("OptionPane.minimumSize",new Dimension(200, 50));
								int input=JOptionPane.showConfirmDialog(null, "Do you really want to exit from the questionnaire? YOUR PROGRESS WILL BE LOST!", "ReallY?", JOptionPane.YES_NO_OPTION);
								if(input==0) {
									setDefaultCloseOperation(JOptionPane.ABORT);result=0;
									}
								else {
									position--;
								}
					        } // Adding the index to the result count
							  else if (n == 0) {
								write.write("Your answer is :  Never \n");  
					        	result=result+n;
					        } else if (n == 1) {
					        	write.write("Your answer is :  Almost Never \n");
					        	result=result+n;
					        } else if (n == 2) {
					        	write.write("Your answer is :  Sometimes \n");
					        	result=result+n;
					        } else if (n == 3) {
					        	write.write("Your answer is :  Fairly Often \n");
					        	result=result+n;
					        } else if (n == 4) {
					        	result=result+n;
					        	write.write("Your answer is :  Very Often \n");
					        }
								write.write("\n");
							System.out.println("result---"+result);
							System.out.println("output---"+n);
							System.out.println("Answer---"+n);
							if (position == 10) {
								int finalresult=result;
								System.out.println("finalresult---"+finalresult);
								// Getting the users id by calling the function getId in setgetid
								int userid=setgetid.getId();
								boolean suc=sub.submitAnsExt(userid, result);
								System.out.println("submit answer---"+suc);
								// Checking if the final reult count is in the range of 0-13
								if ( finalresult >= 0 && finalresult <= 13 ) {
									lblNewLabel.setText(""+finalresult);
									lblNewLabel.setForeground(Color.GREEN);
									lblNewLabel.repaint();
									int progresult = finalresult;
									progressBar.setValue(finalresult);
									progressBar.setForeground(Color.GREEN);
									progressBar.repaint();
								} // Checking if the final reult count is in the range of 14-26 
								 else if (finalresult >= 14 && finalresult <= 26) {
									lblNewLabel.setText(""+finalresult);
									lblNewLabel.setForeground(Color.YELLOW);
									lblNewLabel.repaint();
									int progresult = finalresult;
									progressBar.setValue(finalresult);
									progressBar.setForeground(Color.YELLOW);
									progressBar.repaint();
								} // Checking if the final reult count is in the range of 27-40
								 else if (finalresult >= 27 && finalresult <= 40) {
									lblNewLabel.setText(""+finalresult);
									lblNewLabel.setForeground(Color.RED);
									lblNewLabel.repaint();
									int progresult = finalresult;
									progressBar.setValue(finalresult);
									progressBar.setForeground(Color.RED);
									progressBar.repaint();
								} 
							}
						}
						write.write("=============================================\n");
						write.write("|                                           |\n");
						write.write("|    YOUR PERCEIVED STRESS SCALE: "+result+"        |\n");
						write.write("|                                           |\n");
						write.write("=============================================");
						
						
					} else {
							JOptionPane.showMessageDialog(null,"Try Again!");
					}
					
					write.close();
					
					/**
					 * This block converts a txt file to a pdf document using a Web API. It sends a POST request with the relevant tags and retrieves the pdf document of the generated txt file.
					 *    	
					 */
					Config.setDefaultSecret("obUugvCY2e22d1iI");
					ConvertApi.convert("txt", "pdf",
					    new Param("File", Paths.get(file)),
					    new Param("FileName", "yourdoc" )).get().saveFilesSync(Paths.get("C:\\Users\\qaisf\\Desktop"));
					    UIManager.put("OptionPane.minimumSize",new Dimension(200, 50));
						JOptionPane.showMessageDialog(null,"Check your desktop for the report! ");
				}
				catch (Exception e1) {
					System.out.println("Error in geting Questions");
					e1.printStackTrace();
					// TODO: handle exception
				}
			}
		});
		btnStartQuestionnaire.setBounds(446, 141, 192, 38);
		btnStartQuestionnaire.setBorder(borde);
		btnStartQuestionnaire.setForeground(new Color(255,250,250));
		btnStartQuestionnaire.setBackground(new Color(205,183,158));
		btnStartQuestionnaire.setFont(new Font("Dialog", Font.BOLD, 14));
		btnStartQuestionnaire.setBorder(border);
		contentPane.add(btnStartQuestionnaire);
		
		// Getting the users id by calling the function getId in setgetid
		int a=setgetid.getId();
		// Getting the User Detail by calling the function getUserDetailsExt in questionsAccess(qa)
		users=qa.getUserDetailsExt(a);
		JLabel lblWelcome = new JLabel("Welcome "+users+"!", SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Freestyle Script", Font.BOLD, 40));
		lblWelcome.setBackground(new Color(0, 0, 0));
		lblWelcome.setForeground(new Color(205,183,158));
		lblWelcome.setBounds(389, 43, 302, 38);
		contentPane.add(lblWelcome);
		
		JButton btnLogOut = new JButton("  Log Out");
		btnLogOut.setIcon(new ImageIcon(LandingPage.class.getResource("/images/logout.png")));
		// Event handler for Log Out Button onclick event
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				res.setresult(0);
				Login l = new Login();
				l.setVisible(true);
				l.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnLogOut.setForeground(new Color(255, 250, 250));
		btnLogOut.setFont(new Font("Dialog", Font.BOLD, 14));
		btnLogOut.setBackground(new Color(205, 183, 158));
		btnLogOut.setBounds(463, 410, 153, 38);
		btnLogOut.setBorder(border);
		contentPane.add(btnLogOut);
		
		
		JButton btnSolutionstips = new JButton("  Solutions/Tips");
		btnSolutionstips.setHorizontalAlignment(SwingConstants.LEFT);
		btnSolutionstips.setIcon(new ImageIcon(LandingPage.class.getResource("/images/puzzle.png")));
		// Event handler for Solutions/Tips Button onclick event
		btnSolutionstips.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
//					UIManager.put("OptionPane.minimumSize",new Dimension(650, 412));
//					JOptionPane.showMessageDialog(null, getPanel(), "Solutions: ", JOptionPane.INFORMATION_MESSAGE);
					 new LandingPage().displayGUI();
				} catch (HeadlessException e) {
					System.out.println("Error in geting Solutions");
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnSolutionstips.setForeground(new Color(255, 250, 250));
		btnSolutionstips.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSolutionstips.setBackground(new Color(205, 183, 158));
		btnSolutionstips.setBounds(446, 234, 192, 38);
		btnSolutionstips.setBorder(border);
		contentPane.add(btnSolutionstips);
		
		JButton btnShowPastResult = new JButton("  Show Past Results");
		btnShowPastResult.setHorizontalAlignment(SwingConstants.LEFT);
		btnShowPastResult.setIcon(new ImageIcon(LandingPage.class.getResource("/images/line-chart.png")));
		// Event handler for Show Past Result Button onclick event
		btnShowPastResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Getting the users id by calling the function getId in setgetid
				int userId=setgetid.getId();
				
				try {
					String sql="select date,result from pastResults where userId="+userId;
					JDBCCategoryDataset dataset=new JDBCCategoryDataset(dbConnection.db(),sql);
					JFreeChart chart=ChartFactory.createLineChart("Past Results Analysis","Date","Past Results", dataset,PlotOrientation.VERTICAL,false,true,true);
					BarRenderer renderer=null; 
					CategoryPlot p=null;
					renderer=new BarRenderer();
					
					
					ChartFrame frame=new ChartFrame("Past Results Analysis", chart);
					frame.setVisible(true);
					
					frame.setSize(800,600);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnShowPastResult.setForeground(new Color(255, 250, 250));
		btnShowPastResult.setFont(new Font("Dialog", Font.BOLD, 14));
		btnShowPastResult.setBackground(new Color(205, 183, 158));
		btnShowPastResult.setBounds(446, 317, 192, 38);
		btnShowPastResult.setBorder(border);
		contentPane.add(btnShowPastResult);
		
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
				contentPane.add(lblNewLabel_4);
				
				JLabel lblNewLabel_5 = new JLabel("A Project By: Nadeem, Nasik & Qais");
				lblNewLabel_5.setForeground(Color.RED);
				lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 10));
				lblNewLabel_5.setBounds(556, 507, 184, 14);
				contentPane.add(lblNewLabel_5);
				
				JLabel lblClickBelowTo = new JLabel("Click below to start a questionnaire to determine stress!");
				lblClickBelowTo.setForeground(Color.BLUE);
				lblClickBelowTo.setFont(new Font("Tahoma", Font.BOLD, 10));
				lblClickBelowTo.setBounds(402, 115, 289, 14);
				contentPane.add(lblClickBelowTo);
				
				JLabel lblDeterminedAsHigh = new JLabel("Determined as high stress? Check out these useful tips!\r\n");
				lblDeterminedAsHigh.setForeground(Color.BLUE);
				lblDeterminedAsHigh.setFont(new Font("Tahoma", Font.BOLD, 10));
				lblDeterminedAsHigh.setBounds(402, 206, 289, 14);
				contentPane.add(lblDeterminedAsHigh);
				
				JLabel lblLookAtYour = new JLabel("Look at your past results in a representational analysis!");
				lblLookAtYour.setForeground(Color.BLUE);
				lblLookAtYour.setFont(new Font("Tahoma", Font.BOLD, 10));
				lblLookAtYour.setBounds(402, 292, 289, 14);
				contentPane.add(lblLookAtYour);
			} catch (java.io.IOException e) {
			    e.printStackTrace();
			}	
			
			
			
			
			
		 
		
		
		
		//////
		
	}
}
