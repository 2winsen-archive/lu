package lu.lv.gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.List;

import lu.lv.chart.Chart;
import lu.lv.chart.ChartItem;

@SuppressWarnings("serial")
public class ChartCanvas extends Canvas {
	
	private Boolean clear = true;
	private List<ChartItem> data;
	
	private Chart chart;
	
	public Boolean getClear() {
		return clear;
	}
	
	public void setClear(Boolean clear) {
		this.clear = clear;
	}

	public List<ChartItem> getData() {
		return data;
	}

	public void setData(List<ChartItem> data) {
		this.data = data;
	}
	
	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (!clear && chart != null && data != null )
		{
			chart.drawChart(g, this, data);
		}
	}

}
