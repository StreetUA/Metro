package Metro;

import java.io.Serializable;
import java.util.List;

public class Train implements Serializable {
	private int id; // Номер поезда
	private List<Wagon> wagonlist; // Коллекция из вагонов

	private static final long serialVersionUID = 1;

	public void trainCheck() {

		System.out.println(getId());

		for (Wagon rc : getWagonlist()) {
			if (rc.getType() == true) {
				System.out.print(1 + "(" + rc.getId() + ")" + " ");
			} else {
				System.out.print(0 + "(" + rc.getId() + ")" + " ");
			}
		}

		System.out.println();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public List<Wagon> getWagonlist() {
		return wagonlist;
	}

	public void setWagonlist(List<Wagon> wagonlist) {
		this.wagonlist = wagonlist;
	}

	

}