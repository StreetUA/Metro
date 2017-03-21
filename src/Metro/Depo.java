package Metro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Depo {
	private List<RailwayCarriage> wagonlist; // Коллекция вагонов
	private List<Train> trainlist; // Коллекция поездов
	
	public List<Train> getTrainlist() {
		return this.trainlist;
	}
	
	// создание коллекции вагонов
	public Depo() throws CloneNotSupportedException {
		wagonlist = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			RailwayCarriage temp = rcBuilder();
			temp.setId(i);
			wagonlist.add(temp);
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
	public void trainsBuilder() throws CloneNotSupportedException {
		Iterator<RailwayCarriage> iterwl = wagonlist.iterator();
		int i = 1;
		while ((iterwl).hasNext()) {
			trainlist.add(trainBuilder(i));
			i++;
		}
	}

	// Метод создания поезда из вагонов депо
	public Train trainBuilder(int id) throws CloneNotSupportedException {

		Train train = new Train();
		train.setId(id);

		Iterator<RailwayCarriage> iterwl = wagonlist.iterator();
		List<RailwayCarriage> listRC = new ArrayList<>();

		while (iterwl.hasNext() && listRC.size() < 5) {
			RailwayCarriage temp = iterwl.next();
			if (listRC.size() < 2) {
				if (temp.getType()) {
					listRC.add(temp);
				}
			} else {
				if (!temp.getType()) {
					listRC.add(1, temp);
				}
			}
			iterwl.remove();
		}
		((ArrayList<RailwayCarriage>) wagonlist).trimToSize();
				
		train.setListRC(listRC);
		
		return train;
	}

}
