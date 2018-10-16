package AdminSide;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import CommonClasses.Branch;
import CommonClasses.Choice;
import CommonClasses.College;
import CommonClasses.ServerCollection;

public class Choices extends JPanel{

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public String item,item2,item3;
	public Choices thisobject;
	public College c;
	public Vector<Choice> choicelist=new Vector<Choice>();
	public Vector<College> collegenames=new Vector<College>();
	public Vector<Branch> branchnames=new Vector<Branch>();
	public Vector<String> choice=new Vector<String>();
	public Iterator<College> i;
	public Iterator<Branch> i2;
	public JLabel lblFillTheFollowing,lblCollegeName,lblBranchName,lblThisChoiceIs,lblThisChoiceIs2;
	public JComboBox<String> comboBox,comboBox_1;
	public JButton btnAdd,btnSubmit;
	public NoList nolist;
	public PriorityTable pt;
	public Choices(ObjectInputStream input,ObjectOutputStream output,boolean flag,StudentInfo student) throws Exception {
		
		setLayout(null);
		setBounds(309,180,980,564);
		
		lblFillTheFollowing = new JLabel("Fill the Following Information");
		lblFillTheFollowing.setBounds(301, 50, 410, 30);
		lblFillTheFollowing.setForeground(Color.BLUE);
		lblFillTheFollowing.setFont(new Font("Arial", Font.PLAIN, 25));
		ServerCollection.l5=lblFillTheFollowing;
		add(lblFillTheFollowing);
		
		lblCollegeName = new JLabel("College Name");
		lblCollegeName.setBounds(120, 158, 153, 25);
		lblCollegeName.setFont(new Font("Serif", Font.PLAIN, 20));
		ServerCollection.l4=lblCollegeName;
		add(lblCollegeName);
		
		lblBranchName = new JLabel("Branch Name");
		lblBranchName.setFont(new Font("Serif", Font.PLAIN, 20));
		lblBranchName.setBounds(120, 239, 153, 25);
		add(lblBranchName);
		
		lblThisChoiceIs = new JLabel("This choice is already added");
		lblThisChoiceIs.setForeground(Color.RED);
		lblThisChoiceIs.setBounds(385, 300, 211, 15);
		add(lblThisChoiceIs);
		lblThisChoiceIs.setVisible(false);
		
		lblThisChoiceIs2 = new JLabel("Please select valid choice");
		lblThisChoiceIs2.setForeground(Color.RED);
		lblThisChoiceIs2.setBounds(385, 300, 211, 15);
		add(lblThisChoiceIs2);
		lblThisChoiceIs2.setVisible(false);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(329, 239, 428, 30);
		comboBox_1.addItem("<Select Branch>");
		add(comboBox_1);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(329, 157, 428, 30);
		comboBox.addItem("<Select College>");
		for(College n:ServerCollection.H4.keySet())
		{
			comboBox.addItem(n.name);
			collegenames.add(n);
		}
		comboBox.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource()==comboBox && e.getStateChange() == ItemEvent.SELECTED)
				{
					comboBox_1.removeAllItems();
					comboBox_1.addItem("<Select Branch>");
					for(College c:ServerCollection.H4.keySet())
					{
						if(comboBox.getSelectedItem().equals(c.name))
						{
							branchnames=ServerCollection.H4.get(c);
							i2=branchnames.iterator();
							while(i2.hasNext())
							{
								comboBox_1.addItem(i2.next().name);
							}
						}	
					}
				}
			}
		});
		add(comboBox);
		
		btnAdd = new JButton("Add");
		btnAdd.setFocusable(false);
		btnAdd.setBounds(301, 330, 117, 30);
		btnAdd.setFont(new Font("Arial",Font.PLAIN,20));
		btnAdd.addActionListener(new ActionListener() {
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
			
				item=(String)comboBox.getSelectedItem();
				College C=new College(item);
				item2=(String)comboBox_1.getSelectedItem();
				
				if(item.equals("<Select College>") || item2.equals("<Select Branch>"))
				{
					lblThisChoiceIs2.setVisible(true);
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							lblThisChoiceIs2.setVisible(false);
						}
					});
					t.setRepeats(false);
					t.start();
					return;
				}
				
				item3=item+item2;
				if(choice.contains(item3))
				{
					lblThisChoiceIs.setVisible(true);
					Timer t=new Timer(2000,new ActionListener() 
					{
						public void actionPerformed(ActionEvent e)
						{
							lblThisChoiceIs.setVisible(false);
						}
					});
					t.setRepeats(false);
					t.start();
					return;
				}
				choice.add(item3);
				Branch B=new Branch(item2);
				Choice ch=new Choice(C,B,0);
				choicelist.addElement(ch);
			}
		});
		add(btnAdd);
		
		thisobject=this;
		btnSubmit = new JButton("Submit");
		btnSubmit.setFocusable(false);
		btnSubmit.setBounds(559, 330, 117, 30);
		btnSubmit.setFont(new Font("Arial",Font.PLAIN,20));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SelectedChoices SC;
				try 
				{
					SC = new SelectedChoices(thisobject,input,output,student);
					add(SC);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				thisobject.setVisible(false);
				thisobject.setVisible(true);
			}
		});
		add(btnSubmit);
		
		nolist=new NoList();
		add(nolist);
		nolist.setLayout(null);
		nolist.setVisible(false);
		
		if(!flag)
		{
			lblFillTheFollowing.setVisible(false);
			lblCollegeName.setVisible(false);
			lblBranchName.setVisible(false);
			comboBox.setVisible(false);
			comboBox_1.setVisible(false);
			btnAdd.setVisible(false);
			btnSubmit.setVisible(false);
			nolist.setVisible(true);
		}
		
		if(!student.choice.isEmpty())
		{
			lblFillTheFollowing.setVisible(false);
			lblCollegeName.setVisible(false);
			lblBranchName.setVisible(false);
			comboBox.setVisible(false);
			comboBox_1.setVisible(false);
			btnAdd.setVisible(false);
			btnSubmit.setVisible(false);
			ServerCollection.choice=student.choice;
			pt = new PriorityTable(input,output,student,false);
			add(pt);
		}
	}
	public void refresh(ObjectInputStream input,ObjectOutputStream output,boolean flag,boolean flag2,StudentInfo student) throws Exception
	{
		comboBox.removeAllItems();
		comboBox.addItem("<Select College>");
		for(College n:ServerCollection.H4.keySet())
		{
			comboBox.addItem(n.name);
			collegenames.add(n);
		}
		if(!flag)
		{
			if(!ServerCollection.H4.isEmpty())
			{
				nolist.setVisible(false);
				if(student.choice.isEmpty())
				{
					lblFillTheFollowing.setVisible(true);
					lblCollegeName.setVisible(true);
					lblBranchName.setVisible(true);
					comboBox.setVisible(true);
					comboBox_1.setVisible(true);
					btnAdd.setVisible(true);
					btnSubmit.setVisible(true);
				}
			}
		}
		
		if(!student.choice.isEmpty())
		{
			lblFillTheFollowing.setVisible(false);
			lblCollegeName.setVisible(false);
			lblBranchName.setVisible(false);
			comboBox.setVisible(false);
			comboBox_1.setVisible(false);
			btnAdd.setVisible(false);
			btnSubmit.setVisible(false);
			ServerCollection.choice=student.choice;
			pt = new PriorityTable(input,output,student,false);
			add(pt);
		}
	}
}
