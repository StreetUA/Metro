package Metro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Metro {

	public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

		Depo depo = new Depo();
		depo.trainsBuilder();
		List<Train> trainlist = depo.getTrainlist();
		Iterator<Train> itertr = trainlist.iterator();
		while (itertr.hasNext()) {
			trainCheck(itertr.next());
		}


//		Train tr[] = { tr1, tr2 };
//		writeFile(tr);
//		tr = readFile();
//
//		trainCheck(tr1);
//		trainCheck(tr2);

	}

	// Проверка вагонов в составе
	static void trainCheck(Train tr) {
		System.out.println(tr.getId());

		for (RailwayCarriage rc : tr.getListRC()){
			if (rc.getType() == true) {
				System.out.print(1 + "(" + rc.getId() + ")" + " ");
			} else {
				System.out.print(0 + "(" + rc.getId() + ")" + " ");
			}
		}

//			for (int i = 0; i < 5; i++) {
//				if (tr.getRcm()[i].getType() == true) {
//					System.out.print(1 + " ");
//				} else {
//					System.out.print(0 + " ");
//				}
//			}
		
		System.out.println();
	}

	static Train[] readFile() throws ClassNotFoundException, IOException {
		// Счетчик файлов поездов
		int count = 0;
		// Поиск файлов поездов
		File startCatalog = new File("..//Metro//Trains//");
		File[] listFiles = startCatalog.listFiles();
		for (File i : listFiles) {
			if (!i.isDirectory() && i.getName().endsWith(".trn")) {
				count++;
			}
		}

		Train tr[] = new Train[count];

		// Запись из файлов поездов в массив поездов
		for (int i = 0; i < count; i++) {
			try (ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("..//Metro//Trains//" + "train" + (i + 1) + ".trn"))) {
				tr[i] = (Train) in.readObject();
			}
		}
		return tr;
	}

	// Запись массива поездов в файлы
	static void writeFile(Train tr[]) throws IOException {
		for (int i = 0; i < tr.length; i++) {
			try (ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("..//Metro//Trains//" + "train" + (i + 1) + ".trn"))) {
				out.writeObject(tr[i]);
			}
		}
	}

}