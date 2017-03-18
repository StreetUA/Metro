package Metro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Depo {
	List<RailwayCarriage> wagonlist; // ��������� �������
	List<Train> trainlist; // ��������� �������
	
	// �������� ��������� �������
	public Depo() throws CloneNotSupportedException {
		wagonlist = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			wagonlist.add(rcBuilder());
		}
		((ArrayList<RailwayCarriage>) wagonlist).trimToSize();
		trainlist = new ArrayList<>();
	}

	// ����� �������� ���������� ������
	public static RailwayCarriage rcBuilder() throws CloneNotSupportedException {
		// �������� �������� ������ � �������� ������
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
	
	// ����� �������� ������� � ����
	public void trainsBuilder() {
		
	}
	
	// ����� �������� ������ �� ������� ����
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
