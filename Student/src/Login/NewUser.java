package Login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;

import AdminSide.StudentInfo;
import CommonClasses.ServerCollection;

public class NewUser extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public boolean n,flag=false;
	public String c;char t;
	public JTextPane textPane,textPane2,textPane3,textPane_1,textPane_2,textPane_3,textPane_4;
	public JLabel lblFillTheFollowing,lblCollegeName,lblCollegeName2,lblCollegeName3,lblPleaseEnterThe,lblPleaseEnterThe2,lblPleaseEnterThe3,lblPleaseEnterThe4,lblPleaseEnterThe5,lblPleaseEnterThe6,lblPleaseEnterThe7,
	lblMobileNumber,lblCetMarks,lblNickName,lblPleaseFillValid,lblNewLabel;
	public JButton btnPutChoice,button;
	public NewUser thispanel;
	public StudentInfo studentinfo;
	public JLabel lblPassword;
	public DateFormat formatter;
	public NewUser(Login parent,ObjectInputStream input,ObjectOutputStream output) {

		setBackground(new Color(0, 0, 170));
		setLayout(null);
		setBounds(280,150,715,580);
		
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);
		
		lblFillTheFollowing = new JLabel("New User Creation");
		lblFillTheFollowing.setBounds(240, 27, 263, 30);
		lblFillTheFollowing.setForeground(Color.WHITE);
		lblFillTheFollowing.setFont(new Font("Arial", Font.BOLD, 25));
		add(lblFillTheFollowing);
	
		lblCollegeName = new JLabel("User_Name");
		lblCollegeName.setForeground(Color.WHITE);
		lblCollegeName.setBounds(45, 80, 153, 25);
		lblCollegeName.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(lblCollegeName);
		
		textPane = new JTextPane();
		textPane.setBounds(216, 80, 439, 36);
		textPane.setFont(new Font("Arial", Font.PLAIN, 22));
		add(textPane);
		
		lblCollegeName2 = new JLabel("Address");
		lblCollegeName2.setForeground(Color.WHITE);
		lblCollegeName2.setBounds(45, 140, 153, 25);
		lblCollegeName2.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(lblCollegeName2);
		
		textPane2 = new JTextPane();
		textPane2.setBounds(216, 140, 439, 36);
		textPane2.setFont(new Font("Arial", Font.PLAIN, 22));
		add(textPane2);
		
		lblCollegeName3 = new JLabel("Date of Birth");
		lblCollegeName3.setForeground(Color.WHITE);
		lblCollegeName3.setBounds(45, 200, 153, 25);
		lblCollegeName3.setFont(new Font("Dialog", Font.PLAIN, 20));
		add(lblCollegeName3);
		
		textPane3 = new JTextPane();
		textPane3.setBounds(216, 200, 439, 36);
		textPane3.setFont(new Font("Arial", Font.PLAIN, 22));
		add(textPane3);
		
		lblPleaseEnterThe = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe.setForeground(Color.YELLOW);
		lblPleaseEnterThe.setVisible(false);
		lblPleaseEnterThe.setBounds(216, 115, 225, 15);
		add(lblPleaseEnterThe);
		
		lblPleaseEnterThe2 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe2.setForeground(Color.YELLOW);
		lblPleaseEnterThe2.setVisible(false);
		lblPleaseEnterThe2.setBounds(216, 175, 225, 15);
		add(lblPleaseEnterThe2);
		
		lblPleaseEnterThe3 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe3.setForeground(Color.YELLOW);
		lblPleaseEnterThe3.setVisible(false);
		lblPleaseEnterThe3.setBounds(216, 235, 225, 15);
		add(lblPleaseEnterThe3);
		
		lblPleaseEnterThe4 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe4.setForeground(Color.YELLOW);
		lblPleaseEnterThe4.setVisible(false);
		lblPleaseEnterThe4.setBounds(216, 295, 225, 15);
		add(lblPleaseEnterThe4);
		
		lblPleaseEnterThe5 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe5.setForeground(Color.YELLOW);
		lblPleaseEnterThe5.setVisible(false);
		lblPleaseEnterThe5.setBounds(216, 355, 225, 15);
		add(lblPleaseEnterThe5);
		
		lblPleaseEnterThe6 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe6.setForeground(Color.YELLOW);
		lblPleaseEnterThe6.setVisible(false);
		lblPleaseEnterThe6.setBounds(216, 417, 225, 15);
		add(lblPleaseEnterThe6);
		
		lblPleaseEnterThe7 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe7.setForeground(Color.YELLOW);
		lblPleaseEnterThe7.setVisible(false);
		lblPleaseEnterThe7.setBounds(216, 481, 225, 15);
		add(lblPleaseEnterThe7);
		
		textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("Arial", Font.PLAIN, 22));
		textPane_1.setBounds(216, 260, 439, 36);
		add(textPane_1);
		
		lblMobileNumber = new JLabel("Mobile No");
		lblMobileNumber.setForeground(Color.WHITE);
		lblMobileNumber.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblMobileNumber.setBounds(45, 260, 153, 25);
		add(lblMobileNumber);
		
		textPane_2 = new JTextPane();
		textPane_2.setFont(new Font("Arial", Font.PLAIN, 22));
		textPane_2.setBounds(216, 319, 439, 36);
		add(textPane_2);
		
		lblCetMarks = new JLabel("CET Marks");
		lblCetMarks.setForeground(Color.WHITE);
		lblCetMarks.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblCetMarks.setBounds(45, 320, 140, 25);
		add(lblCetMarks);
		
		textPane_3 = new JTextPane();
		textPane_3.setFont(new Font("Arial", Font.PLAIN, 22));
		textPane_3.setBounds(216, 381, 439, 36);
		add(textPane_3);
		
		lblNickName = new JLabel("Nick Name");
		lblNickName.setForeground(Color.WHITE);
		lblNickName.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNickName.setBounds(45, 380, 153, 25);
		add(lblNickName);
		
		textPane_4 = new JTextPane();
		textPane_4.setFont(new Font("Arial", Font.PLAIN, 22));
		textPane_4.setBounds(216, 444, 439, 36);
		add(textPane_4);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblPassword.setBounds(45, 444, 153, 25);
		add(lblPassword);
		
		lblPleaseFillValid = new JLabel("Student already Exists");
		lblPleaseFillValid.setForeground(Color.YELLOW);
		lblPleaseFillValid.setBounds(277, 500, 198, 15);
		lblPleaseFillValid.setVisible(false);
		add(lblPleaseFillValid);
		
		btnPutChoice = new JButton("Submit");
		btnPutChoice.setBounds(265, 521, 198, 36);
		btnPutChoice.setFont(new Font("Arial",Font.BOLD, 20));
		btnPutChoice.setFocusable(true);
		btnPutChoice.setBackground(new Color(0, 204, 255));
		btnPutChoice.setForeground(Color.white);
		btnPutChoice.setFocusable(false);
		ServerCollection.b1=btnPutChoice;
		thispanel=this;
		btnPutChoice.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
					output.reset();
				} catch (SocketException e1) 
				{	
					JOptionPane.showMessageDialog(null,"Server Down");
					return;
				}
				catch (IOException e) {
					
					e.printStackTrace();
				}
				
			   if(textPane.getText().isEmpty() || textPane2.getText().isEmpty() 
					   || textPane3.getText().isEmpty() ||textPane_1.getText().isEmpty() || textPane_2.getText().isEmpty() ||textPane_3.getText().isEmpty() || textPane_4.getText().isEmpty())
			   {
				   if(textPane.getText().isEmpty())
						lblPleaseEnterThe.setVisible(true);
				   if(textPane2.getText().isEmpty())
						lblPleaseEnterThe2.setVisible(true);
				   if(textPane3.getText().isEmpty())
						lblPleaseEnterThe3.setVisible(true);
				   if(textPane_1.getText().isEmpty())
						lblPleaseEnterThe4.setVisible(true);
				   if(textPane_2.getText().isEmpty())
						lblPleaseEnterThe5.setVisible(true);
				   if(textPane_3.getText().isEmpty())
						lblPleaseEnterThe6.setVisible(true);
				   if(textPane_4.getText().isEmpty())
					   lblPleaseEnterThe7.setVisible(true);
						Timer t=new Timer(2000,new ActionListener() 
						{
							public void actionPerformed(ActionEvent e)
							{
							   if(textPane.getText().isEmpty())
									lblPleaseEnterThe.setVisible(false);
							   if(textPane2.getText().isEmpty())
									lblPleaseEnterThe2.setVisible(false);
							   if(textPane3.getText().isEmpty())
									lblPleaseEnterThe3.setVisible(false);
							   if(textPane_1.getText().isEmpty())
									lblPleaseEnterThe4.setVisible(false);
							   if(textPane_2.getText().isEmpty())
									lblPleaseEnterThe5.setVisible(false);
							   if(textPane_3.getText().isEmpty())
									lblPleaseEnterThe6.setVisible(false);
							   if(textPane_4.getText().isEmpty())
								   lblPleaseEnterThe7.setVisible(false);
							}
						});
						t.start();
						t.setRepeats(false);
						return;
				}
			    studentinfo=new StudentInfo();
			    studentinfo.name=textPane.getText();
			    studentinfo.address=textPane2.getText();
			    studentinfo.dob=textPane3.getText();
			    try {
				    @SuppressWarnings("unused")
					Date date= formatter.parse(studentinfo.dob);
				    c=Character.toString(studentinfo.dob.charAt(studentinfo.dob.length()-1));
				    if((studentinfo.dob.length()!=10 && studentinfo.dob.length()!=9 && studentinfo.dob.length()!=8) || !c.matches("[0-9]+"))
				    {
				    	lblPleaseEnterThe3.setVisible(true);
						Timer t=new Timer(2000,new ActionListener() 
						{
							public void actionPerformed(ActionEvent e)
							{
								lblPleaseEnterThe3.setVisible(false);
							}
						});
						flag=true;
						t.setRepeats(false);
						t.start();
				    }
				} catch (ParseException e) 
			    {
					lblPleaseEnterThe3.setVisible(true);
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							lblPleaseEnterThe3.setVisible(false);
						}
					});
					flag=true;
					t.setRepeats(false);
					t.start();
				}
			    try 
			    {
			    	studentinfo.mob=Long.parseLong(textPane_1.getText());
			    	if(textPane_1.getText().length()!=10)
			    	{
			    		lblPleaseEnterThe4.setVisible(true);
						Timer t=new Timer(2000,new ActionListener() 
						{
							public void actionPerformed(ActionEvent e)
							{
								lblPleaseEnterThe4.setVisible(false);
							}
						});
						flag=true;
						t.setRepeats(false);
						t.start();
				    }
			    }
			    catch(NumberFormatException e1)
				{
			    	lblPleaseEnterThe4.setVisible(true);
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							lblPleaseEnterThe4.setVisible(false);
						}
					});
					flag=true;
					t.setRepeats(false);
					t.start();
				}
			    try 
			    {
			    	studentinfo.marks=Integer.parseInt(textPane_2.getText());	
			    }
			    catch(NumberFormatException e1)
				{
			    	lblPleaseEnterThe5.setVisible(true);
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							lblPleaseEnterThe5.setVisible(false);
						}
					});
					flag=true;
					t.setRepeats(false);
					t.start();
				}
			    if(flag)
			    {
			    	flag=false;
			    	return;
			    }
			    studentinfo.nickname=textPane_3.getText();
			    studentinfo.password=textPane_4.getText();
			    try 
			    {
					output.writeObject(studentinfo);
					output.reset();
					n=(boolean)input.readObject();
				} 
			    catch (Exception e1) 
			    {
					e1.printStackTrace();
				}
			    textPane.setText("");
			    textPane2.setText("");
			    textPane3.setText("");
			    textPane_1.setText("");
			    textPane_2.setText("");
			    textPane_3.setText("");
			    textPane_4.setText("");
			    if(n)
			    {
			    	parent.panel.setVisible(true);
			    	thispanel.setVisible(false);
			    	parent.panel.setVisible(true);
			    	thispanel.setVisible(false);
			    }
			    else
			    {
			    	lblPleaseFillValid.setVisible(true);
					
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							lblPleaseFillValid.setVisible(false);
						}
					});
					t.start();
					t.setRepeats(false);
					return;
			    }
			}
		});
		add(btnPutChoice);
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				parent.panel.setVisible(true);
				setVisible(false);
			}
		});
		button.setBackground(new Color(0, 204, 255));
		button.setFocusable(false);
		button.setIcon(new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages/back5.png"));
		button.setBounds(12, 12, 50, 37);
		add(button);
		
		lblNewLabel = new JLabel("( yyyy-mm-dd)");
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setBounds(45, 221, 111, 15);
		add(lblNewLabel);
	}
}
