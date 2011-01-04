package lu.lv.chart;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import lu.lv.gui.ChartCanvas;

public class PieChart implements Chart {

	@Override
	public void drawChart(Graphics graphics, ChartCanvas canvas, List<ChartItem> data) {
		Graphics2D g = (Graphics2D) graphics;
		// Get total value of all slices
		double total = 0.0D;
		for (ChartItem chartItem : data) {
			total += chartItem.getValue();
		}

		// Draw each pie slice
		int startAngle = 0;
		for (ChartItem chartItem : data) {
			int arcAngle = (int) Math.round(chartItem.getValue() * 360 / total);

			// Ensure that rounding errors do not leave a gap between the first and last slice
			if (chartItem.hashCode() == data.get(data.size() - 1).hashCode()) {
				arcAngle = 360 - startAngle;
			}

			// Set the color and draw a filled arc
			g.setColor(chartItem.getColor());
			
			System.out.println(startAngle);
			System.out.println(arcAngle);
			System.out.println();
			
			
			g.fillArc(0, 0, canvas.getWidth(), canvas.getHeight(), startAngle, arcAngle);

			startAngle = startAngle + arcAngle;
		}
	}

}
