package Metro;

import java.util.Random;

public class Depo {
	
	// Метод создания случйаного вагона
	public static RailwayCarriage rcBuilder() throws CloneNotSupportedException {
		// Создание главного вагона и обычного вагона
		RailwayCarriage rcm = new RailwayCarriage();
		rcm.setType(true);
		RailwayCarriage rcu = new RailwayCarriage();
		rcu.setType(false);

		if ((new Random().nextInt(2)) > 0) {
			return rcm.clone();
		} else {
			return rcu.clone();
		}
	}

	// Метод создания поезда из вагонов депо
	public Train trainBuilder(String driver) throws CloneNotSupportedException {
		Train train = new Train();
		train.setDriver(driver);

		RailwayCarriage rcm[] = new RailwayCarriage[5];

		for (int i = 0; i < 5; i++) {
			if (i == 0 || i == 4) {
				rcm[i] = rcBuilder();
				if (!rcm[i].getType()) {
					i -= 1;
				}
			} else {
				rcm[i] = rcBuilder();
				if (rcm[i].getType()) {
					i -= 1;
				}
			}
		}

		train.setRcm(rcm);

		return train;
	}

}
