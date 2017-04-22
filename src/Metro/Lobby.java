package Metro;

import java.util.ArrayList;
import java.util.List;

public class Lobby implements Runnable {
	private Station station; // Номер станции
	private List<Passenger> passlist; // Список пассажиров

	public Lobby() {
		this.passlist = new ArrayList<Passenger>();
		// this.randomPassOnLobby();
	}

	@Override
	public void run() {
		int i = getPasslist().size();
		do {
			try {
				// Появление пассажира в вестибюле
				Thread.sleep(25);
				i++;
				Passenger passenger = new Passenger();
				passenger.setId(getStation().getId() + i * 100);
				synchronized (getPasslist()) {
					getPasslist().add(passenger);
					getPasslist().notifyAll();
				}
//				System.out
//						.println("New passanger " + passenger.getId() + " on the lobby of station " + getStation().getId());
			} catch (InterruptedException e) {
			}
		} while (true);
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
