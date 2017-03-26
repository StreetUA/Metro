package Metro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Station {
	private int id; // Номер санции
	private Line line; // Номер линии
	private List<Passenger> passlist;

	public Station() {
		passlist = new ArrayList<Passenger>();
		int tmp = new Random().nextInt(20) + 1;
		for (int i = 1; i < tmp + 1; i++) {
			Passenger passenger = new Passenger();
			passenger.setId(i);
			passenger.setStation(this);
			this.passlist.add(passenger);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public List<Passenger> getPasslist() {
		return passlist;
	}

	public void setPasslist(List<Passenger> passlist) {
		this.passlist = passlist;
	}

}
