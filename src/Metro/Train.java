package Metro;

import java.io.Serializable;
import java.util.List;

public class Train implements Serializable {
	private int id; // Номер поезда
	private List<RailwayCarriage> listRC; // Коллекция из вагонов
	private Line line; // Номер линии

	private static final long serialVersionUID = 1;

	public void trainCheck() {

		System.out.println(getId());

		for (RailwayCarriage rc : getListRC()) {
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

	public List<RailwayCarriage> getListRC() {
		return listRC;
	}

	public void setListRC(List<RailwayCarriage> listRC) {
		this.listRC = listRC;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

}