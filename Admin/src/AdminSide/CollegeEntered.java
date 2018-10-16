package AdminSide;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class CollegeEntered extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public JLabel lbl1;
	public JPanel panel;
	public CollegeEntered() {
		setBounds(0,0,980,564);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.focus"));
		panel.setBounds(193, 130, 606, 320);
		add(panel);
		panel.setLayout(null);
		
		lbl1 = new JLabel("College List is Entered");
		lbl1.setBounds(155, 119, 278, 30);
		panel.add(lbl1);
		lbl1.setFont(new Font("Arial", Font.PLAIN, 25));
	}
}
