package Metro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TrainVisitStation {
	private int id;
	private Station station;
	private TrainMovingLine tml;
	private List<Passenger> passin;
	private List<Passenger> passout;

	public TrainVisitStation() {
	}

	public TrainVisitStation(int id, Station station, TrainMovingLine tml) {
		passin = new ArrayList<>();
		passout = new ArrayList<>();
		this.setId(id);
		this.setStation(station);
		this.setTml(tml);

		station.randomPassOnStation();

		// Проверка
		System.out.println();
		System.out.println("Arive train " + tml.getTrain().getId());

		// Итератор вагонов в поезде
		Iterator<Wagon> iterwagon = tml.getTrain().getWagonlist().iterator();
		iterwagon.next();

		for (int i = 0; i < 3; i++) {
			List<Passenger> temp = new ArrayList<>();
			Wagon wagon = iterwagon.next();

			// Проверка приход на станцию
			System.out.println("On station " + station.getId() + ": " + station.getPasslist().size() + " passegers");
			System.out.println("On wagon " + wagon.getId() + ": " + wagon.getPasslist().size() + " passegers");

			// Итератор пассажиров в вагоне
			Iterator<Passenger> iterpasswagon = wagon.getPasslist().iterator();
			int passwagon = 0;
			if (!wagon.getPasslist().isEmpty()) {
				passwagon = new Random().nextInt(wagon.getPasslist().size());
				for (int j = 0; j <= passwagon; j++) {
					temp.add(iterpasswagon.next());
				}
				wagon.getPasslist().removeAll(temp);
				getStation().getPasslist().addAll(temp);
			}
			this.setPassout(temp);

			// Проверка выход из поезда
			System.out.println("Passout");
			System.out.println("On station " + station.getId() + ": " + station.getPasslist().size() + " passegers");
			System.out.println("On wagon " + wagon.getId() + ": " + wagon.getPasslist().size() + " passegers");

			// Итератор пассажиров на станции
			Iterator<Passenger> iterpassstation = this.getStation().getPasslist().iterator();
			temp = new ArrayList<>();
			passwagon = 0;
			if (!wagon.getPasslist().isEmpty()) {
				passwagon = wagon.getPasslist().size();
			}
			int fullload = 30;
			if (this.getStation().getPasslist().size() < 30) {
				fullload = this.getStation().getPasslist().size();
			}
			for (int j = passwagon; j < fullload; j++) {
				temp.add(iterpassstation.next());
			}
			wagon.getPasslist().addAll(temp);
			getStation().getPasslist().removeAll(temp);
			this.setPassin(temp);

			// Проверка вход в поезд
			System.out.println("Passin");
			System.out.println("On station " + station.getId() + ": " + station.getPasslist().size() + " passegers");
			System.out.println("On wagon " + wagon.getId() + ": " + wagon.getPasslist().size() + " passegers");
			System.out.println();

		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public TrainMovingLine getTml() {
		return tml;
	}

	public void setTml(TrainMovingLine tml) {
		this.tml = tml;
	}

	public List<Passenger> getPassin() {
		return passin;
	}

	public void setPassin(List<Passenger> passin) {
		this.passin = passin;
	}

	public List<Passenger> getPassout() {
		return passout;
	}

	public void setPassout(List<Passenger> passout) {
		this.passout = passout;
	}

}
