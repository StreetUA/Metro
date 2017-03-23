package Metro;

import java.io.Serializable;
import java.util.List;

public class Train implements Serializable {
	private int id ;										// Номер поезда
	private List<RailwayCarriage> listRC;	// Коллекция из вагонов
	
	private static final long serialVersionUID = 1;

	// Ввод и вывод номера поезда
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	// Ввод и вывод поля коллекции вагонов
	public List<RailwayCarriage> getListRC() {
		return listRC;
	}

	public void setListRC(List<RailwayCarriage> listRC) {
		this.listRC = listRC;
	}

}