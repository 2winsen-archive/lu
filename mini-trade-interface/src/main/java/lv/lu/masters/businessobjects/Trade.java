package lv.lu.masters.businessobjects;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Trade {
	
	@Id
	private Long id;
	
	private String buyer;
	
	private String seller;
	
	private BigDecimal amount;
	
	private Integer price;
	
	private Date sentToMiddleware;
	
	private String resultPayment;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getResultPayment() {
		return resultPayment;
	}

	public void setResultPayment(String resultPayment) {
		this.resultPayment = resultPayment;
	}

	public Date getSentToMiddleware() {
		return sentToMiddleware;
	}

	public void setSentToMiddleware(Date sentToMiddleware) {
		this.sentToMiddleware = sentToMiddleware;
	}	

}
