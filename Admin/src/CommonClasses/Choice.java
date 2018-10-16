package CommonClasses;

import java.io.Serializable;

public class Choice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public College Cc;
	public Branch Bb;
	public int intake;
	public Choice(College C,Branch B,int I)
	{
		Cc=C;
		Bb=B;
		intake=I;
	}
}