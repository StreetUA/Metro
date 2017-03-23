package Metro;

import java.io.Serializable;

public class RailwayCarriage implements Cloneable, Serializable {
	private boolean type;	// True - �������� ����� False - ������� �����
	private int id;			// ����� ������
	
	// ����� ������������ �������
	public RailwayCarriage clone() throws CloneNotSupportedException {
		RailwayCarriage cloned = (RailwayCarriage) super.clone();
		return cloned;
	}
	
	// ���� � ����� ���� ������
	public void setType(boolean type) {
		this.type = type;
	}
	
	public boolean getType() {
		return this.type;
	}

	// ���� � ����� ������ ������
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}