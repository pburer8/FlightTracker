
import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class Main
{
	static float maxLat = Float.MIN_VALUE;
	static float minLat = Float.MAX_VALUE;
	static float maxLong = Float.MIN_VALUE;
	static float minLong = Float.MAX_VALUE;
	
	
	public static void main(String[] a) throws IOException
	{
		
		Set<String> airportset = new HashSet<>();
		
		Iterable<FlightRecord> input = DataImporter.getData("flights2020.csv");
		
		for (FlightRecord r : input)
		{
			airportset.add(r.ORIGIN);
			airportset.add(r.DEST);
		}
		
		Map<String, Pair> airportcoords = new HashMap<>();
		
		Iterable<AirportRecord> airports = AirportImporter.getData("us-airports.csv");
		
		for (AirportRecord r : airports)
		{
			if (airportset.contains(r.IDENT))
			{
				if (r.REGION.equals("AK") || r.REGION.equals("HI"))
				{
					continue;
				}
				
				if (r.LATITUDE_DEG > maxLat)
				{
					maxLat = r.LATITUDE_DEG;
				}
				
				if (r.LATITUDE_DEG < minLat)
				{
					minLat = r.LATITUDE_DEG;
				}
				
				if (-1*r.LONGITUTE_DEG > maxLong)
				{
					maxLong = -1*r.LONGITUTE_DEG;
				}
				
				if (-1*r.LONGITUTE_DEG < minLong)
				{
					minLong = -1 * r.LONGITUTE_DEG;
				}
			}
		}
		
		for (AirportRecord r : airports)
		{
			if (airportset.contains(r.IDENT))
			{
				if (r.REGION.equals("AK") || r.REGION.equals("HI"))
				{
					continue;
				}
				
				airportcoords.put(r.IDENT, coordsToPixel(new Pair(r.LATITUDE_DEG, -1*r.LONGITUTE_DEG)));
			}
		}
		
		
		ArrayList<MyAirport> airportsList = new ArrayList<>();
		
		
		for (Map.Entry<String, Pair> e : airportcoords.entrySet())
		{
			airportsList.add(new MyAirport(e.getValue()));
			
		}
		
		Set<MyFlight> flightList = new HashSet<>();
		
		
		for (FlightRecord r : input)
		{
			Pair start = airportcoords.get(r.ORIGIN);
			
			Pair dest = airportcoords.get(r.DEST);
			
			if (start == null || dest == null)
			{
				continue;
			}
			
			MyFlight f = new MyFlight(start, dest, r.ORIGIN, r.DEST);
			
			flightList.add(f);
		}
		
		JFrame window = new JFrame();
		JPanel panel = new DrawMap(airportsList, flightList);
		window.setSize(1024, 667);
		window.getContentPane().add(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.setVisible(true);
	}
	
	public static Pair coordsToPixel(Pair p)
	{
		Pair pixels;
		
		float lat = -1*(p.a - maxLat)*(500f/(maxLat - minLat)) + 80f;
		
		float lon = -1*(p.b - maxLong)*(940f/(maxLong - minLong)) + 30f;
		
		pixels = new Pair(lon, lat);
		
		return pixels;
	}
}
