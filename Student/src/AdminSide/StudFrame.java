package AdminSide;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import CommonClasses.Choice;
import CommonClasses.CollegeHashmap;
import CommonClasses.ServerCollection;
import Login.Login;

public class StudFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public ShowDetails showdetails;
	public boolean flag=true,flag2=true;
	public Choices c1;
	public StudentInfo student;
	public CollegeHashmap collegehash;
	public ResultData resultdata;
	public Dimension screenSize;
	public ImageIcon icon,icon2,icon3,icon4;
	public Object obj;
	public Result r;
	public TimeOut1 timeout;
	public CutoffTable cutofftable;
	public JLabel lblAdmission,lblAdmission2,lblAdmission3,lblAdmission4,label_1;
	public JSeparator separator,separator_1;
	public JPanel panel,panel_2,panel_1;
	public JButton btnNewButton,btnEnterCutoffOf,button,btnLogout,button_1,btnDisplayCutoffList;

	public StudFrame(Login parent,ObjectInputStream input,ObjectOutputStream output) throws Exception{
		
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setTitle("Admission System");
		ImageIcon icon=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages/index.png");
		setIconImage(icon.getImage());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		icon2=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages/12.jpg");
		JLabel label=new JLabel(icon2);
		label.setBounds(75, 3, 120, 100);
		contentPane.setLayout(null);
		contentPane.add(label);
		
		for(int i=0;i<2;i++)
		{
			obj=input.readObject();
			if(obj instanceof StudentInfo)
			{
				student=(StudentInfo)obj;
			}
			else if(obj instanceof CollegeHashmap)
			{
				collegehash=(CollegeHashmap)obj;
				if(((CollegeHashmap)obj).H4.isEmpty())
				{
					flag=false;
				}
				else
				{
					for(Choice n:((CollegeHashmap)obj).H5.keySet())
					{
						if(((CollegeHashmap)obj).H5.get(n)==null)
						{
							flag2=false;
							break;
						}
					}
					ServerCollection.H4=((CollegeHashmap)obj).H4;
					ServerCollection.H5=((CollegeHashmap)obj).H5;
				}
			}
		}
		
		showdetails=new ShowDetails(input,output,student);
		contentPane.add(showdetails);
		showdetails.setVisible(false);
		
		icon3=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages/pp4.png");
		JLabel lblNewLabel = new JLabel(icon3);
		lblNewLabel.setBounds(1170, 3, 100, 100);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		
		c1=new Choices(input,output,flag,student);
		contentPane.add(c1);
		c1.setLayout(null);
		c1.setVisible(false);
		
		r=new Result(flag,flag2,student);
		contentPane.add(r);
		r.setLayout(null);
		r.setVisible(false);
		
		timeout=new TimeOut1();
		contentPane.add(timeout);
		timeout.setLayout(null);
		timeout.setVisible(false);
		
		cutofftable=new CutoffTable(flag,flag2);
		contentPane.add(cutofftable);
		cutofftable.setVisible(false);
		
		lblAdmission = new JLabel("Government of Maharashtra");
		lblAdmission.setBounds(570, 15, 300, 20);
		lblAdmission.setFont(new Font("Arial", Font.PLAIN, 17));
		contentPane.add(lblAdmission);
		
		lblAdmission2 = new JLabel("Director Of Technical Education,Maharashtra State");
		lblAdmission2.setBounds(370, 55, 800, 25);
		lblAdmission2.setFont(new Font("Bold", Font.PLAIN, 25));
		contentPane.add(lblAdmission2);
		
		lblAdmission3 = new JLabel("Student Portal");
		lblAdmission3.setBounds(700,127	, 300, 32);
		lblAdmission3.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(lblAdmission3);
		
		separator = new JSeparator();
		separator.setBounds(0, 115, 1342, 100);separator.setForeground(Color.BLACK);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 103, 1342, 100);separator_1.setForeground(Color.BLACK);
		contentPane.add(separator_1);
		
		panel = new JPanel();
		panel.setBounds(0, 115, 300, 758);
		contentPane.add(panel);panel.setBackground(new Color(51, 153, 255));
		panel.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBounds(309, 180, 980, 564);
		panel_2.setLayout(null);
		contentPane.add(panel_2);
		
		btnNewButton = new JButton("Enter Personal Detail");
		btnNewButton.setBounds(0, 60, 300, 60);btnNewButton.setFont(new Font("Arial", Font.PLAIN, 23));
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener()
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
						panel_2.setVisible(false);
						showdetails.setVisible(true);
						r.setVisible(false);
						c1.setVisible(false);
						cutofftable.setVisible(false);
						timeout.setVisible(false);
					} 
				});
		panel.add(btnNewButton);
		
		btnEnterCutoffOf = new JButton("Check the Result");
		btnEnterCutoffOf.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				
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
				c1.setVisible(false);
				panel_2.setVisible(false);
				showdetails.setVisible(false);
				cutofftable.setVisible(false);
				timeout.setVisible(false);
				r.setVisible(true);
				try 
				{
					output.writeObject("Student");
					output.reset();
					resultdata=(ResultData)input.readObject();
					student=resultdata.student;
					ServerCollection.H4=resultdata.H4;
					ServerCollection.H5=resultdata.H5;
					if(!resultdata.flag && resultdata.student.choice.isEmpty() && !resultdata.flag2)
					{
						r.setVisible(false);
						timeout.setVisible(true);
						timeout.lblYouHaventSelected.setVisible(true);
						return;
					}
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				r.refresh(flag,flag2,student);
			}
		});
		btnEnterCutoffOf.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnEnterCutoffOf.setFocusable(false);
		btnEnterCutoffOf.setBounds(0, 178, 300, 60);
		panel.add(btnEnterCutoffOf);
		
		button = new JButton("Select the Choices");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
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
				
				panel_2.setVisible(false);
				showdetails.setVisible(false);
				r.setVisible(false);
				cutofftable.setVisible(false);
				c1.setVisible(true);
				timeout.setVisible(false);
				try 
				{
					output.writeObject("Choice");
					output.reset();
					resultdata=(ResultData)input.readObject();
					ServerCollection.H4=resultdata.H4;
					ServerCollection.H5=resultdata.H5;
					if(!resultdata.flag && resultdata.student.choice.isEmpty() && !resultdata.flag2)
					{
						c1.setVisible(false);
						timeout.setVisible(true);
						timeout.lblYouHaventSelected.setVisible(false);
						return;
					}
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				try
				{
					c1.refresh(input,output,flag,flag2,resultdata.student);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		button.setFont(new Font("Dialog", Font.PLAIN, 23));
		button.setFocusable(false);
		button.setBounds(0, 119, 300, 60);
		panel.add(button);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
				
				setVisible(false);
			    Login frame;
				try {
					frame = new Login();
					frame.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    dispose();
			}
		});
		btnLogout.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnLogout.setFocusable(false);
		btnLogout.setBounds(0, 292, 300, 60);
		panel.add(btnLogout);
		
		button_1 = new JButton("Home");
		button_1.setFont(new Font("Dialog", Font.PLAIN, 23));
		button_1.setFocusable(false);
		button_1.addActionListener(new ActionListener()
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
				panel_2.setVisible(true);
				showdetails.setVisible(false);
				c1.setVisible(false);
				r.setVisible(false);
				cutofftable.setVisible(false);
				timeout.setVisible(false);
			}
		});
		button_1.setBounds(0, 1, 300, 60);
		panel.add(button_1);
		
		btnDisplayCutoffList = new JButton("Display Cutoff List");
		btnDisplayCutoffList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
				
				panel_2.setVisible(false);
				showdetails.setVisible(false);
				c1.setVisible(false);
				r.setVisible(false);
				cutofftable.setVisible(true);
				timeout.setVisible(false);
				try 
				{
					output.writeObject("CutoffList");
					output.reset();
					collegehash=(CollegeHashmap)input.readObject();
					ServerCollection.H5=collegehash.H5;
					ServerCollection.H4=collegehash.H4;
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				cutofftable.refresh(flag,flag2);
			}
		});
		btnDisplayCutoffList.setBounds(0, 235, 300, 60);
		panel.add(btnDisplayCutoffList);
		btnDisplayCutoffList.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnDisplayCutoffList.setFocusable(false);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 105, 1356, 10);
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1);
		
		lblAdmission4 = new JLabel("<html>The last date for submission of engineering admission form is 30 september 2018.\n Result with cutoff list of colleges will be displayed on 5 october 2018 at 12:00 pm.</html>");
		lblAdmission4.setBounds(150,60,680,80);
		lblAdmission4.setForeground(Color.RED);
		lblAdmission4.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_2.add(lblAdmission4);
		
		icon4=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages/home2.jpg");
		label_1 = new JLabel(icon4);
		label_1.setBounds(90, 185,icon4.getIconWidth(), icon4.getIconHeight());
		panel_2.add(label_1);
		
		
	}
}