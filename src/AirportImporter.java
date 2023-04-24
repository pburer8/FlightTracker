import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
public class AirportImporter
{
	public static Iterable<AirportRecord> getData(String filename) throws IOException
	{
		CSVParser parser;
		parser = CSVFormat.EXCEL.withHeader().parse(new FileReader(filename));
		
		List<AirportRecord> input = new LinkedList<>();
		
		for (CSVRecord r : parser.getRecords())
		{
			input.add(new AirportRecord(r));
		}
		
		return input;
	}
}
