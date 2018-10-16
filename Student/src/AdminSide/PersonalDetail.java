package AdminSide;
import java.awt.Color;
import java.awt.Container;
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
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;

import CommonClasses.Branch;
import CommonClasses.College;
import CommonClasses.ServerCollection;

public class PersonalDetail extends JPanel{

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public boolean flag=false;
	public Container c2;
	public String c;
	public String name,address,dob,nickname,marks,mob;
	public PersonalDetail thispanel;
	public JTextPane textPane,textPane2,textPane3,textPane_1,textPane_2,textPane_3;
	public JLabel lblFillTheFollowing,lblCollegeName,lblCollegeName2,lblCollegeName3,lblPleaseEnterThe,lblPleaseEnterThe2,lblPleaseEnterThe3,lblPleaseEnterThe4,lblPleaseEnterThe5,lblPleaseEnterThe6,
	lblMobileNumber,lblCetMarks,lblNickName;
	public JButton btnPutChoice;
	public HashMap<College,Vector<Branch>> H4=new HashMap<College,Vector<Branch>>();
	public JLabel label;
	public PersonalDetail(ObjectInputStream input,ObjectOutputStream output,ShowDetails parent,StudentInfo student) {
		
		setBounds(0,0,980,564);
		setLayout(null);
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);
		
