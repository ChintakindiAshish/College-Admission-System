package AdminSide;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CommonClasses.*;

import java.awt.Font;

import javax.swing.JLabel;

public class CollegeTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public int cnt=1;
	public DefaultTableModel model;
	public JTable table;
	public JLabel lblNewLabel;
	public CollegeTable() {
		
		setLayout(null);
		setBounds(0,0,980,600);
		
		model=new DefaultTableModel();
		table = new JTable(model){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		model.addColumn("Choice No");
        model.addColumn("College Name");
        model.addColumn("Branch Name");
        model.addColumn("Intake");
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        table.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        table.setRowHeight(20);
        JScrollPane j1=new JScrollPane(table);
        j1.setBounds(70,71,830,420);
		add(j1);
		
		lblNewLabel = new JLabel("Entered College List");
		lblNewLabel.setBounds(368, 24, 236, 24);
		lblNewLabel.setFont(new Font("Arial",Font.PLAIN,20));
		add(lblNewLabel);
		
		for(Choice n:ServerCollection.H5.keySet())
		{
			model.addRow(new Object[]{cnt,n.Cc.name,n.Bb.name,n.intake});
			cnt++;
		}
	}

}
