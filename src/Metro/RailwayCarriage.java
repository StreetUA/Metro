package Metro;

import java.io.Serializable;

public class RailwayCarriage implements Cloneable, Serializable {
	private boolean type;	// True - головной вагон False - обычный вагон
	private int id;			// Номер вагона
	
	// Метод клонирования вагонов
	public RailwayCarriage clone() throws CloneNotSupportedException {
		RailwayCarriage cloned = (RailwayCarriage) super.clone();
		return cloned;
	}
	
	// Записи и считывания типа вагона
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

}