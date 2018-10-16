package AdminSide;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

import CommonClasses.Branch;
import CommonClasses.Choice;
import CommonClasses.College;

public class ResultData implements Serializable{

	private static final long serialVersionUID = 1L;
	public HashMap<Choice,Integer> H5;
	public HashMap<College,Vector<Branch>> H4;
	public StudentInfo student;
	public boolean flag,flag2=false;
}
