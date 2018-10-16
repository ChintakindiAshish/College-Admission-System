package AdminSide;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class NoList2 extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public JLabel lbl1;
	public JPanel panel;
	public NoList2() {
		setBounds(0,0,980,564);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.focus"));
		panel.setBounds(193, 130, 606, 320);
		add(panel);
		panel.setLayout(null);
		
		lbl1 = new JLabel("Cutoff List Not Displayed Yet");
		lbl1.setBounds(115, 120, 370, 30);
		panel.add(lbl1);
		lbl1.setFont(new Font("Arial", Font.PLAIN, 25));
	}
}
