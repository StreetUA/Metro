package Metro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Depo {
	private List<RailwayCarriage> wagonlist; // Коллекция вагонов
	private List<Train> trainlist; // Коллекция поездов

	public List<Train> getTrainlist() {
		return this.trainlist;
	}
	
	private void setTrainlist(List<Train> trainlist) {
		this.trainlist = trainlist;
	}

	// Метод создания поездов в депо
	public void trainsBuilder() throws CloneNotSupportedException {

		// создание коллекции вагонов
		wagonlist = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			RailwayCarriage temp = rcBuilder();
			temp.setId(i);
			wagonlist.add(temp);
		}
		((ArrayList<RailwayCarriage>) wagonlist).trimToSize();
		
		// Создание коллекции поездов
		trainlist = new ArrayList<>();

		Iterator<RailwayCarriage> iterwl = wagonlist.iterator();
		int i = 1;
		while (iterwl.hasNext()) {
			Train train = trainBuilder(i);
			if (train.getListRC().size() == 5) {
				trainlist.add(train);
			}
			i++;
		}
	}

	// Метод создания случайного вагона
	private static RailwayCarriage rcBuilder() throws CloneNotSupportedException {
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
	private Train trainBuilder(int id) throws CloneNotSupportedException {

		Train train = new Train();
		train.setId(id);

		Iterator<RailwayCarriage> iterwl = wagonlist.iterator();
		List<RailwayCarriage> listRC = new ArrayList<>();

		while (iterwl.hasNext() && listRC.size() < 5) {
			RailwayCarriage temp = iterwl.next();
			temp.setTrain(train);
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

	// Считывание поездов из файла
	public void readFile() throws ClassNotFoundException, IOException {

		List<Train> trainlist = new ArrayList<>();

		File startCatalog = new File("..//Metro//Trains//");

		for (File filepath : startCatalog.listFiles()) {
			if (!filepath.isDirectory() && filepath.getName().endsWith(".trn")) {
				try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath))) {
					trainlist.add((Train) in.readObject());
				}
			}
		}
		
		setTrainlist(trainlist);

	}

	// Запись поездов в файлы
	public void writeFile() throws IOException {
		Iterator<Train> iterlt = getTrainlist().iterator();
		while (iterlt.hasNext()) {
			Train train = iterlt.next();
			try (ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("..//Metro//Trains//" + "train" + train.getId() + ".trn"))) {
				out.writeObject(train);
			}
		}
	}

}
