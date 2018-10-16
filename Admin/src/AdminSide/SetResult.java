package AdminSide;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Vector;

import CommonClasses.Choice;
import CommonClasses.ServerCollection;

public class SetResult {

	int cutoff,cnt=0;
	String result;
	public SetResult(ObjectInputStream input,ObjectOutputStream output) throws Exception {
		
		output.writeObject("Result");
		output.reset();
		AllStudents students=(AllStudents) input.readObject();
		
		Vector<Choice> ch=new Vector<Choice>();
		for(Choice n:ServerCollection.H5.keySet())
		{
			ch.add(n);
		}
		
		Iterator<StudentInfo> i=students.info.iterator();
		StudentInfo s;
		Iterator<Choice> i2;
		Choice c,c2;
		
		while(i.hasNext())
		{
			s=i.next();
			i2=s.choice.iterator();
			cnt=0;
			while(i2.hasNext())
			{
				c=i2.next();
				Iterator<Choice> i3=ch.iterator();
				while(i3.hasNext())
				{
					c2=i3.next();
					if(c2.Cc.name.equals(c.Cc.name) && c2.Bb.name.equals(c.Bb.name))
					{
						if(c2.intake==0)
							break;
						cutoff=ServerCollection.H5.get(c2);
						if(s.marks>=cutoff)
						{
							result=c2.Cc.name+"--->"+c2.Bb.name;
							cnt=1;
							c2.intake--;
							break;
						}
					}
				}
				if(cnt==1)
					break;
			}
			if(cnt==1)
				s.result=result;
			else
				s.result="College Not Alloted";
		}
		output.writeObject(students);
		output.reset();
	}
}
