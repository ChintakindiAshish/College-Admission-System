package AdminSide;

import CommonClasses.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.HashMap;
import javax.swing.Timer;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import javax.swing.JButton;

public class College_list extends JPanel{

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public Container c2;
	public String text1;
	public Branch b;
	public int intake;
	public HashMap<College,Vector<Branch>> H4=new HashMap<College,Vector<Branch>>();
	public JLabel lblFillTheFollowing,lblCollegeName,lblPleaseEnterThe;
	public JTextPane textPane;
	public JButton btnPutChoice;
	public Branch_list p1;
	public College_list(NoList panel,NoList2 panel2,ObjectInputStream input,ObjectOutputStream output,boolean flag) {
		
		setLayout(null);
		setBounds(309,180,980,564);
		
		lblFillTheFollowing = new JLabel("Fill the Following Information");
		lblFillTheFollowing.setBounds(301, 50, 410, 30);
		lblFillTheFollowing.setForeground(Color.BLUE);
		lblFillTheFollowing.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lblFillTheFollowing);
		
		lblCollegeName = new JLabel("College Name");
		lblCollegeName.setBounds(120, 158, 153, 25);
		lblCollegeName.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblCollegeName);
		
		textPane = new JTextPane();
		textPane.setBounds(330, 158, 479, 36);
		textPane.setFont(new Font("Arial", Font.BOLD, 16));
		textPane.setBackground(new Color(179, 217, 255));
		add(textPane);
		
		lblPleaseEnterThe = new JLabel("Please Enter the Text");
		lblPleaseEnterThe.setForeground(Color.RED);
		lblPleaseEnterThe.setVisible(false);
		lblPleaseEnterThe.setBounds(330, 197, 225, 15);
		add(lblPleaseEnterThe);
		
		p1=new Branch_list(this,panel,panel2,input,output,flag);
		add(p1);
		p1.setLayout(null);
		p1.setVisible(false);
		
		btnPutChoice = new JButton("Add College");
		btnPutChoice.setBounds(349, 257, 198, 36);
		btnPutChoice.setFont(new Font("Arial",Font.PLAIN, 20));
		btnPutChoice.setFocusable(false);
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
			   if(textPane.getText().isEmpty())
			   {
						lblPleaseEnterThe.setVisible(true);
						Timer t=new Timer(2000,new ActionListener() 
						{
							public void actionPerformed(ActionEvent e)
							{
								  lblPleaseEnterThe.setVisible(false);
							}
						});
						t.start();
						return;
				}
				text1=textPane.getText();
				p1.setVisible(true);
				textPane.setText(null);
				textPane.setVisible(false);
				btnPutChoice.setVisible(false);
				lblCollegeName.setVisible(false);
			    } 
		});
		add(btnPutChoice);
		
		if(!flag)
		{
			p1.setVisible(true);
			textPane.setVisible(false);
			btnPutChoice.setVisible(false);
		}
	}
}
