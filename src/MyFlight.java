import java.awt.*;
import java.awt.geom.Line2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
public class MyFlight extends JComponent
{
	Pair start;
	Pair dest;
	String ORIGIN;
	String DEST;
	
	
	public MyFlight(Pair s, Pair d, String o, String D)
	{
		start = s;
		dest = d;
		ORIGIN = o;
		DEST = D;
	}
	
	public String toString()
	{
		return "Start: " + start.toString() + "\nDest: " + dest.toString() + "\n";
	}
	
	@Override
	public int hashCode()
	{
		return 31 * (ORIGIN.hashCode() + 17 * DEST.hashCode());
	}
	
	public boolean equals(Object obj)
	{
		if (obj instanceof MyFlight f)
		{
			return f.hashCode() == hashCode();
		}
		return false;
	}
	
	public Shape toShape()
	{
		return new Line2D.Float(start.a, start.b, dest.a, dest.b);
	}
}
