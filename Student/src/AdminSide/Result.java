package AdminSide;

import javax.swing.JPanel;

import CommonClasses.Choice;
import CommonClasses.ServerCollection;

import java.awt.Font;

import javax.swing.JLabel;
import java.awt.Color;

public class Result extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public NoList nolist;
	public NoList3 nolist3;
	public JLabel lblResultNotDisplayed,lblResult,lblAllotedCollege;
	public Result(boolean flag,boolean flag2,StudentInfo student) {

		setLayout(null);
		setBounds(309,180,980,564);
		
		nolist=new NoList();
		add(nolist);
		nolist.setVisible(false);
		
		nolist3=new NoList3();
		add(nolist3);
		nolist3.setVisible(false);
		
		lblResult = new JLabel("Result");
		lblResult.setForeground(Color.BLUE);
		lblResult.setBounds(429, 69, 80, 25);
		lblResult.setFont(new Font("Arial",Font.BOLD,22));
		add(lblResult);
		
		lblAllotedCollege = new JLabel("Alloted College");
		lblAllotedCollege.setBounds(222, 229, 135, 27);
		lblAllotedCollege.setFont(new Font("Arial",Font.PLAIN,17));
		add(lblAllotedCollege);
		
		lblResultNotDisplayed = new JLabel(student.result);
		lblResultNotDisplayed.setBounds(495, 229, 362, 21);
		lblResultNotDisplayed.setFont(new Font("Arial",Font.PLAIN,17));
		add(lblResultNotDisplayed);
		
		if(!flag)
		{
			lblResultNotDisplayed.setVisible(false);
			lblAllotedCollege.setVisible(false);
			lblResult.setVisible(false);
			nolist.setVisible(true);
		}
		else if(!flag2)
		{
			lblResultNotDisplayed.setVisible(false);
			lblAllotedCollege.setVisible(false);
			lblResult.setVisible(false);
			nolist3.setVisible(true);
		}
	}
	public void refresh(boolean flag,boolean flag2,StudentInfo student)
	{
		lblResultNotDisplayed.setText(student.result);
		
		if(!flag)
		{
			if(!ServerCollection.H4.isEmpty())
			{
				nolist3.setVisible(true);
				nolist.setVisible(false);
				for(Choice n:ServerCollection.H5.keySet())
				{
					if(ServerCollection.H5.get(n)!=null)
					{
						nolist3.setVisible(false);
						lblResultNotDisplayed.setVisible(true);
						lblAllotedCollege.setVisible(true);
						lblResult.setVisible(true);
						break;
					}
				}
			}
		}
		else if(!flag2)
		{
			for(Choice n:ServerCollection.H5.keySet())
			{
				if(ServerCollection.H5.get(n)!=null)
				{
					nolist3.setVisible(false);
					lblResultNotDisplayed.setVisible(true);
					lblAllotedCollege.setVisible(true);
					lblResult.setVisible(true);
					break;
				}
			}
		}
	}
}
