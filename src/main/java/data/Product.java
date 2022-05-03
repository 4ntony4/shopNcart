package data;

import java.time.Instant;

import org.json.JSONObject;

/**
 * @author Diogo Antão
 *
 * 04-2022
 */

public class Product {
	
	private long productId;
	private String productDs;
	private ProductStatus productSt;
	private JSONObject productJd;
	private String productImageCd;
	private CurrencyCode currencyCd;
	private Instant startDt;
	private float priceVl;
	private float previousPriceVl;
	private PriceStatus priceSt;	
	private PriceStatus previousPriceSt;
	
	public Product() {}
	
	public Product(
			long productId,
			String productDs,
			ProductStatus productSt,
			JSONObject productJd,
			String productImageCd	
			) {
		
		this.productId = productId;
		setProductDs(productDs);
		this.productSt = productSt;
		this.productJd = productJd;
		setProductImageCd(productImageCd);
	}

	public Product(
			long productId,
			String productDs,
			ProductStatus productSt,
			JSONObject productJd,
			String productImageCd,
			CurrencyCode currencyCd,
			Instant startDt,
			float priceVl,
			float previousPriceVl,
			PriceStatus priceSt,
			PriceStatus previousPriceSt
			) {

		this.productId = productId;
		setProductDs(productDs);
		this.productSt = productSt;
		this.productJd = productJd;
		setProductImageCd(productImageCd);
		this.currencyCd = currencyCd;
		this.startDt = startDt;
		this.priceVl = priceVl;
		this.previousPriceVl = previousPriceVl;
		this.priceSt = priceSt;
		this.previousPriceSt = previousPriceSt;
	}

	public long getProductId() {
		return this.productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductDs() {
		return this.productDs;
	}

	public void setProductDs(String productDs) {
		if (Constants.verifyLength(productDs)) {
			this.productDs = productDs;
		}
	}

	public ProductStatus getProductSt() {
		return this.productSt;
	}

	public void setProductSt(ProductStatus productSt) {
		this.productSt = productSt;
	}

	public JSONObject getProductJd() {
		return this.productJd;
	}
	
	public void setProductJd(JSONObject productJd) {
		this.productJd = productJd;
	}
	
	public String getProductImageCd() {
		return this.productImageCd;
	}

	public void setProductImageCd(String productImageCd) {
		if (Constants.verifyLength(productImageCd)) {
			this.productImageCd = productImageCd;
		}
	}
	
	public CurrencyCode getCurrencyCd() {
		return this.currencyCd;
	}

	public void setCurrencyCd(CurrencyCode currencyCd) {
		this.currencyCd = currencyCd;
	}

	public Instant getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Instant startDt) {
		this.startDt = startDt;
	}

	public float getPriceVl() {
		return this.priceVl;
	}

	public void setPriceVl(float priceVl) {
		this.priceVl = priceVl;
	}

	public float getPreviousPriceVl() {
		return this.previousPriceVl;
	}

	public void setPreviousPriceVl(float previousPriceVl) {
		this.previousPriceVl = previousPriceVl;
	}

	public PriceStatus getPriceSt() {
		return this.priceSt;
	}

	public void setPriceSt(PriceStatus priceSt) {
		this.priceSt = priceSt;
	}

	public PriceStatus getPreviousPriceSt() {
		return this.previousPriceSt;
	}

	public void setPreviousPriceSt(PriceStatus previousPriceSt) {
		this.previousPriceSt = previousPriceSt;
	}


}
