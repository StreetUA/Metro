package Metro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TrainVisitStation {
	private int id; // Ключ
	private Station station; // Станция
	private Train train; // Объект перемещения поезда по линии
	private List<Passenger> passin; // Пассажири, которые зашли в поезд
	private List<Passenger> passout; // Пассажири, которые вышли из поезда

	int countpassin;
	int countpassout;
	CountDownLatch start;
	CountDownLatch finish;

	public TrainVisitStation() {
	}

	public TrainVisitStation(int id, Station station, Train train) {
		this.passin = new ArrayList<>();
		this.passout = new ArrayList<>();
		this.id = id;
		this.station = station;
		this.train = train;
		finish = new CountDownLatch(train.getWagonlist().size() - 2);
		start = new CountDownLatch(1);
		countpassin = 0;
		countpassout = 0;
	}

	public void runTVS() throws InterruptedException {
		for (int i = 1; i < 4; i++) {
			Wagon wagon = getTrain().getWagonlist().get(i);
			new Thread(new Runnable() {
				@Override
				public void run() {
					Passenger passenger = new Passenger();
					int passwagonout = 0;
					try {
						start.await();

						// Выход из поезда
						passwagonout = 0;
						if (!wagon.getPasslist().isEmpty()) {
							passwagonout = new Random().nextInt(wagon.getPasslist().size());
						}
						for (int time = 0; time < wagon.fullload; time++) {
							if (!wagon.getPasslist().isEmpty() && passwagonout > 0) {
								synchronized (getStation().getPasslist()) {
									passenger = wagon.getPasslist().get(0);
									wagon.getPasslist().remove(0);
									getStation().getPasslist().add(passenger);
									countpassout++;
									getStation().getPasslist().notifyAll();
								}
								passwagonout--;
							}
							Thread.sleep(10);
						}

						// Вход в поезд
						for (int time = 0; time < wagon.fullload; time++) {
							synchronized (getStation().getPasslist()) {
								if (!getStation().getPasslist().isEmpty()
										&& wagon.getPasslist().size() < wagon.fullload) {
									if (!getStation().getPasslist().get(0).isInout()) {
										passenger = getStation().getPasslist().get(0);
										passenger.setInout(true);
										getStation().getPasslist().remove(0);
										wagon.getPasslist().add(passenger);
										countpassin++;
									}
								}
								getStation().getPasslist().notifyAll();
							}
							Thread.sleep(10);
						}

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					finish.countDown();
				}
			}).start();
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
