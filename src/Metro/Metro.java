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

public class Metro {

	public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

		Depo depo = new Depo(readFile());
//		depo.trainsBuilder();
//		writeFile(depo.getTrainlist());

		// проверка поездов
		List<Train> trainlist = depo.getTrainlist();
		Iterator<Train> itertr = trainlist.iterator();
		while (itertr.hasNext()) {
			trainCheck(itertr.next());
		}
	}

	// Проверка вагонов в составе
	public static void trainCheck(Train tr) {
		
		System.out.println(tr.getId());

		for (RailwayCarriage rc : tr.getListRC()) {
			if (rc.getType() == true) {
				System.out.print(1 + "(" + rc.getId() + ")" + " ");
			} else {
				System.out.print(0 + "(" + rc.getId() + ")" + " ");
			}
		}

		System.out.println();
	}

	// Считывание поездов из файла
	public static List<Train> readFile() throws ClassNotFoundException, IOException {
		
		List<Train> trainlist = new ArrayList<>();
		
		File startCatalog = new File("..//Metro//Trains//");
		
		for (File filepath : startCatalog.listFiles()) {
			if (!filepath.isDirectory() && filepath.getName().endsWith(".trn")) {
				try (ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(filepath))) {
					trainlist.add((Train) in.readObject());
				}
			}
		}
		
		return trainlist;
		
	}

	// Запись поездов в файлы
	public static void writeFile(List<Train> listtr) throws IOException {
		Iterator<Train> iterlt = listtr.iterator();
		while (iterlt.hasNext()) {
			Train train = iterlt.next();
			try (ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("..//Metro//Trains//" + "train" + train.getId() + ".trn"))) {
				out.writeObject(train);
			}
		}

	}

}