		lblFillTheFollowing = new JLabel("Fill the Following Information");
		lblFillTheFollowing.setBounds(340, 20, 410, 30);
		lblFillTheFollowing.setForeground(Color.BLUE);
		lblFillTheFollowing.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lblFillTheFollowing);
		
		lblCollegeName = new JLabel("Name");
		lblCollegeName.setBounds(201, 81, 153, 25);
		lblCollegeName.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblCollegeName);
		
		textPane = new JTextPane();
		textPane.setText(student.name);
		textPane.setBounds(361, 81, 439, 36);
		textPane.setFont(new Font("Arial", Font.PLAIN, 20));
		textPane.setBackground(new Color(179, 217, 255));
		add(textPane);
		
		lblCollegeName2 = new JLabel("Address");
		lblCollegeName2.setBounds(201, 141, 153, 25);
		lblCollegeName2.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblCollegeName2);
		
		textPane2 = new JTextPane();
		textPane2.setText(student.address);
		textPane2.setBounds(361, 141, 439, 36);
		textPane2.setFont(new Font("Arial", Font.PLAIN, 20));
		textPane2.setBackground(new Color(179, 217, 255));
		add(textPane2);
		
		lblCollegeName3 = new JLabel("Date of Birth");
		lblCollegeName3.setBounds(201, 201, 153, 25);
		lblCollegeName3.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblCollegeName3);
		
		
		textPane3 = new JTextPane();
		textPane3.setText(String.valueOf(student.dob));
		textPane3.setBounds(361, 201, 439, 36);
		textPane3.setFont(new Font("Arial", Font.PLAIN, 20));
		textPane3.setBackground(new Color(179, 217, 255));
		add(textPane3);
		
		lblPleaseEnterThe = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe.setBounds(361, 116, 225, 15);
		lblPleaseEnterThe.setForeground(Color.RED);
		lblPleaseEnterThe.setVisible(false);
		add(lblPleaseEnterThe);
		
		lblPleaseEnterThe2 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe2.setBounds(361, 176, 225, 15);
		lblPleaseEnterThe2.setForeground(Color.RED);
		lblPleaseEnterThe2.setVisible(false);
		add(lblPleaseEnterThe2);
		
		lblPleaseEnterThe3 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe3.setBounds(361, 236, 225, 15);
		lblPleaseEnterThe3.setForeground(Color.RED);
		lblPleaseEnterThe3.setVisible(false);
		add(lblPleaseEnterThe3);
		
		lblPleaseEnterThe4 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe4.setBounds(361, 296, 225, 15);
		lblPleaseEnterThe4.setForeground(Color.RED);
		lblPleaseEnterThe4.setVisible(false);
		add(lblPleaseEnterThe4);
		
		lblPleaseEnterThe5 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe5.setBounds(361, 356, 225, 15);
		lblPleaseEnterThe5.setForeground(Color.RED);
		lblPleaseEnterThe5.setVisible(false);
		add(lblPleaseEnterThe5);
		
		lblPleaseEnterThe6 = new JLabel("Please Enter the valid text");
		lblPleaseEnterThe6.setBounds(361, 416, 225, 15);
		lblPleaseEnterThe6.setForeground(Color.RED);
		lblPleaseEnterThe6.setVisible(false);
		add(lblPleaseEnterThe6);
		
		textPane_1 = new JTextPane();
		textPane_1.setBounds(361, 261, 439, 36);
		textPane_1.setText(String.valueOf(student.mob));
		textPane_1.setFont(new Font("Arial", Font.PLAIN, 20));
		textPane_1.setBackground(new Color(179, 217, 255));
		add(textPane_1);
		
		lblMobileNumber = new JLabel("Mobile No");
		lblMobileNumber.setBounds(201, 261, 153, 25);
		lblMobileNumber.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblMobileNumber);
		
		textPane_2 = new JTextPane();
		textPane_2.setText(String.valueOf(student.marks));
		textPane_2.setBounds(361, 321, 439, 36);
		textPane_2.setFont(new Font("Arial", Font.PLAIN, 20));
		textPane_2.setBackground(new Color(179, 217, 255));
		add(textPane_2);
		
		lblCetMarks = new JLabel("CET Marks");
		lblCetMarks.setBounds(201, 321, 140, 25);
		lblCetMarks.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblCetMarks);
		
		textPane_3 = new JTextPane();
		textPane_3.setText(student.nickname);
		textPane_3.setBounds(361, 381, 439, 36);
		textPane_3.setFont(new Font("Arial", Font.PLAIN, 20));
		textPane_3.setBackground(new Color(179, 217, 255));
		add(textPane_3);
		
		lblNickName = new JLabel("Nick Name");
		lblNickName.setBounds(201, 381, 153, 25);
		lblNickName.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblNickName);
		
		btnPutChoice = new JButton("Submit");
		btnPutChoice.setBounds(450, 445, 198, 36);
		btnPutChoice.setFont(new Font("Arial",Font.BOLD, 20));
		btnPutChoice.setFocusable(true);
		ServerCollection.b1=btnPutChoice;
		thispanel=this;
		btnPutChoice.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e2) {
				
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
					   || textPane3.getText().isEmpty() ||textPane_1.getText().isEmpty() || textPane_2.getText().isEmpty() ||textPane_3.getText().isEmpty())
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
							}
						});
						t.start();
						t.setRepeats(false);
						return;
				}
			   	student.name=textPane.getText();
				student.address=textPane2.getText();
				student.dob=textPane3.getText();
			   try 
			    {
				    formatter.parse(student.dob);
				    c=Character.toString(student.dob.charAt(student.dob.length()-1));
				    if((student.dob.length()!=10 && student.dob.length()!=9 && student.dob.length()!=8) || !c.matches("[0-9]+"))
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
				} catch (ParseException e1) 
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
			    	student.mob=Long.parseLong(textPane_1.getText());
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
			    	student.marks=Integer.parseInt(textPane_2.getText());	
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
				student.nickname=textPane_3.getText();
				student.choice=null;
				setVisible(false);
				parent.lblNewLabel.setText(student.name);
				parent.label.setText(student.address);
				parent.label_1.setText(student.dob);
				parent.label_2.setText(String.valueOf(student.mob));
				parent.label_3.setText(String.valueOf(student.marks));
				parent.label_4.setText(student.nickname);
				parent.lblNewLabel.setVisible(true);
				parent.label.setVisible(true);
				parent.label_1.setVisible(true);
				parent.label_2.setVisible(true);
				parent.label_3.setVisible(true);
				parent.label_4.setVisible(true);
				parent.btnEditProfile.setVisible(true);
				try 
				{
					output.writeObject(student);
					output.reset();
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			 }
		});
		add(btnPutChoice);
		
		label = new JLabel("( yyyy-mm-dd)");
		label.setForeground(Color.BLACK);
		label.setBounds(201, 222, 111, 15);
		add(label);
	}
}