package Metro;

import java.util.Random;

public class TrainMovingLine {
	private int id;
	private Driver driver;
	private Train train;
	private Line line;

	public TrainMovingLine(int id, Driver driver, Train train, Line line) {
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

	public Driver getDriver() {
		return driver;
	}

	private void setDriver(Driver driver) {
		this.driver = driver;
	}

}
