package Metro;

import java.util.Iterator;

public class Metro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Depo depo = new Depo();

		int linecount = 1;
		for (int i = 0; i < 3; i++) {
			Iterator<Train> itertrain = depo.getLine()[i].getTrainlist().iterator();
			while (itertrain.hasNext()) {
				Train train = itertrain.next();
				TrainMovingLine tml = new TrainMovingLine(linecount, depo.getDriverlist().poll(), train, train.getLine());
				depo.getDriverlist().add(tml.getDriver());
				linecount++;
				int stationcount = 1;
				Iterator<Station> iterstation = depo.getLine()[i].getStationlist().iterator();
				while (iterstation.hasNext()) {
					TrainVisitStation tvs = new TrainVisitStation(stationcount, iterstation.next(), tml);
					stationcount++;
				}
			}
			
		}

	}
}