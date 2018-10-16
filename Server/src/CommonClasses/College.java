package CommonClasses;

import java.io.Serializable;

public class College implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public College(String name)
	{
		this.name=name;
	}
}