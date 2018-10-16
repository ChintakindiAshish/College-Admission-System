package CommonClasses;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public class CollegeHashmap implements Serializable{

	private static final long serialVersionUID = -8941279430101132741L;
	public HashMap<Choice,Integer> H5;
	public HashMap<College,Vector<Branch>> H4;
	public CollegeHashmap(HashMap<Choice,Integer> h1,HashMap<College,Vector<Branch>> h2)
	{
		this.H5=h1;
		this.H4=h2;
	}
}
