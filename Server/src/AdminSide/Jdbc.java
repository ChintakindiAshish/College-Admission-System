package AdminSide;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import CommonClasses.*;
public class Jdbc {

	public static Connection conn;
	public static Statement st1;
	public static PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6,ps7,ps8,ps9,ps10,ps11,ps12,ps13,ps14,ps15,ps16;
	public static ResultSet rset1,rset2,rset3;
	public static int n;
	public static Vector<String> College_list=new Vector<String>(15);
	public static Iterator<String> I1;
	public static void createConnection() throws Exception
	{
		String url = "jdbc:mysql://localhost:3306/CodeSkate002";
		String userName = "root";
		String password = "Mayuresh@123";
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url, userName, password);
		st1=conn.createStatement();
		ps1=conn.prepareStatement("Insert into Choice(College_name,Branch_name,Intake) values(?,?,?);");
		ps2=conn.prepareStatement("update Admin set password=? where name=?;");
		ps3=conn.prepareStatement("update Choice set Cutoff=? where College_name=? and Branch_name=?;");
		ps4=conn.prepareStatement("select Branch_name,Intake,Cutoff from Choice where College_name=?;");
		ps5=conn.prepareStatement("Insert into Student(Name,Address,Mob,DOB,Marks,Password,Nickname) values(?,?,?,?,?,?,?);");
		ps6=conn.prepareStatement("select Name,Mob,Password from Student;");
		ps7=conn.prepareStatement("update Student set Password=? where Name=?;");
		ps8=conn.prepareStatement("select * from Student where Name=? and Password=?;");
		ps9=conn.prepareStatement("update Student set Name=?,Nickname=?,Address=?,Mob=?,DOB=?,Marks=? where Name=? and Password=?;");
		ps10=conn.prepareStatement("insert into Student_Choice values(?,?,?);");
		ps11=conn.prepareStatement("select * from Choice where College_name=? and Branch_name=?;");
		ps12=conn.prepareStatement("select ID from Student where Name=? and Password=?;");
		ps13=conn.prepareStatement("select No from Student_Choice where ID=? order by Priority;");
		ps14=conn.prepareStatement("select College_name,Branch_name from Choice where No=?;");
		ps15=conn.prepareStatement("select Name,Password from Student;");
		ps16=conn.prepareStatement("update Student set Result=? where name=? and password=?;");
	}
	public static boolean CheckAdmin(String s1,String s2) throws Exception
	{
		rset1 = st1.executeQuery("select * from Admin;");
		if(rset1.next())
		{
			if(rset1.getString("name").equals(s1) && rset1.getString("password").equals(s2))
				return true;
			else
				return false;
		}
		return false;
	}
	public static boolean CheckStudent(String s1,String s2) throws Exception
	{
		rset3 = st1.executeQuery("select * from Student;");
		boolean flag=false;
		while(rset3.next())
		{
			if(rset3.getString("Name").equals(s1) && rset3.getString("Password").equals(s2))
				flag=true;
		}
		return flag;
	}
	public static boolean ChangePassword(String s1,String s2,String s3) throws Exception
	{
		rset1 = st1.executeQuery("select * from Admin;");
		if(rset1.next())
		{
			if(rset1.getString("name").equals(s1) && rset1.getString("nickname").equals(s2))
			{
				ps2.setString(1,s3);
				ps2.setString(2,s1);
				ps2.executeUpdate();
				return true;
			}
			else
				return false;
		}
		return false;
	}
	public static boolean ChangeStudentPassword(String s1,String s2,String s3) throws Exception
	{
		rset1 = st1.executeQuery("select * from Student;");
		if(rset1.next())
		{
			if(rset1.getString("Name").equals(s1) && rset1.getString("Nickname").equals(s2))
			{
				ps7.setString(1,s3);
				ps7.setString(2,s1);
				ps7.executeUpdate();
				return true;
			}
			else
				return false;
		}
		return false;
	}
	public static boolean InsertStudent(StudentInfo s) throws Exception
	{
		rset1=ps6.executeQuery();
		while(rset1.next())
		{
			if(rset1.getLong("Mob")==s.mob && rset1.getString("Name").equals(s.name) && rset1.getString("Password").equals(s.password))
				return false;
		}
			ps5.setString(1,s.name);
			ps5.setString(2,s.address);
			ps5.setLong(3,s.mob);
			ps5.setString(4,s.dob);
			ps5.setInt(5,s.marks);
			ps5.setString(6,s.password);
			ps5.setString(7,s.nickname);
			ps5.executeUpdate();
			return true;
	}
	public static void InsertChoice(HashMap<Choice,Integer> h) throws Exception
	{
		for(Choice n:h.keySet())
		{
			ps1.setInt(3,n.intake);
			ps1.setString(1,n.Cc.name);
			ps1.setString(2,n.Bb.name);
			ps1.executeUpdate();
		}
	}
	public static void InsertCutoff(HashMap<Choice,Integer> h) throws Exception
	{
		for(Choice n:h.keySet())
		{
			ps3.setInt(1,h.get(n));
			ps3.setString(2,n.Cc.name);
			ps3.setString(3,n.Bb.name);
			ps3.executeUpdate();
		}
	}
	public static void InsertStudentChoice(Vector<Choice> h,String s1,String s2) throws Exception
	{
		Iterator<Choice> i=h.iterator();
		int cnt=1,ID;
		Choice ch;
		ps12.setString(1,s1);
		ps12.setString(2,s2);
		rset1=ps12.executeQuery();
		rset1.next();
		ID=rset1.getInt("ID");
		while(i.hasNext())
		{
			ch=i.next();
			ps11.setString(1,ch.Cc.name);
			ps11.setString(2,ch.Bb.name);
			rset3=ps11.executeQuery();
		    rset3.next();
			ps10.setInt(3,rset3.getInt(1));
			ps10.setInt(1,cnt);
			ps10.setInt(2,ID);
			ps10.executeUpdate();
			cnt++;
		}
	}
	public static String UpdateStudentInfo(StudentInfo student,String s1,String s2) throws Exception
	{
		ps9.setString(1,student.name);
		ps9.setString(2,student.nickname);
		ps9.setString(3,student.address);
		ps9.setLong(4,student.mob);
		ps9.setString(5,student.dob);
		ps9.setInt(6,student.marks);
		ps9.setString(7,s1);
		ps9.setString(8,s2);
		ps9.executeUpdate();
		return student.name;
	}
	public static void LoadState(CollegeHashmap h) throws Exception
	{
		rset1 = st1.executeQuery("select * from Choice;");
		while(rset1.next())
		{
			College C=new College(rset1.getString("College_name"));
			Branch B=new Branch(rset1.getString("Branch_name"));
			Choice Ch=new Choice(C,B,rset1.getInt("Intake"));
			if(rset1.getString("Cutoff")==null)
				h.H5.put(Ch,null);
			else
				h.H5.put(Ch,rset1.getInt("Cutoff"));
		}
	}
	public static StudentInfo LoadStudentState(String name,String password) throws Exception
	{
		int ID;
		ps8.setString(1,name);
		ps8.setString(2,password);
		rset1=ps8.executeQuery();
		if(rset1.next())
		{
			Vector<Choice> choice=new Vector<Choice>();
			StudentInfo student=new StudentInfo();
			student.choice=choice;
			student.ID=rset1.getInt("ID");
			student.name=rset1.getString("Name");
			student.address=rset1.getString("Address");
			student.dob=rset1.getString("DOB");
			student.marks=rset1.getInt("Marks");
			student.nickname=rset1.getString("Nickname");
			student.password=rset1.getString("Password");
			student.mob=rset1.getLong("Mob");
			student.result=rset1.getString("Result");
			ps12.setString(1,name);
			ps12.setString(2,password);
			rset1=ps12.executeQuery();
		    rset1.next();
		    ID=rset1.getInt("ID");
			ps13.setInt(1,ID);
			rset1=ps13.executeQuery();
			while(rset1.next())
			{
				ps14.setInt(1,rset1.getInt("No"));
				rset2=ps14.executeQuery();
				rset2.next();
				College C=new College(rset2.getString("College_name"));
				Branch B=new Branch(rset2.getString("Branch_name"));
				Choice ch=new Choice(C,B,0);
				student.choice.add(ch);
			}
			return student;
		}
		return null;
	}
	public static void LoadCollegeState(CollegeHashmap h) throws Exception
	{
		rset1 = st1.executeQuery("select distinct(College_name) from Choice;");
		while(rset1.next())
		{
			College C=new College(rset1.getString("College_name"));
			ps4.setString(1,rset1.getString("College_name"));
			rset2 = ps4.executeQuery();
			Vector<Branch> v1=new Vector<Branch>();
			while(rset2.next())
			{
				Branch B=new Branch(rset2.getString("Branch_name"));
				Choice Ch=new Choice(C,B,rset2.getInt("Intake"));
				if(rset2.getString("Cutoff")==null)
					h.H5.put(Ch,null);
				else
					h.H5.put(Ch,rset2.getInt("Cutoff"));
				v1.add(B);
			}
			h.H4.put(C,v1);
		}
	}
	public static AllStudents LoadAllStudents() throws Exception
	{
		Vector<StudentInfo> students=new Vector<StudentInfo>();
		AllStudents h=new AllStudents();
		h.info=students;
		StudentInfo st;
		rset3=ps15.executeQuery();
		while(rset3.next())
		{
			st=LoadStudentState(rset3.getString("Name"),rset3.getString("Password"));
			h.info.add(st);
		}
		return h;
	}
	public static ResultData LoadResult(String name,String password,CollegeHashmap h) throws Exception
	{
		ResultData result=new ResultData();
		result.student=LoadStudentState(name,password);
		LoadCollegeState(h);
		result.H4=h.H4;
		result.H5=h.H5;
		{
			result.flag2=Timer.flag2;
			result.flag=Timer.checktime();
		}
		return result;
	}
	public static void UpdateAllStudents(Vector<StudentInfo> h) throws Exception
	{
		Iterator<StudentInfo> i=h.iterator();
		StudentInfo s;
		while(i.hasNext())
		{
			s=i.next();
			ps16.setString(1,s.result);
			ps16.setString(2,s.name);
			ps16.setString(3,s.password);
			ps16.executeUpdate();
		}
	}
}
