package Metro;

import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

public class TrainMovingLine implements Runnable {
	private int id; // Ключ
	private Queue<Driver> driverlist; // Водитель поезда на линии
	private List<Train> trainlist; // Поезд на линии
	private Line line; // Линия

	public TrainMovingLine() {
	}

	public TrainMovingLine(int id, Queue<Driver> driverlist, List<Train> trainlist, Line line) {
		this.line = line;
		this.id = id;
		this.driverlist = driverlist;
		this.trainlist = trainlist;
	}

	@Override
	public void run() {
		try {
			Driver driver = new Driver();
			Train train = new Train();

			synchronized (getTrainlist()) {
				if (getTrainlist().isEmpty()) {
					getTrainlist().wait();
				} else {
					train = getTrainlist().get(0);
					getTrainlist().remove(0);
				}
			}

			synchronized (getDriverlist()) {
				driver = getDriverlist().poll();
			}

			System.out.println(
					"Line " + getLine().getId() + " Driver " + driver.getId() + " is moving on Train " + train.getId());

			// Движение поезда от станции к станции
			ListIterator<Station> stationiter = getLine().getStationlist().listIterator();
			while (stationiter.hasNext()) {
				Thread.sleep(1000);
				Station station = stationiter.next();
				TrainVisitStation tvs = new TrainVisitStation(getId() * 100 + station.getId(), station, train);
				tvs.runTVS();
				tvs.start.countDown();
				tvs.finish.await();
				System.out
						.println("Line " + getLine().getId() + " Train " + train.getId() + " Station " + station.getId()
								+ " " + tvs.countpassout + " passengers out and " + tvs.countpassin + " passengers in");
				System.out.println("Lobby pass " + station.getLobby().getPasslist().size() + " in "
						+ station.getLobby().in + " out " + station.getLobby().out);
				System.out.println("Station pass " + station.getPasslist().size());
			}
			while (stationiter.hasPrevious()) {
				Thread.sleep(1000);
				Station station = stationiter.previous();
				TrainVisitStation tvs = new TrainVisitStation(getId() * 100 + station.getId(), station, train);
				tvs.runTVS();
				tvs.start.countDown();
				tvs.finish.await();
				System.out
						.println("Line " + getLine().getId() + " Train " + train.getId() + " Station " + station.getId()
								+ " " + tvs.countpassout + " passengers out and " + tvs.countpassin + " passengers in");
				System.out.println("Lobby pass " + station.getLobby().getPasslist().size() + " in "
						+ station.getLobby().in + " out " + station.getLobby().out);
				System.out.println("Station pass " + station.getPasslist().size());
			}

			// Раздаем опыт водителям
			driver.setExp(driver.getExp() + 5);

			synchronized (getDriverlist()) {
				getDriverlist().add(driver);
				getDriverlist().notifyAll();
			}

			synchronized (getTrainlist()) {
				getTrainlist().add(train);
				getTrainlist().notifyAll();
			}

			System.out.println(
					"Line " + getLine().getId() + " Driver " + driver.getId() + " returned on Train " + train.getId());

		} catch (

		InterruptedException e) {
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Queue<Driver> getDriverlist() {
		return driverlist;
	}

	public void setDriverlist(Queue<Driver> driverlist) {
		this.driverlist = driverlist;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public List<Train> getTrainlist() {
		return trainlist;
	}

	public void setTrainlist(List<Train> trainlist) {
		this.trainlist = trainlist;
	}

}
