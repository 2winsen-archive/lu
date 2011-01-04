package lu.lv.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lu.lv.chart.Chart;
import lu.lv.chart.ChartItem;
import lu.lv.chart.PieChart;
import lu.lv.gui.ChartCanvas;
import lu.lv.gui.model.ChartDAO;
import lu.lv.gui.model.ChartDAOImpl;
import lu.lv.utils.ColorUtils;

public class FrontController {

	private final ChartDAO dao = new ChartDAOImpl();
	private final Chart chart = new PieChart();

	public void processData(Map<String, Object> data) {
		Integer total = 0;
		List<ChartItem> chartItems = new ArrayList<ChartItem>();
		for (Object value : data.values()) {
			if (value != null && value.toString() != "" ) {
				total += ((Integer)value);
			}
		}
		for (Entry<String, Object> item : data.entrySet()) {
			Double value = ((Integer)item.getValue()*100.0)/total;
			value = (double) Math.round(value);
			
			ChartItem chartItem = new ChartItem();
			chartItem.setTitle(item.getKey());
			chartItem.setValue(value.intValue());
			chartItem.setColor(ColorUtils.getChartItemColorByKey(item.getKey()));
			chartItems.add(chartItem);
		}
		dao.storeData(chartItems);
	}

	public void draw(ChartCanvas canvas) {
		canvas.setClear(false);
		canvas.setData(dao.getData());
		canvas.setChart(chart);
	}

}
