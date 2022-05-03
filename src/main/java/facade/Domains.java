package facade;

/**
 * @author Diogo Antão
 *
 * 04-2022
 */

@SuppressWarnings("nls")
public enum Domains {
	
	APOLLO("apollo:9999", "Apollo", 1),
	U_APOLLO("uapollo:9999", "Apollo", 1),
	ARTEMIS("artemis:9999", "Artemis", 2),
	U_ARTEMIS("uartemis:9999", "Artemis", 2),
	CHAOS("chaos:9999", "Chaos", 3),
	U_CHAOS("uchaos:9999", "Chaos", 3),
	
	LOCALHOST("localhost:9999", "Apollo", 1),
	LOCALHOST_IP("127.0.0.1:9999", "Apollo", 1),
	UBUNTU("ubuntu:9999", "Apollo", 1),
	UBUNTU_IP("192.168.1.90:9999", "Apollo", 1),
	DEFAULT("192.168.1.90:9999", "Apollo", 1);

	private String url;
	private String title;
	private long siteId;
	
	Domains(String url, String title, long siteId) {
		this.url = url;
		this.title = title;
		this.siteId = siteId;
	}

	public String getUrl() {
		return this.url;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public long getSiteId() {
		return this.siteId;
	}
	
	public static Domains getEnumByTitle(String argTitle) {
		for (Domains domain : Domains.values()) {
			if (domain.title.equals(argTitle)) {
				return domain;
			}
		}
		return Domains.DEFAULT;
	}
	
	public String getStylePath() {
		String path = "\"WebContent/css/";
		
		if (isDefaultDomain()) {
			path += DEFAULT.title.toLowerCase();
		}
		else {
			path += this.title.toLowerCase();
		}
		
		return path + ".css\"";
	}
	
	private boolean isDefaultDomain() {
		return (
				this.title.equals(APOLLO.title) ||
				this.title.equals(U_APOLLO.title) ||
				this.title.equals(LOCALHOST.title) ||
				this.title.equals(LOCALHOST_IP.title) ||
				this.title.equals(UBUNTU.title) || 
				this.title.equals(UBUNTU_IP.title) ||
				this.title.equals(DEFAULT.title)
				);
	}
	
	public String getLogoPath(boolean customColor) {
		String path = "\"WebContent/img/logos/";
		
		if (isDefaultDomain()) {
			path += DEFAULT.title.toLowerCase();
		}
		else {
			path += this.title.toLowerCase();
		}
		
		if (customColor) {
			path += "-e9";
		}
		
		return path + ".svg\"";
	}
	
	public static void PrintAllNames() {
		for (Domains domain : Domains.values()) {
			System.out.println(domain.name() +
							   " :: " +
							   domain.getUrl() +
							   ", " +
							   domain.getTitle() +
							   ", " +
							   domain.getSiteId());
		}
	}

}
