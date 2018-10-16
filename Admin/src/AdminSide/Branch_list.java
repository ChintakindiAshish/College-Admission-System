package AdminSide;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;

import CommonClasses.Branch;
import CommonClasses.Choice;
import CommonClasses.College;
import CommonClasses.CollegeHashmap;
import CommonClasses.ServerCollection;

public class Branch_list extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	HashMap<String,Integer> h3=new HashMap<String,Integer>();
	
	public String text1,intake2;
	public int intake;
	public CollegeTable table1;
	public JLabel lblFillTheFollowing,lblBranchName,lblPleaseEnterThe,lblNewLabel,lblIntake;
	public JTextPane textPane,textPane_1;
	public JButton btnAddNewCollege,btnNewButton_1,btnAddBranch;
	public Branch B;
	public Choice c1;
	public Branch_list(College_list parent,NoList parent2,NoList2 parent3,ObjectInputStream input,ObjectOutputStream output,boolean flag) {

		setLayout(null);
		setBounds(0,0,980,564);
		
		lblFillTheFollowing = new JLabel("Fill the Following Information");
		lblFillTheFollowing.setBounds(301, 50, 410, 30);
		lblFillTheFollowing.setForeground(Color.BLUE);
		lblFillTheFollowing.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lblFillTheFollowing);
		
		lblBranchName = new JLabel("Branch Name");
		lblBranchName.setBounds(120, 158, 153, 25);
		lblBranchName.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblBranchName);
		
		textPane = new JTextPane();
		textPane.setBounds(330, 158, 479, 36);
		textPane.setFont(new Font("Arial", Font.BOLD, 16));
		textPane.setBackground(new Color(179, 217, 255));
		add(textPane);
		
		textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("Dialog", Font.BOLD, 16));
		textPane_1.setBackground(new Color(179, 217, 255));
		textPane_1.setBounds(330, 241, 479, 36);
		add(textPane_1);
		
		lblPleaseEnterThe = new JLabel("Please Enter valid the Text");
		lblPleaseEnterThe.setForeground(Color.RED);
		lblPleaseEnterThe.setVisible(false);
		lblPleaseEnterThe.setBounds(330, 197, 225, 15);
		add(lblPleaseEnterThe);
		
		lblNewLabel = new JLabel("Please Enter valid the Text");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setVisible(false);
		lblNewLabel.setBounds(330, 279, 198, 15);
		add(lblNewLabel);
		
		btnAddNewCollege = new JButton("Add new College");
		btnAddNewCollege.setEnabled(false);
		btnAddNewCollege.addActionListener(new ActionListener() {
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
				College C=new College(parent.text1);
				for(String n:h3.keySet())
				{
					intake=h3.get(n);
					B=new Branch(n);
					Choice c1=new Choice(C,B,intake);
					ServerCollection.H5.put(c1,null);
				}
				setVisible(false);
				textPane.setText(null);
				textPane_1.setText(null);
				parent.btnPutChoice.setVisible(true);
				parent.textPane.setVisible(true);
				parent.lblCollegeName.setVisible(true);
				h3.clear();
			}
		});
		btnAddNewCollege.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnAddNewCollege.setFocusable(false);
		btnAddNewCollege.setBounds(546, 346, 245, 36);
		add(btnAddNewCollege);
		
		lblIntake = new JLabel("Intake");
		lblIntake.setFont(new Font("Serif", Font.PLAIN, 20));
		lblIntake.setBounds(120, 241, 153, 25);
		add(lblIntake);
		
		btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
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
				College C=new College(parent.text1);
				for(String n:h3.keySet())
				{
					intake=h3.get(n);
					B=new Branch(n);
					c1=new Choice(C,B,intake);
					ServerCollection.H5.put(c1,null);
				}
				try {
					CollegeHashmap collegehashmap=new CollegeHashmap(ServerCollection.H5,null);
					output.writeObject(collegehashmap);
					output.reset();
				} catch (IOException e) {
					e.printStackTrace();
				}
				table1=new CollegeTable();
				parent3.add(table1);
				table1.setVisible(true);
				parent3.lbl1.setVisible(false);
				parent3.panel.setVisible(false);
				setVisible(false);
				parent.lblCollegeName.setVisible(false);
				parent.lblFillTheFollowing.setVisible(false);
				parent.textPane.setVisible(false);
				parent.setVisible(false);
				parent.setVisible(true);
				SetTimer settimer=new SetTimer(parent,input,output);
				parent.add(settimer);
				settimer.setVisible(true);
				SetCutoff cutoff=new SetCutoff(parent2,parent3,input,output,true);
				parent2.add(cutoff);
				ServerCollection.cut=cutoff;
				parent2.lbl1.setVisible(false);
				parent2.panel.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(770, 445, 198, 36);
		btnNewButton_1.setFont(new Font("Arial",Font.PLAIN, 20));
		add(btnNewButton_1);
		
		btnAddBranch = new JButton("Add Branch");
		btnAddBranch.setBounds(249, 346, 198, 36);
		btnAddBranch.setFont(new Font("Arial",Font.PLAIN, 20));
		btnAddBranch.setFocusable(false);
		btnAddBranch.addActionListener(new ActionListener()
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
				if(textPane.getText().isEmpty() || textPane_1.getText().isEmpty())
				   {
					if(textPane.getText().isEmpty())
							lblPleaseEnterThe.setVisible(true);
					if(textPane_1.getText().isEmpty())
							lblNewLabel.setVisible(true);
							Timer t=new Timer(2000,new ActionListener() 
							{
								public void actionPerformed(ActionEvent e)
								{
									if(textPane.getText().isEmpty())
										lblPleaseEnterThe.setVisible(false);
									if(textPane_1.getText().isEmpty())
										lblNewLabel.setVisible(false);
								}
							});
							t.setRepeats(false);
							t.start();
							return;
					}
				try {
					text1=textPane.getText();
					intake2=textPane_1.getText();
					h3.put(text1,Integer.parseInt(intake2));
				}
				catch(NumberFormatException e1)
				{
					lblNewLabel.setVisible(true);
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							lblNewLabel.setVisible(false);
						}
					});
					t.setRepeats(false);
					t.start();
					return;
				}
				textPane.setText(null);
				textPane_1.setText(null);
				btnNewButton_1.setEnabled(true);
				btnAddNewCollege.setEnabled(true);
			} 
		});
		add(btnAddBranch);
		
		if(!flag)
		{
			table1=new CollegeTable();
			parent3.add(table1);
			table1.setVisible(true);
			parent3.lbl1.setVisible(false);
			parent3.panel.setVisible(false);
			setVisible(false);
			parent.lblCollegeName.setVisible(false);
			parent.lblFillTheFollowing.setVisible(false);
			parent.textPane.setVisible(false);
			parent.setVisible(false);
			parent.setVisible(true);
			CollegeEntered coll=new CollegeEntered();
			parent.add(coll);
			coll.setVisible(true);
			SetCutoff cutoff=new SetCutoff(parent2,parent3,input,output,flag);
			parent2.add(cutoff);
			ServerCollection.cut=cutoff;
			parent2.lbl1.setVisible(false);
			parent2.panel.setVisible(false);
			textPane.setVisible(false);
			textPane_1.setVisible(false);
		}
	}
}
