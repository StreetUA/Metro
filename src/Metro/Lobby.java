package Metro;

import java.util.ArrayList;
import java.util.List;

public class Lobby implements Runnable {
	private Station station; // Номер станции
	private List<Passenger> passlist; // Список пассажиров
	int out, in;

	public Lobby() {
		this.passlist = new ArrayList<Passenger>();
		out = 0;
		in = 0;
	}

	@Override
	public void run() {
		int i = getPasslist().size();
		do {
			try {
				// Появление пассажира в вестибюле
				Thread.sleep(100);
				i++;
				Passenger passenger = new Passenger();
				passenger.setId(getStation().getId() + i * 100);
				passenger.setInout(false);
				synchronized (getPasslist()) {
					// Выход из вестибюля
					if (!getPasslist().isEmpty()) {
						if (getPasslist().get(0).isInout()) {
							getPasslist().remove(0);
							out++;
						}
					}
					getPasslist().add(passenger);
					getPasslist().notifyAll();
					in++;
				}
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
