package Metro;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Metro {

	public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

		Depo depo = new Depo();
		depo.readFile();
		// depo.trainsBuilder();
		// depo.writeFile();

		// Создание линий и станций
		Line[] line = new Line[3];
		for (int i = 0; i < 3; i++) {
			line[i] = new Line();
			line[i].setId(i + 1);
		}

		// Раздача поездов линиям
		Iterator<Train> triter = depo.getTrainlist().iterator();
		while (triter.hasNext()) {
			for (int i = 0; i < 3; i++) {
				if (triter.hasNext()) {
					line[i].getTrainlist().add(triter.next());
					line[i].getTrainlist().get(line[i].getTrainlist().size() - 1).setLine(line[i]);
				}
			}
		}

		// Проверка
		for (int i = 0; i < 3; i++) {
			System.out.println("Line " + line[i].getId());
			Iterator<Station> iterst = line[i].getStationlist().iterator();
			while (iterst.hasNext()) {
				System.out.print("Station " + iterst.next().getId() + " ");
			}
			System.out.println();
			Iterator<Train> itertrn = line[i].getTrainlist().iterator();
			while (itertrn.hasNext()) {
				System.out.print("Train " + itertrn.next().getId() + " ");
			}
			System.out.println();
		}

		// Поездки водителей по линиям на поездах
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

		Queue<Driver> driverlist = new PriorityQueue<>(20, comparator);
		for (int i = 0; i < 20; i++) {
			Driver newdriver = new Driver();
			newdriver.setId(i + 1);
			newdriver.setExp((new Random().nextInt(5)));
			driverlist.add(newdriver);
		}

		int count = 1;
		Iterator<Train> itertrn = depo.getTrainlist().iterator();
		while (itertrn.hasNext()) {
			Train train = itertrn.next();
			TrainMovingLine tml = new TrainMovingLine(count, driverlist.poll(), train, train.getLine());
			driverlist.add(tml.getDriver());
			count++;
		}

		// Проверка
		System.out.println();
		int x = driverlist.size();
		for (int i = 0; i < x; i++) {
			System.out.print(driverlist.poll().getExp() + " ");
		}

	}
}