import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DrawMap extends JPanel
{
	BufferedImage bImg;
	Iterable<MyAirport> airports;
	Iterable<MyFlight> flights;
	
	static Map<Color, Color> colorGradient;
	
	public DrawMap(Iterable<MyAirport> a, Iterable<MyFlight> f) throws IOException
	{
		bImg = ImageIO.read(new File("blank-map-of-us.jpg"));
		airports = a;
		flights = f;
		
		colorGradient = new HashMap<>();
		colorGradient.put(Color.red, Color.orange);
		colorGradient.put(Color.orange, Color.yellow);
		colorGradient.put(Color.yellow, Color.green);
		colorGradient.put(Color.green, Color.cyan);
		colorGradient.put(Color.cyan, Color.blue);
		colorGradient.put(Color.blue, Color.pink);
		colorGradient.put(Color.pink, Color.red);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		
		
		g2.drawImage(bImg, 0, 0, this);
		
		for (MyAirport a : airports)
		{
			g2.draw(a.toShape());
		}
		
		g2.setColor(Color.red);
		for (MyFlight f : flights)
		{
			g2.draw(f.toShape());
			g2.setColor(colorGradient.get(g2.getColor()));
		}
		
		g2.dispose();
	}
}
