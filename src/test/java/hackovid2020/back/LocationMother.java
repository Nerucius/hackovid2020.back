package hackovid2020.back;

import java.util.Calendar;
import java.util.Random;

import hackovid2020.back.dao.Location;

public class LocationMother {
	
	private static final float latitude = new Random().nextFloat();
	private static final float longitude = new Random().nextFloat();
    private static final String streetName = "Suicidal Street 42069";
	
	public static Location createRandomLocation() {
		return Location.createLocation(latitude, longitude, streetName,
				Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
	}

}
