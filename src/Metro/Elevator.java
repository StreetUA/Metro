package Metro;

import java.util.ArrayList;
import java.util.List;

public class Elevator implements Runnable {
	private int id; // ����� ����������
	private boolean updown; // �������� ���� � ����
	private Station station; // ����� �������
	private List<Passenger> passlist; // ������ ����������

	public Elevator() {
		this.passlist = new ArrayList<Passenger>();
	}

	@Override
	public void run() {
		do {
			try {
				Thread.sleep(100);
				// ������� ��������� �� ��������� �� ���������
				synchronized (getStation().getLobby().getPasslist()) {
					// �� ��������� �� ���������
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
						// � ��������� � ���������
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
