package AdminSide;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class SetTimer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
    public JLabel lbl1,lblTimerinMsec,lblNewLabel;
    public JPanel panel;
    public JButton btnSubmit;
    public JTextPane textPane;
    public int time;
	public SetTimer(College_list parent,ObjectInputStream input,ObjectOutputStream output) {
		setBounds(0,0,980,564);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.focus"));
		panel.setBounds(193, 130, 606, 320);
		add(panel);
		panel.setLayout(null);
		
		lblTimerinMsec = new JLabel("Time (in sec)");
		lblTimerinMsec.setFont(new Font("Arial",Font.PLAIN,20));
		lblTimerinMsec.setBounds(88, 85, 158, 23);
		panel.add(lblTimerinMsec);
		
		textPane = new JTextPane();
		textPane.setBounds(317, 83, 195, 30);
		textPane.setFont(new Font("Arial", Font.PLAIN, 25));
		panel.add(textPane);
		
		lblNewLabel = new JLabel("Please Enter valid the Text");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setVisible(false);
		lblNewLabel.setBounds(318, 115, 198, 15);
		panel.add(lblNewLabel);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
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
				try 
				{
					time=Integer.parseInt(textPane.getText());
				}catch(NumberFormatException nfe)
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
				try
				{
					output.writeObject(time);
					output.reset();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				setVisible(false);
				CollegeEntered coll=new CollegeEntered();
				parent.add(coll);
				coll.setVisible(true);
			}
		});
		btnSubmit.setBounds(230, 177, 121, 37);
		btnSubmit.setFocusable(false);
		btnSubmit.setFont(new Font("Arial",Font.PLAIN,20));
		panel.add(btnSubmit);
		
		lbl1 = new JLabel("Set the Timer");
		lbl1.setForeground(Color.BLUE);
		lbl1.setBounds(394, 47, 176, 30);
		add(lbl1);
		lbl1.setFont(new Font("Arial", Font.PLAIN, 25));
	}
}
