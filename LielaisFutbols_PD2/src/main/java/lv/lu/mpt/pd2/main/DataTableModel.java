package lv.lu.mpt.pd2.main;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class DataTableModel extends DefaultTableModel {

	public DataTableModel() {
		super();
	}
	
	public DataTableModel(Object[][] objects, Object[] objects2) {
		super(objects, objects2);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
