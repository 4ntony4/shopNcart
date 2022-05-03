package business;

/**
 * @author Diogo Antão
 *
 * 04-2022
 */

@SuppressWarnings("nls")
public enum DBConProperties {
	
	URL("jdbc:mysql://localhost:3306/stm"),
	USER("root"),
	PASSWORD("dmdsca");
	
	private String string;
	
	DBConProperties(String str) {
		this.string = str;
	}
	
	public String getString() {
		return this.string;
	}
	
}
