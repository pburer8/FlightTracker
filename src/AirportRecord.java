import org.apache.commons.csv.CSVRecord;

public class AirportRecord
{
	public final String IDENT;
	public final float LATITUDE_DEG;
	public final float LONGITUTE_DEG;
	
	public final String REGION;
	
	public AirportRecord(CSVRecord from)
	{
		IDENT = from.get("ident").substring(1);
		LATITUDE_DEG = Float.parseFloat(from.get("latitude_deg"));
		LONGITUTE_DEG = Float.parseFloat(from.get("longitude_deg"));
		REGION = from.get("local_region");
	}
}
