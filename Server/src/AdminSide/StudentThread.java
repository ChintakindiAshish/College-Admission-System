package AdminSide;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Vector;

import CommonClasses.Branch;
import CommonClasses.Choice;
import CommonClasses.College;
import CommonClasses.CollegeHashmap;

public class StudentThread implements Runnable,Serializable{

	private static final long serialVersionUID = 1L;
	public ObjectInputStream input;
	public ObjectOutputStream output;int n;
	public String name,password,store;
	public ResultData resultdata;
	public StudentThread(ObjectInputStream input,ObjectOutputStream output,String name,String password) throws Exception
	{
		this.input=input;
		this.output=output;
		this.name=name;
		this.password=password;
	}
	public void run()
	{
		try
		{
			StudentInfo student=Jdbc.LoadStudentState(name,password);
			output.writeObject(student);
			output.reset();
			HashMap<Choice,Integer> H5=new HashMap<Choice,Integer>();
			HashMap<College,Vector<Branch>> H4=new HashMap<College,Vector<Branch>>();
			CollegeHashmap college=new CollegeHashmap(H5,H4);
			Jdbc.LoadCollegeState(college);
			output.writeObject(college);
			output.reset();
			while(true)
			{
				try
				{
					Object obj=input.readObject();
					if(obj instanceof StudentInfo)
					{
						if(((StudentInfo)obj).choice==null)
							name=Jdbc.UpdateStudentInfo(((StudentInfo)obj),name,password);
						else
							Jdbc.InsertStudentChoice(((StudentInfo)obj).choice,name,password);
					}
					else if(obj instanceof String)
					{
						store=String.valueOf(obj);
						if(store.equals("CutoffList"))
						{
							college.H5.clear();
							college.H4.clear();
							Jdbc.LoadCollegeState(college);
							output.writeObject(college);
							output.reset();
						}
						else if(store.equals("Student") || store.equals("Choice"))
						{
							college.H5.clear();
							college.H4.clear();
							resultdata=Jdbc.LoadResult(name,password,college);
							output.writeObject(resultdata);
							output.reset();
						}
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