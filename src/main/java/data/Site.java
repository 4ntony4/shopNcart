package data;

import org.json.JSONObject;

/**
 * @author Diogo Antão
 *
 * 04-2022
 */

public class Site {
	
	private long siteId;
	private String siteDs;
	private SiteType siteTp;
	private SiteStatus siteSt;
	private JSONObject siteJd;
	
	public Site() {}
	
	public Site(
			long siteId,
			String siteDs,
			SiteType siteTp,
			SiteStatus siteSt,
			JSONObject siteJd
			) {
		
		this.siteId = siteId;
		setSiteDs(siteDs);
		this.siteTp = siteTp;
		this.siteSt = siteSt;
		this.siteJd = siteJd;
	}


	public long getSiteId() {
		return this.siteId;
	}
	
	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}
	
	public String getSiteDs() {
		return this.siteDs;
	}
	
	public void setSiteDs(String siteDs) {
		if (Constants.verifyLength(siteDs)) {
			this.siteDs = siteDs;
		}
	}
	
	public SiteType getSiteTp() {
		return this.siteTp;
	}
	
	public void setSiteTp(SiteType siteTp) {
		this.siteTp = siteTp;
	}
	
	public SiteStatus getSiteSt() {
		return this.siteSt;
	}
	
	public void setSiteSt(SiteStatus siteSt) {
		this.siteSt = siteSt;
	}
	
	public JSONObject getSiteJd() {
		return this.siteJd;
	}
	
	public void setSiteJd(JSONObject siteJd) {
		this.siteJd = siteJd;
	}
	
}
