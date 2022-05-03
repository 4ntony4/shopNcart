package data;

import java.time.Instant;

/**
 * @author Diogo Antão
 *
 * 04-2022
 */

public class SiteSession {
	
	private long siteId;
	private String sessionTk;
	private long clientId;
	private Instant createdDt;
	private Instant updatedDt;
	
	public SiteSession() {}
	
	public SiteSession(
			long siteId,
			String sessionTk,
			long clientId,
			Instant createdDt,
			Instant updatedDt
			) {
		
		this.siteId = siteId;
		setSessionTk(sessionTk);
		this.clientId = clientId;
		this.createdDt = createdDt;
		this.updatedDt = updatedDt;		
	}
	
	
	public long getSiteId() {
		return this.siteId;
	}
	
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}

	public String getSessionTk() {
		return this.sessionTk;
	}

	public void setSessionTk(String sessionTk) {
		if (Constants.verifyLength(sessionTk)) {
			this.sessionTk = sessionTk;
		}
	}

	public long getClientId() {
		return this.clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public Instant getCreatedDt() {
		return this.createdDt;
	}

	public void setCreatedDt(Instant createdDt) {
		this.createdDt = createdDt;
	}

	public Instant getUpdatedDt() {
		return this.updatedDt;
	}

	public void setUpdatedDt(Instant updatedDt) {
		this.updatedDt = updatedDt;
	}
	
}
