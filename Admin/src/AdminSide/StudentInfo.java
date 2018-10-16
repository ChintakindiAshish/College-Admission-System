package AdminSide;

import java.io.Serializable;
import java.util.Vector;

import CommonClasses.Choice;

public class StudentInfo implements Serializable{

	private static final long serialVersionUID = -2880869040951508387L;
	public String name,address,dob,nickname,password,result;
	public long mob;
	public int marks,ID;
	public Vector<Choice> choice;
}
