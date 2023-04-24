import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
public class MyAirport extends JComponent
{
	Pair coords;
	public MyAirport(Pair p)
	{
		coords = p;
	}
	public Shape toShape()
	{
		return new Ellipse2D.Float(coords.a, coords.b, 1, 1);
	}
	
	public String toString()
	{
		return coords.toString();
	}
}
