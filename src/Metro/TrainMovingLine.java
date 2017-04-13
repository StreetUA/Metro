package Metro;

import java.util.Iterator;
import java.util.Random;

public class TrainMovingLine implements Runnable {
	private int id; // ����
	private Driver driver; // �������� ������ �� �����
	private Train train; // ����� �� �����
	private Line line; // �����

	public TrainMovingLine() {
	}

	// ������� ���� ���������
	public TrainMovingLine(int id, Driver driver, Train train, Line line) {

		// �������� ����������� �����
		int temp;
		if ((new Random().nextInt(2)) > 0) {
			temp = 5;
		} else {
			temp = -5;
		}
		driver.setExp(driver.getExp() + temp);

		this.line = line;
		this.id = id;
		this.driver = driver;
		this.train = train;
	}

	@Override
	public void run() {
		// �������� ������ �� ������� � �������
		Iterator<Station> stationiter = this.getLine().getStationlist().iterator();
		try {
			while (stationiter.hasNext()) {
				Thread.sleep(30000);
				Station station = stationiter.next();
				TrainVisitStation tvs = new TrainVisitStation(this.getId() * 100 + station.getId(), station, this);
				Thread StationThread = new Thread(tvs);
				StationThread.start();
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

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

}
