package AdminSide;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TimeOut1 extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public JLabel lbl1,lblYouHaventSelected;
	public JPanel panel;
	public TimeOut1() {
		setBounds(309,180,980,564);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.focus"));
		panel.setBounds(193, 130, 606, 320);
		add(panel);
		panel.setLayout(null);
		
		lbl1 = new JLabel("Addmission process is closed");
		lbl1.setBounds(117, 120, 451, 30);
		panel.add(lbl1);
		lbl1.setFont(new Font("Arial", Font.PLAIN, 25));
		
		lblYouHaventSelected = new JLabel("You did not select the choices before");
		lblYouHaventSelected.setBounds(68, 162, 463, 30);
		lblYouHaventSelected.setFont(new Font("Arial", Font.PLAIN, 25));
		lblYouHaventSelected.setVisible(false);
		panel.add(lblYouHaventSelected);
	}
}
