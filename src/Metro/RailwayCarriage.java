package Metro;

import java.io.Serializable;

public class RailwayCarriage implements Cloneable, Serializable {
	private boolean type; // True - головной вагон False - обычный вагон

	// ћетод клонировани€ вагонов
	public RailwayCarriage clone() throws CloneNotSupportedException {
		RailwayCarriage cloned = (RailwayCarriage) super.clone();
		return cloned;
	}
	
	// «аписи и считывани€ типа вагона
	public void setType(boolean type) {
		this.type = type;
	}
	
	public boolean getType() {
		return this.type;
	}

}