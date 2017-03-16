package Metro;

import java.io.Serializable;

public class Train implements Serializable {
	private String driver;									// ��������
	private RailwayCarriage rcm[] = new RailwayCarriage[5]; // ������ �� 5
															// ������������
															// �������

	private static final long serialVersionUID = 1;

	// ���� � ����� ���� ������� �������
	public RailwayCarriage[] getRcm() {
		return this.rcm;
	}

	public void setRcm(RailwayCarriage[] rcm) {
		this.rcm = rcm;
	}
	
	// ���� � ����� ����� ���������
	public String getDriver() {
		return this.driver;
	}
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
}