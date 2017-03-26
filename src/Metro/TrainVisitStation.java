package Metro;

import java.util.List;
import java.util.Random;

public class TrainVisitStation {
	private int id;
	private Station station;
	private TrainMovingLine tml;
	private List<Passenger> passin;
	private List<Passenger> passout;

	public TrainVisitStation(int id, Station station, TrainMovingLine tml) {

		int x = (new Random().nextInt(30) + 1);
		for (int i = 0; i < x; i++) {
			Passenger pass = new Passenger();
			pass.setId(i + 1);
			station.getPasslist().add(pass);
		
		}

		this.id = id;
		this.station = station;
		this.tml = tml;
	}

}
