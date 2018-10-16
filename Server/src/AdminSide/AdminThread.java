package AdminSide;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.SocketException;
import java.util.HashMap;

import CommonClasses.*;

public class AdminThread implements Runnable,Serializable{

	private static final long serialVersionUID = 1L;
	public ObjectInputStream input;
	public ObjectOutputStream output;int n;
	public AllStudents students;
	public AdminThread(ObjectInputStream input,ObjectOutputStream output) throws Exception
	{
		this.input=input;
		this.output=output;
	}
	public void run()
	{
		try
		{
			HashMap<Choice,Integer> H5=new HashMap<Choice,Integer>();
			CollegeHashmap collection=new CollegeHashmap(H5,null);
			Jdbc.LoadState(collection);
			output.writeObject(collection);
			output.reset();
			while(true)
			{
				try 
				{
					Object obj=input.readObject();
					if(obj instanceof CollegeHashmap)
					{
						Jdbc.InsertChoice(((CollegeHashmap)obj).H5);
					}
					else if(obj instanceof CutoffHashmap)
					{
						Jdbc.InsertCutoff(((CutoffHashmap)obj).H5);
					}
					else if(obj instanceof String)
					{
						if(((String)obj).equals("StudentDetails"))
						{
							students=Jdbc.LoadAllStudents();
							output.writeObject(students);
							output.reset();
						}
						else if(((String)obj).equals("CheckFlag"))
						{
							output.writeObject(Timer.checktime());
							output.reset();
						}
						else if(((String)obj).equals("Result"))
						{
							AllStudents all=new AllStudents();
							students=Jdbc.LoadAllStudents();
							new PriorityStudents(all,students);
							output.writeObject(all);
							output.reset();
						}
					}
					else if(obj instanceof Integer)
					{
						Timer.StartTimer((Integer)obj);
					}
					else if(obj instanceof AllStudents)
					{
						Jdbc.UpdateAllStudents(((AllStudents)obj).info);
					}
				}
				catch(SocketException se)
				{
					break;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}