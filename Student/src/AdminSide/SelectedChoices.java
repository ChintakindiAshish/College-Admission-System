package AdminSide;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import CommonClasses.Choice;
import CommonClasses.PriorityChoices;
import CommonClasses.ServerCollection;

public class SelectedChoices extends JPanel implements Comparator<PriorityChoices>{

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public int cnt=1;
	public Choice n;
	public SelectedChoices thisobject;
	public JTable table;
	public JLabel lblNewLabel_1;
	public JButton btnSubmit;
	public PriorityQueue<PriorityChoices> pq;
	public Vector<Integer> v1=new Vector<Integer>();
	public DefaultTableModel model;
	public JScrollPane j1;
	public JLabel lblNewLabel_2;
	public SelectedChoices(Choices parent,ObjectInputStream input,ObjectOutputStream output,StudentInfo student) throws Exception{

		pq=new PriorityQueue<PriorityChoices>(100,this);  
		
		parent.lblFillTheFollowing.setVisible(false);
		parent.lblCollegeName.setVisible(false);
		parent.lblBranchName.setVisible(false);
		parent.comboBox.setVisible(false);
		parent.comboBox_1.setVisible(false);
		parent.btnAdd.setVisible(false);
		parent.btnSubmit.setVisible(false);
		
		setLayout(null);
		setBounds(0,0,980,564);
		
		model=new DefaultTableModel(); 
		table = new JTable(model){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {
	        	
	        	if(column==3)
	        		return true;
	            return false;               
	        };
	    };
		model.addColumn("Choice No");
        model.addColumn("College Name");
        model.addColumn("Branch Name");
        model.addColumn("Priority");
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        table.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        table.setRowHeight(20);
        
		
        /*table.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
        {
			public void valueChanged(ListSelectionEvent e) {
			    
				System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
			}
        	
        });*/
        j1=new JScrollPane(table);
        j1.setBounds(70,71,830,376);
		add(j1);
		
		lblNewLabel_1 = new JLabel("Double click on the priority column to enter priority");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(70, 498, 440, 20);
		lblNewLabel_1.setFont(new Font("Arial",Font.PLAIN,17));
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Please enter the valid Priorities");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(370, 459, 231, 15);
		lblNewLabel_2.setVisible(false);
		add(lblNewLabel_2);
		
		thisobject=this;
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					output.reset();
				} catch (SocketException e1) 
				{	
					JOptionPane.showMessageDialog(null,"Server Down");
					return;
				}
				catch (IOException e) {
					
					e.printStackTrace();
				}
				System.out.println("Here");
					    TableModel model = table.getModel();
					    int rows = model.getRowCount();
					    Iterator<Choice> i=parent.choicelist.iterator();
					    try {
					    for ( int row = 0; row < rows; row++ ) {
					        PriorityChoices pc=new PriorityChoices(i.next(),Integer.parseInt((String)model.getValueAt( row, 3 )));
					        if(v1.contains(Integer.parseInt((String)model.getValueAt( row, 3 ))))
					        {
					        	lblNewLabel_2.setVisible(true);
					    		pq.clear();
					    		v1.clear();
					        	Timer t=new Timer(2000,new ActionListener() 
								{
									public void actionPerformed(ActionEvent e)
									{
										lblNewLabel_2.setVisible(false);
									}
								});
								t.start();
								t.setRepeats(false);
								return;	
					        }
					        v1.add(Integer.parseInt((String)model.getValueAt( row, 3 )));
					        pq.add(pc);
					    }
					    for ( int row = 1; row <= rows; row++ )
					    {
					    	if(!v1.contains(row))
					    	{
					    		lblNewLabel_2.setVisible(true);
					    		pq.clear();
					    		v1.clear();
					        	Timer t=new Timer(2000,new ActionListener() 
								{
									public void actionPerformed(ActionEvent e)
									{
										lblNewLabel_2.setVisible(false);
									}
								});
								t.start();
								t.setRepeats(false);
								return;	
					    	}
					    }
					    }
					    catch(NumberFormatException nfe)
					    {
					    	lblNewLabel_2.setVisible(true);
				    		pq.clear();
				    		Timer t=new Timer(2000,new ActionListener() 
							{
								public void actionPerformed(ActionEvent e)
								{
									lblNewLabel_2.setVisible(false);
								}
							});
							t.start();
							t.setRepeats(false);
							return;	
					    }
					    PriorityTable pt;
					    PriorityChoices pc;
					    Vector<Choice> choice=new Vector<Choice>();
					    ServerCollection.choice=choice;
					    while(!pq.isEmpty())
						{
					    	pc=pq.poll();
							ServerCollection.choice.add(pc.Ch);
						}
						try 
						{
							pt = new PriorityTable(input,output,student,true);
							table.setVisible(false);
						    btnSubmit.setVisible(false);
							lblNewLabel_1.setVisible(false);
							j1.setVisible(false);
							add(pt);
						} catch (Exception e) 
						{
							e.printStackTrace();
						}
			}
		});
		btnSubmit.setBounds(779, 487, 117, 35);
		add(btnSubmit);
		
		Iterator<Choice> i=parent.choicelist.iterator();
		while(i.hasNext())
		{
			n=i.next();
			model.addRow(new Object[]{cnt,n.Cc.name,n.Bb.name,""});
			cnt++;
		}
	}
	public int compare(PriorityChoices p1, PriorityChoices p2) {
		if(p1.priority>p2.priority)
			return 1;
		else if(p1.priority<p2.priority)
			return -1;
		return 1;
	}
}
