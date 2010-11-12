package lu.lv.gui.model;

import java.util.List;

import lu.lv.chart.ChartItem;

public class ChartDAOImpl implements ChartDAO {

	private final BaseModel model = new BaseModel();
	
	@Override
	public void storeData(List<ChartItem> data) {
		model.setItems(data);
	}

	@Override
	public List<ChartItem> getData() {
		return model.getItems();
	}

}
