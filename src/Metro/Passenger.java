package Metro;

public class Passenger {
	private int id; // Номер пассажира
	private boolean inout; // Вход выход на станции

	public boolean isInout() {
		return inout;
	}

	public void setInout(boolean inout) {
		this.inout = inout;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
