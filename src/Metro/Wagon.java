package Metro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wagon implements Cloneable, Serializable {
	private boolean type; // True - �������� ����� False - ������� �����
	private int id; // ����� ������
	private List<Passenger> passlist; // ������ ���������� � ������
	final int fullload = 30;

	private static final long serialVersionUID = 1;

	public Wagon() {
		this.passlist = new ArrayList<Passenger>();
	}

	// ����� ������������ �������
	@Override
	public Wagon clone() {
		Wagon cloned = new Wagon();
		try {
			cloned = (Wagon) super.clone();
			return cloned;
		} catch (CloneNotSupportedException ex) {
			System.out.println("��� ������������ ������ " + getId() + " ��������� ������");
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

	public List<Passenger> getPasslist() {
		return passlist;
	}

	public void setPasslist(List<Passenger> passlist) {
		this.passlist = passlist;
	}

}