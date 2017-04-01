package Metro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wagon implements Cloneable, Serializable {
	private boolean type; // True - �������� ����� False - ������� �����
	private int id; // ����� ������
	private Train train; // ����� ������
	private List<Passenger> passlist;

	private static final long serialVersionUID = 1;

	public Wagon() {
		passlist = new ArrayList<Passenger>();
	}

	// ����� ������������ �������
	@Override
	public Wagon clone() {
		Wagon cloned = new Wagon();
		try {
			cloned = (Wagon) super.clone();
			return cloned;
		} catch (CloneNotSupportedException ex) {
			System.out.println("��� ������������ ������ " + this.getId() + " ��������� ������");
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

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public List<Passenger> getPasslist() {
		return passlist;
	}

	public void setPasslist(List<Passenger> passlist) {
		this.passlist = passlist;
	}

}