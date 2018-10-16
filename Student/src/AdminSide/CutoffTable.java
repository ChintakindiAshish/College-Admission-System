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
	public int cnt=1;
	public DefaultTableModel model;
	public JTable table;
	public JScrollPane j1;
	public JLabel lblNewLabel;
	public NoList nolist;
	public NoList2 nolist2;
	public CutoffTable(boolean flag,boolean flag2) {

		setLayout(null);
		setBounds(309,180,980,600);
		
		nolist2=new NoList2();
		add(nolist2);
		nolist2.setVisible(false);
		
		nolist=new NoList();
		add(nolist);
		nolist.setVisible(false);
		
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
        j1=new JScrollPane(table);
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
		
		if(!flag)
		{
			lblNewLabel.setVisible(false);
			j1.setVisible(false);
			nolist.setVisible(true);
		}
		else if(!flag2)
		{
			lblNewLabel.setVisible(false);
			j1.setVisible(false);
			nolist2.setVisible(true);
		}
	}
	public void refresh(boolean flag,boolean flag2)
	{
		int rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		
		for(Choice n:ServerCollection.H5.keySet())
		{
			model.addRow(new Object[]{cnt,n.Cc.name,n.Bb.name,n.intake,ServerCollection.H5.get(n)});
			cnt++;
		}
		
		if(!flag)
		{
			if(!ServerCollection.H4.isEmpty())
			{
				nolist2.setVisible(true);
				nolist.setVisible(false);
				for(Choice n:ServerCollection.H5.keySet())
				{
					if(ServerCollection.H5.get(n)!=null)
					{
						nolist2.setVisible(false);
						lblNewLabel.setVisible(true);
						j1.setVisible(true);
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
					nolist2.setVisible(false);
					lblNewLabel.setVisible(true);
					j1.setVisible(true);
					break;
				}
			}
		}
	}
}
