package Metro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TrainVisitStation implements Runnable {
	private int id; // Ключ
	private Station station; // Станция
	private Train train; // Объект перемещения поезда по линии
	private List<Passenger> passin; // Пассажири, которые зашли в поезд
	private List<Passenger> passout; // Пассажири, которые вышли из поезда

	public TrainVisitStation() {
	}

	public TrainVisitStation(int id, Station station, Train train) {
		this.passin = new ArrayList<>();
		this.passout = new ArrayList<>();
		this.id = id;
		this.station = station;
		this.train = train;

	}

	@Override
	public void run() {
		try {
			// Выгрузка из вагона случайного количества пассажиров
			for (int i = 1; i < getTrain().getWagonlist().size() - 1; i++) {
				List<Passenger> temp = new ArrayList<>();
				Wagon wagon = getTrain().getWagonlist().get(i);
				Iterator<Passenger> iterpasswagon = wagon.getPasslist().iterator();
				int passwagon = 0;
				if (!wagon.getPasslist().isEmpty()) {
					passwagon = new Random().nextInt(wagon.getPasslist().size());
					for (int j = 0; j <= passwagon; j++) {
						temp.add(iterpasswagon.next());
					}
					wagon.getPasslist().removeAll(temp);
					synchronized (getStation().getPasslist()) {
						getStation().getPasslist().addAll(temp);
					}
				}
				setPassout(temp);

				// Загрузка в вагоны максимального количества пассажиров
				synchronized (getStation().getPasslist()) {
					if (getStation().getPasslist().isEmpty()) {
						getStation().getPasslist().wait();
					} else {
						Iterator<Passenger> iterpassstation = getStation().getPasslist().iterator();
						temp.clear();
						passwagon = 0;
						if (!wagon.getPasslist().isEmpty()) {
							passwagon = wagon.getPasslist().size();
						}
						int fullload = 30;
						if (getStation().getPasslist().size() < fullload) {
							fullload = getStation().getPasslist().size();
						}
						for (int j = passwagon; j < fullload; j++) {
							temp.add(iterpassstation.next());
						}
						wagon.getPasslist().addAll(temp);
						getStation().getPasslist().removeAll(temp);
						setPassin(temp);
					}
				}
			}
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

	public List<Passenger> getPassin() {
		return passin;
	}

	public void setPassin(List<Passenger> passin) {
		this.passin = passin;
	}

	public List<Passenger> getPassout() {
		return passout;
	}

	public void setPassout(List<Passenger> passout) {
		this.passout = passout;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

}
