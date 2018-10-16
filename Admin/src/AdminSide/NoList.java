package AdminSide;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class NoList extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public JLabel lbl1,lblCollegeChoiceDetails;
	public JPanel panel;
	public NoList() {
		setBounds(309,180,980,564);
		setLayout(null);
		
		lbl1 = new JLabel("College List Not Entered Yet");
		lbl1.setBounds(320, 250, 370, 30);
		lbl1.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lbl1);
		
		lblCollegeChoiceDetails = new JLabel("College Choice Details");
		lblCollegeChoiceDetails.setForeground(Color.BLUE);
		lblCollegeChoiceDetails.setBounds(342, 46, 300, 30);
		lblCollegeChoiceDetails.setFont(new Font("Arial", Font.PLAIN, 25));
		
		panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.focus"));
		panel.setBounds(193, 130, 606, 320);
		add(panel);
		add(lblCollegeChoiceDetails);
		panel.setLayout(null);
	}
}
