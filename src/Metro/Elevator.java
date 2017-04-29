package Metro;

import java.util.ArrayList;
import java.util.List;

public class Elevator implements Runnable {
	private int id; // Номер эскалатора
	private boolean updown; // Движение ввер и вниз
	private Station station; // Номер станции
	private List<Passenger> passlist; // Список пассажиров

	public Elevator() {
		this.passlist = new ArrayList<Passenger>();
	}

	@Override
	public void run() {
		do {
			try {
				Thread.sleep(100);
				// Переход пассажира из вестибюля на эскалатор
				synchronized (getStation().getLobby().getPasslist()) {
					// из вестибюля на эскалатор
					if (!this.isUpdown()) {
						if (getStation().getLobby().getPasslist().isEmpty()) {
							getStation().getLobby().getPasslist().wait();
						} else {
							synchronized (getPasslist()) {
								getPasslist().add(getStation().getLobby().getPasslist().get(0));
								getStation().getLobby().getPasslist().remove(0);
							}
						}
					} else {
						// с эскалатор в вестибюль
						synchronized (getPasslist()) {
							if (!getPasslist().isEmpty()) {
								getStation().getLobby().getPasslist().add(getPasslist().get(0));
								getPasslist().remove(0);
							}
						}
					}
				}
			} catch (InterruptedException e) {
			}
		} while (true);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isUpdown() {
		return updown;
	}

	public void setUpdown(boolean updown) {
		this.updown = updown;
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
