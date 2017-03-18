package Metro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Depo {
	List<RailwayCarriage> wagonlist; // Коллекция вагонов
	List<Train> trainlist; // Коллекция поездов
	
	// создание коллекции вагонов
	public Depo() throws CloneNotSupportedException {
		wagonlist = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			wagonlist.add(rcBuilder());
		}
		((ArrayList<RailwayCarriage>) wagonlist).trimToSize();
		trainlist = new ArrayList<>();
	}

	// Метод создания случайного вагона
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
	
	// Метод создания поездов в депо
	public void trainsBuilder() {
		
	}
	
	// Метод создания поезда из вагонов депо
	public Train trainBuilder(String driver) throws CloneNotSupportedException {

		Train train = new Train();
		train.setDriver(driver);

		Iterator<RailwayCarriage> iterwl = wagonlist.iterator();
		List<RailwayCarriage> listRC = new ArrayList<>();

		while (iterwl.hasNext() && listRC.size() < 5) {
			RailwayCarriage temp = iterwl.next();

			if (temp.getType() && (listRC.size() == 0 || listRC.size() == 4)) {
				listRC.add(listRC.size(), temp);
				System.out.println(listRC.size());
			}
			if (!temp.getType() && (listRC.size() > 0 || listRC.size() < 4)) {
				listRC.add(listRC.size(), temp);
				System.out.println(listRC.size());
			}

			iterwl.remove();
		}
		
		train.setListRC(listRC);

		// Train train = new Train();
		// train.setDriver(driver);
		//
		// RailwayCarriage rcm[] = new RailwayCarriage[5];
		//
		// for (int i = 0; i < 5; i++) {
		// if (i == 0 || i == 4) {
		// rcm[i] = rcBuilder();
		// if (!rcm[i].getType()) {
		// i -= 1;
		// }
		// } else {
		// rcm[i] = rcBuilder();
		// if (rcm[i].getType()) {
		// i -= 1;
		// }
		// }
		// }
		//
		// train.setRcm(rcm);

		return train;
	}

}
