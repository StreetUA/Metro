package Metro;

import java.util.ArrayList;
import java.util.List;

public class Station implements Runnable {
	private int id; // Номер санции
	private Line line; // Номер линии
	private Lobby lobby; // номер лобби
	private Elevator[] elevator = new Elevator[4]; // 4 эскалатора
	private List<Passenger> passlist; // Список пассажиров

	public Station() {
		this.passlist = new ArrayList<Passenger>();
		this.lobby = new Lobby();
	}

	public void createElevator() {
		getLobby().setStation(this);

		for (int i = 0; i < elevator.length; i++) {
			getElevator()[i] = new Elevator();
			getElevator()[i].setStation(this);
			getElevator()[i].setId(getId() + i * 100);
			getElevator()[i].setUpdown(i % 2 == 0);
		}
	}

	public void stationRun() {
		Thread lobbyThread = new Thread(getLobby());
		lobbyThread.start();

		for (int i = 0; i < elevator.length; i++) {
			Thread elevatorThread = new Thread(getElevator()[i]);
			elevatorThread.start();
		}
	}

	@Override
	public void run() {
		do {
			try {
				Thread.sleep(100);
				// Переход пассажира
				for (Elevator elevator : getElevator()) {
					if (!elevator.isUpdown()) {
						// с эскалатора на станцию
						synchronized (elevator.getPasslist()) {
							if (!elevator.getPasslist().isEmpty()) {
								synchronized (getPasslist()) {
									getPasslist().add(elevator.getPasslist().get(0));
									elevator.getPasslist().remove(0);
									getPasslist().notifyAll();
								}
							}
						}
					} else {
						// со станции на эскалатор
						synchronized (getPasslist()) {
							if (!getPasslist().isEmpty()) {
								if (getPasslist().get(0).isInout()) {
									synchronized (elevator.getPasslist()) {
										elevator.getPasslist().add(getPasslist().get(0));
										getPasslist().remove(0);
										getPasslist().notifyAll();
									}
								}
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
