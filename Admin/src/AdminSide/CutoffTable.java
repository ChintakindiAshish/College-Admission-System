package AdminSide;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CommonClasses.*;

public class CutoffTable extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	int cnt=1;
	public DefaultTableModel model;
	public JTable table;
	public JLabel lblNewLabel;
	public CutoffTable() {

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
        model.addColumn("Cutoff");
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        table.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        table.setRowHeight(20);
        JScrollPane j1=new JScrollPane(table);
        j1.setBounds(70,71,830,420);
		add(j1);
		
		lblNewLabel = new JLabel("Cutoff List of Colleges");
		lblNewLabel.setBounds(360, 24, 236, 24);
		lblNewLabel.setFont(new Font("Arial",Font.PLAIN,20));
		add(lblNewLabel);
		
		for(Choice n:ServerCollection.H5.keySet())
		{
			model.addRow(new Object[]{cnt,n.Cc.name,n.Bb.name,n.intake,ServerCollection.H5.get(n)});
			cnt++;
		}

	}

}
