package Metro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Elevator implements Runnable {
	private int id; // ����� ����������
	private Station station; // ����� �������
	private List<Passenger> passlist; // ������ ����������

	public Elevator() {
		this.passlist = new ArrayList<Passenger>();
	}

	@Override
	public void run() {
		// Thread.sleep(2000);
		do {
			try {
				Thread.sleep(500);
				// ������� ��������� �� ��������� �� ���������
				synchronized (getStation().getLobby().getPasslist()) {
					if (getStation().getLobby().getPasslist().isEmpty()) {
						getStation().getLobby().getPasslist().wait();
					} else {
						synchronized (getPasslist()) {
							getPasslist().add(getStation().getLobby().getPasslist().get(0));
						}
						System.out.println("New passanger " + getStation().getLobby().getPasslist().get(0).getId()
								+ " on the elevator " + getId());
						getStation().getLobby().getPasslist().remove(0);
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
