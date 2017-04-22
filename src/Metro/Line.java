package Metro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Line implements Runnable {
	private int id; // Номер линии
	private List<Station> stationlist; // Список станций
	private List<Train> trainlist; // Список поездов на линии
	private Depo depo;

	public Line() {
		this.trainlist = new ArrayList<Train>();
		this.stationlist = new ArrayList<Station>();
	}

	@Override
	public void run() {
		int count = 0;
		// Запуск поездов на линии
		do {
			try {
				count++;
				Thread.sleep(new Random().nextInt(500));
				synchronized (getTrainlist()) {
					if (getTrainlist().isEmpty()) {
						getTrainlist().wait();
					} else {
						synchronized (getDepo().getDriverlist()) {
							if (getDepo().getDriverlist().isEmpty()) {
								getDepo().getDriverlist().wait();
							} else {
								TrainMovingLine tml = new TrainMovingLine(count*100 + getId(), getDepo().getDriverlist(),
										getTrainlist(), this);
								Thread tmlThread = new Thread(tml);
								tmlThread.start();
							}
						}
					}
				}

//				// Проверка опыта водителей
//				synchronized (getDepo().getDriverlist()) {
//					List<Driver> temp = new ArrayList<>();
//					while (!getDepo().getDriverlist().isEmpty()) {
//						Driver driver = getDepo().getDriverlist().poll();
//						System.out.print(driver.getId() + "(" + driver.getExp() + ") ");
//						temp.add(driver);
//					}
//					System.out.println();
//
//					Iterator<Driver> tempIter = temp.iterator();
//					while (tempIter.hasNext()) {
//						getDepo().getDriverlist().add(tempIter.next());
//					}
//				}

			} catch (InterruptedException e) {
			}
		} while (true);
	}

	// Создание станций
	public void stationCreate() {
		for (int i = 1; i < 11; i++) {
			Station station = new Station();
			station.setId(getId() + i * 100);
			station.setLine(this);
			getStationlist().add(station);
			station.createElevator();
		}
	}

	public void lineRun() {
		this.getStationlist().get(0).stationRun();
		Thread stationThread = new Thread(getStationlist().get(0));
		stationThread.start();

		// Iterator<Station> stationiter = this.getStationlist().iterator();
		// while (stationiter.hasNext()) {
		// stationiter.next().stationRun();
		// }
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Station> getStationlist() {
		return stationlist;
	}

	public void setStationlist(List<Station> stationlist) {
		this.stationlist = stationlist;
	}

	public List<Train> getTrainlist() {
		return trainlist;
	}

	public void setTrainlist(List<Train> trainlist) {
		this.trainlist = trainlist;
	}

	public Depo getDepo() {
		return depo;
	}

	public void setDepo(Depo depo) {
		this.depo = depo;
	}

}
