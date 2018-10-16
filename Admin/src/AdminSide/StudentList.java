package AdminSide;

import java.awt.Font;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CommonClasses.ServerCollection;

public class StudentList extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public StudentInfo student;
	public DefaultTableModel model;
	public JTable table;
	public JScrollPane j1;
	public JLabel lblNewLabel;
	public StudentList() {

		setLayout(null);
		setBounds(309,180,980,564);
		
		model=new DefaultTableModel();
		table = new JTable(model){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Address");
        model.addColumn("Mob.");
        model.addColumn("DOB");
        model.addColumn("Marks");
        model.addColumn("Result");
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        table.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        table.setRowHeight(20);
        j1=new JScrollPane(table);
        j1.setBounds(70,71,830,420);
		add(j1);
		
		lblNewLabel = new JLabel("Registered Student List");
		lblNewLabel.setBounds(360, 24, 236, 24);
		lblNewLabel.setFont(new Font("Arial",Font.PLAIN,20));
		add(lblNewLabel);
		
		Iterator<StudentInfo> i=ServerCollection.students.iterator();
		while(i.hasNext())
		{
			student=i.next();
			model.addRow(new Object[]{student.ID,student.name,student.address,student.mob,student.dob,student.marks,null});
		}
	}
	public void refresh()
	{
		int rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   model.removeRow(i); 
		}
		Iterator<StudentInfo> i=ServerCollection.students.iterator();
		while(i.hasNext())
		{
			student=i.next();
			model.addRow(new Object[]{student.ID,student.name,student.address,student.mob,student.dob,student.marks,student.result});
		}
	}
}
