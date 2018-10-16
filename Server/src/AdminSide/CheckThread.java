package AdminSide;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class CheckThread implements Runnable,Serializable{
	private static final long serialVersionUID = 1L;
	
	public Socket s;
	public ObjectInputStream input;
	public ObjectOutputStream output;
	public Object obj;
	public CheckThread(Socket s1)
	{
		this.s=s1;
	}

	public void run() 
	{
		
		boolean n;
		try
		{
			input = new ObjectInputStream(s.getInputStream());
			output=new ObjectOutputStream(s.getOutputStream());
			while(true) {
			obj = input.readObject();
			if(obj instanceof AdminInfo)
			{
				if(((AdminInfo) obj).nickname!=null)
				{
					n=Jdbc.ChangePassword(((AdminInfo) obj).name,((AdminInfo) obj).nickname,((AdminInfo) obj).password);
					output.writeObject(n);
					output.reset();
				}
				else
				{
					n=Jdbc.CheckAdmin(((AdminInfo) obj).name,((AdminInfo) obj).password);
					output.writeObject(n);
					output.reset();
					if(n)
					{
						AdminThread adminthread=new AdminThread(input,output);
						Thread t=new Thread(adminthread);
						t.start();
						return;
					}
				}
			}
			else if(obj instanceof StudentInfo)
			{
				if(((StudentInfo) obj).address!=null)
				{
						n=Jdbc.InsertStudent(((StudentInfo) obj));
						output.writeObject(n);
						output.reset();
				}
				else if(((StudentInfo) obj).nickname!=null)
				{
					n=Jdbc.ChangeStudentPassword(((StudentInfo) obj).name,((StudentInfo) obj).nickname,((StudentInfo) obj).password);
					output.writeObject(n);
					output.reset();
				}
				else
				{
					n=Jdbc.CheckStudent(((StudentInfo) obj).name,((StudentInfo) obj).password);
					output.writeObject(n);
					output.reset();
					if(n)
					{
						StudentThread studentthread=new StudentThread(input,output,((StudentInfo) obj).name,((StudentInfo) obj).password);
						Thread t=new Thread(studentthread);
						t.start();
						return;
					}
				}
			}
			else
			{
				if(Timer.Total_Time==-1)
				{
					output.writeObject("ServerActive");
				}
				else
					output.writeObject(Timer.checktime());
				output.reset();
			}
		  }
		} catch (Exception e) 
		{
			return;
		}
  }
}
