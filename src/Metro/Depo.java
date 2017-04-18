package Metro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Depo {
	private List<Wagon> wagonlist; // Коллекция вагонов
	private List<Train> trainlist; // Коллекция поездов
	private Line[] line; // Массив линий
	private Queue<Driver> driverlist; // Список водителей

	public Depo() {
		this.readFile();
		// this.trainListBuilder();
		// this.writeFile();

		// Создание линий и станций
		this.line = new Line[3];
		for (int i = 0; i < this.line.length; i++) {
			this.getLine()[i] = new Line();
			this.getLine()[i].setId(i + 1);
			this.getLine()[i].stationCreate();
			this.getLine()[i].setDepo(this);
		}

		// Раздача поездов линиям
		Iterator<Train> triter = getTrainlist().iterator();
		while (triter.hasNext()) {
			for (int i = 0; i < this.line.length; i++) {
				if (triter.hasNext()) {
					getLine()[i].getTrainlist().add(triter.next());
				}
			}
		}

		// Создание списка водителей
		Comparator<Driver> comparator = new Comparator<Driver>() {
			@Override
			public int compare(Driver d1, Driver d2) {
				if (d1.getExp() > d2.getExp()) {
					return -1;
				}
				if (d1.getExp() < d2.getExp()) {
					return 1;
				}
				return 0;
			}
		};

		this.driverlist = new PriorityQueue<>(12, comparator);
		for (int i = 0; i < this.getDriverlist().size(); i++) {
			Driver newdriver = new Driver();
			newdriver.setId(i + 1);
			newdriver.setExp((new Random().nextInt(5)));
			getDriverlist().add(newdriver);
		}
	}

	public void metroRun() {
		for (Line line : getLine()) {
			// Запуск линии, станций на линии, эскалаторов и лобби на станции
			// line.lineRun();
			Thread lineThread = new Thread(line);
			lineThread.start();
		}
	}

	// Метод создания поездов в депо
	private void trainListBuilder() {

		// создание коллекции вагонов
		wagonlist = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			Wagon temp = wagonBuilder();
			temp.setId(i);
			getWagonlist().add(temp);
		}

		// Создание коллекции поездов
		trainlist = new ArrayList<>();

		Iterator<Wagon> iterwagon = getWagonlist().iterator();
		int i = 1;
		while (iterwagon.hasNext()) {
			Train train = trainBuilder(i);
			if (train.getWagonlist().size() == 5) {
				getTrainlist().add(train);
			}
			i++;
		}
	}

	// Метод создания случайного вагона
	private static Wagon wagonBuilder() {
		// Создание главного вагона и обычного вагона
		Wagon rcm = new Wagon();
		rcm.setType(true);
		Wagon rcu = new Wagon();
		rcu.setType(false);

		if ((new Random().nextInt(2)) > 0) {
			return rcm.clone();
		} else {
			return rcu.clone();
		}
	}

	// Метод создания поезда из вагонов депо
	private Train trainBuilder(int id) {

		Train train = new Train();
		train.setId(id);

		Iterator<Wagon> iterwagon = getWagonlist().iterator();
		List<Wagon> wagonlist = new ArrayList<>();

		while (iterwagon.hasNext() && wagonlist.size() < 5) {
			Wagon temp = iterwagon.next();
			if (wagonlist.size() < 2) {
				if (temp.getType()) {
					wagonlist.add(temp);
				}
			} else {
				if (!temp.getType()) {
					wagonlist.add(1, temp);
				}
			}
			iterwagon.remove();
		}

		train.setWagonlist(wagonlist);

		return train;
	}

	// Считывание поездов из файла
	private void readFile() {

		List<Train> trainlist = new ArrayList<>();

		try {
			File startCatalog = new File("..//Metro//Trains//");

			for (File filepath : startCatalog.listFiles()) {
				if (!filepath.isDirectory() && filepath.getName().endsWith(".trn")) {
					try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath))) {
						trainlist.add((Train) in.readObject());
					}
				}
			}
		} catch (IOException ex) {
			System.out.println("Не удалось прочитать файл поезда");
		} catch (ClassNotFoundException ex) {
			System.out.println("Файл в указанной папке не являются файлом поезда");
		}

		setTrainlist(trainlist);

	}

	// Запись поездов в файлы
	private void writeFile() {
		try {
			Iterator<Train> itertrain = getTrainlist().iterator();
			while (itertrain.hasNext()) {
				Train train = itertrain.next();
				try (ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream("..//Metro//Trains//" + "train" + train.getId() + ".trn"))) {
					out.writeObject(train);
				}
			}
		} catch (IOException ex) {
			System.out.println("Не удалось создать файл поезда");
		}
	}

	public List<Train> getTrainlist() {
		return trainlist;
	}

	public void setTrainlist(List<Train> trainlist) {
		this.trainlist = trainlist;
	}

	public Line[] getLine() {
		return line;
	}

	public void setLine(Line[] line) {
		this.line = line;
	}

	public Queue<Driver> getDriverlist() {
		return driverlist;
	}

	public void setDriverlist(Queue<Driver> driverlist) {
		this.driverlist = driverlist;
	}

	public List<Wagon> getWagonlist() {
		return wagonlist;
	}

	public void setWagonlist(List<Wagon> wagonlist) {
		this.wagonlist = wagonlist;
	}

}
