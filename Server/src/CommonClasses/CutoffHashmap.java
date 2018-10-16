package CommonClasses;

import java.io.Serializable;
import java.util.HashMap;

public class CutoffHashmap implements Serializable{

	private static final long serialVersionUID = -9011316046251658326L;
	public HashMap<Choice,Integer> H5;
	public CutoffHashmap(HashMap<Choice,Integer> h)
	{
		this.H5=h;
	}
}
