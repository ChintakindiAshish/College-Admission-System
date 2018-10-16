package CommonClasses;

import java.io.Serializable;

public class Branch implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public Branch(String name)
	{
		this.name=name;
	}
}