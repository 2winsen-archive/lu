package lu.lv.chart;

import java.awt.Graphics;
import java.util.List;

import lu.lv.gui.ChartCanvas;

public interface Chart {

	public void drawChart(Graphics graphics, ChartCanvas canvas, List<ChartItem> data);

}
