package lu.lv.gui.model;

import java.util.List;

import lu.lv.chart.ChartItem;

public interface ChartDAO {
	
	public void storeData(List<ChartItem> data);
	
	public List<ChartItem> getData();

}
