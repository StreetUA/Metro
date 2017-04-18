package Metro;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class TrainMovingLine implements Runnable {
	private int id; // Ключ
	private Queue<Driver> driverlist; // Водитель поезда на линии
	private List<Train> trainlist; // Поезд на линии
	private Line line; // Линия

	public TrainMovingLine() {
	}

	// Раздаем опыт водителям
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
				train = getTrainlist().get(0);
				getTrainlist().remove(0);
			}

			synchronized (getDriverlist()) {
				driver = getDriverlist().poll();
			}

			System.out.println("Line " + getLine().getId() + " Driver " + driver.getId() + " is moving on train " + train.getId());
			
			// Движение поезда от станции к станции
			Iterator<Station> stationiter = getLine().getStationlist().iterator();
			while (stationiter.hasNext()) {
				Thread.sleep(500);
				Station station = stationiter.next();
				TrainVisitStation tvs = new TrainVisitStation(getId() * 100 + station.getId(), station, train);
				Thread stationThread = new Thread(tvs);
				stationThread.start();
			}

			driver.setExp(driver.getExp() + 5);
			
			synchronized (getDriverlist()) {
				getDriverlist().add(driver);
				getDriverlist().notifyAll();
			}

			synchronized (getTrainlist()) {
				getTrainlist().add(train);
				getTrainlist().notifyAll();
			}

			System.out.println("Line " + getLine().getId() + " Train " + train.getId() + " is back");

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
