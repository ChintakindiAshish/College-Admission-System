package CommonClasses;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

import AdminSide.SetCutoff;
import AdminSide.StudentInfo;

public class ServerCollection implements Serializable{

	private static final long serialVersionUID = 1L;
	public static HashMap<Choice,Integer> H5=new HashMap<Choice,Integer>();
	public static Vector<StudentInfo> students=new Vector<StudentInfo>();
	public static boolean flag=true;
	public static SetCutoff cut=null;
}
