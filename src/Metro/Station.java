package Metro;

import java.util.ArrayList;
import java.util.List;

public class Station implements Runnable {
	private int id; // Номер санции
	private Line line; // Номер линии
	private Lobby lobby; // номер лобби
	private Elevator[] elevator = new Elevator[3]; // 3 эскалатора
	private List<Passenger> passlist; // Список пассажиров

	public Station() {
		this.passlist = new ArrayList<Passenger>();
		this.lobby = new Lobby();
	}

	public void createElevator() {
		getLobby().setStation(this);

		for (int i = 0; i < 3; i++) {
			getElevator()[i] = new Elevator();
			getElevator()[i].setStation(this);
			getElevator()[i].setId(getId() + i * 100);
		}
	}

	public void stationRun() {
		Thread lobbyThread = new Thread(getLobby());
		lobbyThread.start();

		Thread elevatorThread0 = new Thread(getElevator()[0]);
		elevatorThread0.start();

		Thread elevatorThread1 = new Thread(getElevator()[1]);
		elevatorThread1.start();

		Thread elevatorThread2 = new Thread(getElevator()[2]);
		elevatorThread2.start();
	}

	@Override
	public void run() {
		do {
			try {
				Thread.sleep(500);
				// Переход пассажира с эскалатора на станцию
				for (Elevator elevator : getElevator()) {
					synchronized (elevator.getPasslist()) {
						if (!elevator.getPasslist().isEmpty()) {
							synchronized (getPasslist()) {
								getPasslist().add(elevator.getPasslist().get(0));
								getPasslist().notifyAll();
							}
							System.out.println("New passanger " + elevator.getPasslist().get(0).getId()
									+ " on the station " + this.getId());
							elevator.getPasslist().remove(0);
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
