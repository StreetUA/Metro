package Metro;

import java.util.ArrayList;
import java.util.List;

public class Station {
	private int id; // Номер санции
	private Line line; // Номер линии
	private Lobby lobby; // номер лобби
	private Elevator[] elevator = new Elevator[3]; // 3 эскалатора
	private List<Passenger> passlist; // Список пассажиров

	public Station() {
		passlist = new ArrayList<Passenger>();
		lobby = new Lobby();
	}

	public void createElevator() {
		lobby.setStation(this);

		for (int i = 0; i < 3; i++) {
			elevator[i] = new Elevator();
			elevator[i].setStation(this);
			elevator[i].setId(this.getId() * 100 + i);
		}
	}

	public void stationRun() {
		Thread lobbyThread = new Thread(lobby);
		lobbyThread.start();

		Thread elevatorThread0 = new Thread(elevator[0]);
		elevatorThread0.start();

		Thread elevatorThread1 = new Thread(elevator[1]);
		elevatorThread1.start();

		Thread elevatorThread2 = new Thread(elevator[2]);
		elevatorThread2.start();
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

	public Lobby getLobby() {
		return lobby;
	}

	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
	}

	public Elevator[] getElevator() {
		return elevator;
	}

	public void setElevator(Elevator[] elevator) {
		this.elevator = elevator;
	}

	public List<Passenger> getPasslist() {
		return passlist;
	}

	public void setPasslist(List<Passenger> passlist) {
		this.passlist = passlist;
	}

}
