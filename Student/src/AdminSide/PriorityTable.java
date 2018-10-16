package AdminSide;

import java.awt.Font;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CommonClasses.Choice;
import CommonClasses.ServerCollection;
import javax.swing.JLabel;
import java.awt.Color;

public class PriorityTable extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public int cnt=1;
	public JTable table;
	public DefaultTableModel model;
	public JScrollPane j1;
	public JLabel lblSelectedChoices;
	public Choice ch;
	public PriorityTable(ObjectInputStream input,ObjectOutputStream output,StudentInfo student,boolean flag) throws Exception {

		setLayout(null);
		setBounds(0,0,980,564);
		
		model=new DefaultTableModel(); 
		table = new JTable(model){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {
	                return false;               
	        };
	    };
		model.addColumn("Priority");
        model.addColumn("College Name");
        model.addColumn("Branch Name");
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        table.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        table.setRowHeight(20);
        
        j1=new JScrollPane(table);
        j1.setBounds(70,71,830,420);
		add(j1);
		
		lblSelectedChoices = new JLabel("Selected Choices");
		lblSelectedChoices.setForeground(Color.BLUE);
		lblSelectedChoices.setBounds(360, 27, 253, 32);
		lblSelectedChoices.setFont(new Font("Arial",Font.BOLD,25));
		add(lblSelectedChoices);
		
		student.choice=new Vector<Choice>();
		Iterator<Choice> i=ServerCollection.choice.iterator();
		while(i.hasNext())
		{
			ch=i.next();
			if(flag)
			    student.choice.add(ch);
			model.addRow(new Object[]{cnt,ch.Cc.name,ch.Bb.name});
			cnt++;
		}
		if(flag)
		{
			output.writeObject(student);
			output.reset();
		}
	}

}
