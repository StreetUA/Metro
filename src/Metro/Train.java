package Metro;

import java.io.Serializable;
import java.util.List;

public class Train implements Serializable {
	private String driver;									// Машинист
	private RailwayCarriage rcm[] = new RailwayCarriage[5]; // Массив из 5
															// клонированых
															// вагонов
	
	
	private List<RailwayCarriage> listRC;	// Коллекция из вагонов
	
	private static final long serialVersionUID = 1;

	// Ввод и вывод поля массива вагонов
	public RailwayCarriage[] getRcm() {
		return this.rcm;
	}

	public void setRcm(RailwayCarriage[] rcm) {
		this.rcm = rcm;
	}
	
	// Ввод и вывод имени машиниста
	public String getDriver() {
		return this.driver;
	}
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	// Ввод и вывод поля коллекции вагонов
	public List<RailwayCarriage> getListRC() {
		return listRC;
	}

	public void setListRC(List<RailwayCarriage> listRC) {
		this.listRC = listRC;
	}
	
}