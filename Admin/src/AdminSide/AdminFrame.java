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

import CommonClasses.CollegeHashmap;
import CommonClasses.ServerCollection;
import Login.AdminLogin;

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public String name,password;
	boolean flag;
	public AllStudents studentsclass;
	public ImageIcon icon,icon2,icon3,icon4;
	public JLabel label,lblNewLabel,lblAdmission,lblAdmission2,lblAdmission3,lblAdmission5,lblAdmission4;
	public Object obj;
	public NoList nolist1;
	public NoList2 nolist2;
	public SetTimer2 timer;
	public StudentList studentlist;
	public College_list p1;
	public JSeparator separator,separator_1;
	public JPanel panel,panel_2,panel_1;
	public JButton btnNewButton,btnEnterCutoffOf,button,btnLogout,button_1,btnDisplayCollegeList,btnExternallyAddTime;
	

	public AdminFrame(ObjectInputStream input,ObjectOutputStream output) throws Exception{
		
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setTitle("Admission System");
		icon=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages/index.png");
		setIconImage(icon.getImage());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		icon2=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages/12.jpg");
		label=new JLabel(icon2);
		label.setBounds(75, 3, 120, 100);
		contentPane.setLayout(null);
		contentPane.add(label);
		
		icon3=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages/pp4.png");
		lblNewLabel = new JLabel(icon3);
		lblNewLabel.setBounds(1170, 3, 100, 100);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		
		obj=input.readObject();
		if(obj instanceof CollegeHashmap)
		{
			flag=((CollegeHashmap) obj).H5.isEmpty();
			if(!flag)
			{
				ServerCollection.H5=((CollegeHashmap) obj).H5;
			}
		}
		
		nolist1=new NoList();
		contentPane.add(nolist1);
		nolist1.setVisible(false);
		
		nolist2=new NoList2();
		contentPane.add(nolist2);
		nolist2.setVisible(false);
		
		timer=new SetTimer2(input,output);
		contentPane.add(timer);
		timer.setVisible(false);
		
		studentlist=new StudentList();
		contentPane.add(studentlist);
		studentlist.setVisible(false);
		
		p1=new College_list(nolist1,nolist2,input,output,flag);
		contentPane.add(p1);
		p1.setLayout(null);
		p1.setVisible(false);
		
		lblAdmission = new JLabel("Government of Maharashtra");
		lblAdmission.setBounds(540, 15, 300, 20);
		lblAdmission.setFont(new Font("Arial", Font.BOLD, 17));
		contentPane.add(lblAdmission);
		
		lblAdmission2 = new JLabel("Director Of Technical Education,Maharashtra State");
		lblAdmission2.setBounds(325, 55, 800, 25);
		lblAdmission2.setFont(new Font("Bold", Font.BOLD, 25));
		contentPane.add(lblAdmission2);
		
		lblAdmission3 = new JLabel("Admin Portal");
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
		
		btnNewButton = new JButton("Enter College List");
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
						p1.setVisible(true);
						nolist1.setVisible(false);
						nolist2.setVisible(false);
						studentlist.setVisible(false);
						timer.setVisible(false);
					} 
				});
		panel.add(btnNewButton);
		
		btnEnterCutoffOf = new JButton("Display Student Detail");
		btnEnterCutoffOf.addActionListener(new ActionListener() {
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
				p1.setVisible(false);
				nolist1.setVisible(false);
				nolist2.setVisible(false);
				studentlist.setVisible(true);
				timer.setVisible(false);
				try 
				{
					output.writeObject("StudentDetails");
					output.reset();
					studentsclass=(AllStudents)input.readObject();
					ServerCollection.students=studentsclass.info;
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				studentlist.refresh();
			}
		});
		btnEnterCutoffOf.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnEnterCutoffOf.setFocusable(false);
		btnEnterCutoffOf.setBounds(0, 237, 300, 60);
		panel.add(btnEnterCutoffOf);
		
		button = new JButton("Enter Cutoff of College");
		button.addActionListener(new ActionListener() {
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
				p1.setVisible(false);
				nolist1.setVisible(true);
				nolist2.setVisible(false);
				studentlist.setVisible(false);
				timer.setVisible(false);
				try 
				{
					output.writeObject("CheckFlag");
					output.reset();
					ServerCollection.flag=(boolean)input.readObject();
					System.out.println(ServerCollection.flag);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				if(ServerCollection.cut!=null)
				{
					ServerCollection.cut.refresh(ServerCollection.H5.isEmpty());
					System.out.println("Here");
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
				
				setVisible(false);
			    AdminLogin frame;
				try {
					frame = new AdminLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			    dispose();
			}
		});
		btnLogout.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnLogout.setFocusable(false);
		btnLogout.setBounds(0, 355, 300, 60);
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
				p1.setVisible(false);
				nolist1.setVisible(false);
				nolist2.setVisible(false);
				studentlist.setVisible(false);
				timer.setVisible(false);
			}
		});
		button_1.setBounds(0, 1, 300, 60);
		panel.add(button_1);
		
		btnDisplayCollegeList = new JButton("Display College List");
		btnDisplayCollegeList.addActionListener(new ActionListener() {
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
				p1.setVisible(false);
				nolist1.setVisible(false);
				nolist2.setVisible(true);
				studentlist.setVisible(false);
				timer.setVisible(false);
			}
		});
		btnDisplayCollegeList.setBounds(0, 178, 300, 60);
		panel.add(btnDisplayCollegeList);
		btnDisplayCollegeList.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnDisplayCollegeList.setFocusable(false);
		
		btnExternallyAddTime = new JButton("Externally add Time");
		btnExternallyAddTime.addActionListener(new ActionListener() {
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
				p1.setVisible(false);
				nolist1.setVisible(false);
				nolist2.setVisible(false);
				studentlist.setVisible(false);
				timer.setVisible(true);
			}
		});
		btnExternallyAddTime.setBounds(0, 296, 300, 60);
		panel.add(btnExternallyAddTime);
		btnExternallyAddTime.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnExternallyAddTime.setFocusable(false);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 105, 1356, 10);
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1);
		
		lblAdmission5 = new JLabel("..........................................Welcome Admin...........................................");
		lblAdmission5.setBounds(150,60,680,20);
		lblAdmission5.setForeground(Color.RED);
		lblAdmission5.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_2.add(lblAdmission5);
		
		lblAdmission4 = new JLabel("<html>Insert the College list with branches first.\n Then enter the Cutoff list of the choices.\n After this students can select their choices.</html>");
		lblAdmission4.setBounds(150,85,680,50);
		lblAdmission4.setForeground(Color.RED);
		lblAdmission4.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_2.add(lblAdmission4);
		
		icon4=new ImageIcon("/home/ashish/eclipse-workspace/ProjectImages/home2.jpg");
		JLabel label_1 = new JLabel(icon4);
		label_1.setBounds(90, 185,icon4.getIconWidth(), icon4.getIconHeight());
		panel_2.add(label_1);
	}
}