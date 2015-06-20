package lv.lu.masters.integration.transformer;

import lv.lu.masters.businessobjects.Trade;

import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

@Component
public class PaymentSystemTradeTransformer {

	@Transformer
	public Trade transformForPaymentSystem(Trade trade) {
		return trade;
	}

}
