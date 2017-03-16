package Metro;

import java.io.Serializable;

public class Train implements Serializable {
	private String driver;									// Машинист
	private RailwayCarriage rcm[] = new RailwayCarriage[5]; // Массив из 5
															// клонированых
															// вагонов

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
	
}