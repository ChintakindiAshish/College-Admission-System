package AdminSide;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Vector;

public class PriorityStudents implements Comparator<StudentInfo>{

	
	public PriorityQueue<StudentInfo> pq;
	public PriorityStudents(AllStudents all,AllStudents old)
	{
		pq=new PriorityQueue<StudentInfo>(100,this);
		
		Iterator<StudentInfo> i=old.info.iterator();
		while(i.hasNext())
		{
			pq.add(i.next());
		}
		Vector<StudentInfo> students=new Vector<StudentInfo>();
		all.info=students;
		while(!pq.isEmpty())
		{
	    	all.info.add(pq.poll());
		}
	}
	
	public int compare(StudentInfo p1, StudentInfo p2) 
	{
		if(p1.marks>p2.marks)
			return 1;
		else if(p1.marks<p2.marks)
			return -1;
		return 1;
	}
}
