package AdminSide;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;

import CommonClasses.*;

public class SetCutoff extends JPanel {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public Choice c;
	public JLabel lblA,label,lblCollegeName,lblBranchName,lblCollegeChoiceDetails,lblCutoff,lblNewLabel;
	public String cutoff;
	public JTextPane textPane;
	public NoList parentframe;
	public TimeNotOver timenotover;
	public JButton btnAdd;
	public SetCutoff(NoList parent,NoList2 parent2,ObjectInputStream input,ObjectOutputStream output,boolean flag) {

		parentframe=parent;
		Vector<Choice> v=new Vector<Choice>();
		
		setLayout(null);
		setBounds(0,0,980,564);
		
		timenotover=new TimeNotOver();
		add(timenotover);
		
		lblCollegeName = new JLabel("College Name");
		lblCollegeName.setBounds(138, 138, 162, 23);
		lblCollegeName.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblCollegeName);
		
		lblBranchName = new JLabel("Branch Name");
		lblBranchName.setBounds(138, 204, 147, 23);
		lblBranchName.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblBranchName);
		
		lblCollegeChoiceDetails = new JLabel("College Choice Details");
		lblCollegeChoiceDetails.setForeground(Color.BLUE);
		lblCollegeChoiceDetails.setBounds(342, 46, 300, 30);
		lblCollegeChoiceDetails.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lblCollegeChoiceDetails);
		
		lblCutoff = new JLabel("Cutoff");
		lblCutoff.setBounds(155, 275, 81, 23);
		lblCutoff.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblCutoff);
		
		textPane = new JTextPane();
		textPane.setBounds(374, 268, 338, 36);
		textPane.setFont(new Font("Arial", Font.BOLD, 16));
		textPane.setBackground(new Color(179, 217, 255));
		add(textPane);
		
		for(Choice n:ServerCollection.H5.keySet())
		{
			v.add(n);
		}
		final Iterator<Choice> i=v.iterator();
		c=i.next();
		lblA = new JLabel(c.Cc.name);
		lblA.setBounds(374, 131, 483, 36);
		lblA.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblA);
		
		label = new JLabel(c.Bb.name);
		label.setBounds(374, 197, 483, 36);
		label.setFont(new Font("Serif", Font.PLAIN, 20));
		add(label);
		
		lblNewLabel = new JLabel("Please Enter valid the Text");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setVisible(false);
		lblNewLabel.setBounds(374, 304, 198, 15);
		add(lblNewLabel);
		
		btnAdd = new JButton("Add Cutoff");
		btnAdd.setBounds(327, 361, 175, 36);
		btnAdd.setFont(new Font("Arial",Font.PLAIN, 20));
		btnAdd.setFocusable(false);
		btnAdd.addActionListener(new ActionListener(){
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
				
				if(textPane.getText().isEmpty())
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
				try {
				cutoff = textPane.getText();
				ServerCollection.H5.put(c,Integer.parseInt(cutoff));
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
				if(i.hasNext())
				{
					c=i.next();
					lblA.setText(c.Cc.name);
					label.setText(c.Bb.name);
				}
				else
				{
					try 
					{
						new SetResult(input,output);
					} catch (Exception e21) 
					{
						e21.printStackTrace();
					}
					CutoffEntered coll=new CutoffEntered();
					parent.add(coll);
					setVisible(false);
					CutoffTable table2=new CutoffTable();
					parent2.removeAll();
					parent2.add(table2);
					CutoffHashmap cutoffhash=new CutoffHashmap(ServerCollection.H5);
					try 
					{
						output.writeObject(cutoffhash);
						output.reset();
					} catch (IOException e1)
					{
						e1.printStackTrace();
					}
				}
				textPane.setText(null);
			}
		});
		add(btnAdd);
		
		textPane.setVisible(false);
		btnAdd.setVisible(false);
		
		if(!flag)
		{
			for(Choice n:ServerCollection.H5.keySet())
			{
				if(ServerCollection.H5.get(n)==null)
				{
					timenotover.setVisible(true);
					if(!ServerCollection.flag)
					{
						setVisible(true);
						timenotover.setVisible(false);
					}
					return;
				}
			}
			CutoffEntered coll=new CutoffEntered();
			parent.add(coll);
			setVisible(false);
			CutoffTable table2=new CutoffTable();
			parent2.removeAll();
			parent2.add(table2);
		}
		if(ServerCollection.flag)
		{
			timenotover.setVisible(true);
		}
	}
	public void refresh(boolean flag)
	{
		if(!flag)
		{
			for(Choice n:ServerCollection.H5.keySet())
			{
				if(ServerCollection.H5.get(n)==null)
				{
					if(!ServerCollection.flag)
					{
						setVisible(true);
						timenotover.setVisible(false);
						textPane.setVisible(true);
						btnAdd.setVisible(true);
					}
					else
						return;
				}
			}
		}
	}
}
