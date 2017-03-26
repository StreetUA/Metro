package Metro;

public class Passenger {
	private int id;			// Номер пассажира
	private RailwayCarriage wagon;	// Номер вагона
	private Station station;	// Номер станции
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RailwayCarriage getWagon() {
		return wagon;
	}

	public void setWagon(RailwayCarriage wagon) {
		this.wagon = wagon;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
}
