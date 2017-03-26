package Metro;

import java.util.ArrayList;
import java.util.List;

public class Line {
	private int id; // Номер линии
	private List<Station> stationlist;
	private List<Train> trainlist;

	public Line() {
		this.trainlist = new ArrayList<Train>();
		this.stationlist = new ArrayList<Station>();
		for (int i = 1; i < 11; i++) {
			Station station = new Station();
			station.setId(i);
			station.setLine(this);
			this.stationlist.add(station);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Station> getStationlist() {
		return stationlist;
	}

	public void setStationlist(List<Station> stationlist) {
		this.stationlist = stationlist;
	}

	public List<Train> getTrainlist() {
		return trainlist;
	}

	public void setTrainlist(List<Train> trainlist) {
		this.trainlist = trainlist;
	}

}
