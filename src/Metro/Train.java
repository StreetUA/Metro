package Metro;

import java.io.Serializable;
import java.util.List;

public class Train implements Serializable {
	private int id ;										// ����� ������
	private List<RailwayCarriage> listRC;	// ��������� �� �������
	
	private static final long serialVersionUID = 1;

	// ���� � ����� ������ ������
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	// ���� � ����� ���� ��������� �������
	public List<RailwayCarriage> getListRC() {
		return listRC;
	}

	public void setListRC(List<RailwayCarriage> listRC) {
		this.listRC = listRC;
	}

}