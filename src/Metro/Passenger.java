package Metro;

public class Passenger {
	private int id;			// Номер пассажира
	private Wagon wagon;	// Номер вагона
	private Station station;	// Номер станции
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Wagon getWagon() {
		return wagon;
	}

	public void setWagon(Wagon wagon) {
		this.wagon = wagon;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
}
