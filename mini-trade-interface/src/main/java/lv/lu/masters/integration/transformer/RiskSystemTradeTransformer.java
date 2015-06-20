package lv.lu.masters.integration.transformer;

import lv.lu.masters.businessobjects.Trade;

import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

@Component
public class RiskSystemTradeTransformer {

	@Transformer
	public Trade transformForRiskSystem(Trade trade) {
		return trade;
	}
	
}
