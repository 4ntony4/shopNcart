package data;

/**
 * @author Diogo Antão
 *
 * 04-2022
 */

public class SiteClient {
	
	private long siteId;
	private long clientId;
	private String clientDs;
	
	public SiteClient() {}
	
	public SiteClient(
			long siteId,
			long clientId,
			String clientDs
			) {
		
		this.siteId = siteId;
		this.clientId = clientId;
		setClientDs(clientDs);
	}
	
	
	public long getSiteId() {
		return this.siteId;
	}
	
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}

	public long getClientId() {
		return this.clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getClientDs() {
		return this.clientDs;
	}

	public void setClientDs(String clientDs) {
		if (Constants.verifyLength(clientDs)) {
			this.clientDs = clientDs;
		}
	}
	
}
