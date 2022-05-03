package data;

/**
 * @author Diogo Antão
 *
 * 04-2022
 */

@SuppressWarnings("nls")
public enum CurrencyCode {
	
	EUR("€"),
	USD("$"),
	GBP("£");
	
	private String currencySymbol;
	
	CurrencyCode(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	
	public String getCurrencySymbol() {
		return this.currencySymbol;
	}

}
