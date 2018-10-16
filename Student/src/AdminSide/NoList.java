package AdminSide;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class NoList extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public JLabel lbl1;
	public JPanel panel;
	public NoList() {
		setBounds(0,0,980,564);
		setLayout(null);
		
		lbl1 = new JLabel("Addmission process not started yet");
		lbl1.setBounds(266, 250, 451, 30);
		lbl1.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lbl1);
		
		panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.focus"));
		panel.setBounds(193, 130, 606, 320);
		add(panel);
		panel.setLayout(null);
	}
}
