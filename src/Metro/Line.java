package Metro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Line {
	private int id; // Номер линии
	private List<Station> stationlist; // Список станций
	private List<Train> trainlist; // Список поездов на линии

	public Line() {
		trainlist = new ArrayList<Train>();
		stationlist = new ArrayList<Station>();
	}

	// Создание станций
	public void stationCreate() {
		for (int i = 1; i < 11; i++) {
			Station station = new Station();
			station.setId(this.getId() * 100 + i);
			station.setLine(this);
			this.getStationlist().add(station);
			station.createElevator();
		}
	}

	public void lineRun() {
		Iterator<Station> stationiter = this.getStationlist().iterator();
		while (stationiter.hasNext()) {
			stationiter.next().stationRun();
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
