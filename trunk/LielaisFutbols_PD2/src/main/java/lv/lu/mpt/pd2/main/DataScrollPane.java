package lv.lu.mpt.pd2.main;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class DataScrollPane extends JScrollPane {
	
	private JTable table = new JTable();
	
	private DataTableModel model;
	
	public DataTableModel getModel() {
		return model;
	}

	public void setModel(DataTableModel model) {
		this.model = model;
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.model);
		table.setRowSorter(sorter);
		table.setModel(this.model);
	}

	public DataScrollPane() {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setViewportView(table);
	}
	
}
