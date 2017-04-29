package Metro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wagon implements Cloneable, Serializable {
	private boolean type; // True - головной вагон False - обычный вагон
	private int id; // Номер вагона
	private List<Passenger> passlist; // Список пассажиров в вагоне
	final int fullload = 30;

	private static final long serialVersionUID = 1;

	public Wagon() {
		this.passlist = new ArrayList<Passenger>();
	}

	// Метод клонирования вагонов
	@Override
	public Wagon clone() {
		Wagon cloned = new Wagon();
		try {
			cloned = (Wagon) super.clone();
			return cloned;
		} catch (CloneNotSupportedException ex) {
			System.out.println("При клонировании вагона " + getId() + " произошла ошибка");
		}
		return cloned;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public boolean getType() {
		return this.type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Passenger> getPasslist() {
		return passlist;
	}

	public void setPasslist(List<Passenger> passlist) {
		this.passlist = passlist;
	}

}