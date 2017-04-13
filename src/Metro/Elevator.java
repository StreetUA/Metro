package Metro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Elevator implements Runnable {
	private int id; // Номер эскалатора
	private Station station; // Номер станции
	private List<Passenger> passlist; // Список пассажиров

	public Elevator() {
		passlist = new ArrayList<Passenger>();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
			do {
				// Переход пассажира из вестибюля на эскалатор
				Thread.sleep(1000);
				Iterator<Passenger> passlobbyiter = station.getLobby().getPasslist().iterator();
				Passenger passenger = passlobbyiter.next();
				passlist.add(passenger);
				station.getLobby().getPasslist().remove(passenger);
				System.out.println("New passanger " + passenger.getId() + " on the elevator " + this.getId());

				// Переход пассажира с эскалатора на станцию
				Thread.sleep(1000);
				Iterator<Passenger> passeleviter = station.getLobby().getPasslist().iterator();
				passenger = passeleviter.next();
				station.getPasslist().add(passenger);
				passlist.remove(passenger);
				System.out.println("New passanger " + passenger.getId() + " on the station " + station.getId());

			} while (true);
		} catch (InterruptedException e) {
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public List<Passenger> getPasslist() {
		return passlist;
	}

	public void setPasslist(List<Passenger> passlist) {
		this.passlist = passlist;
	}
}
