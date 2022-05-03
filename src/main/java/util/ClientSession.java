package util;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

import facade.Domains;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Diogo Antão
 *
 * 04-2022
 */

public class ClientSession {
	
	private static ConcurrentHashMap<String, ClientSession> allSessions = new ConcurrentHashMap<>();
	private TransactionUtil transactionUtil;
	private String host;
	private String title;
	private long siteId;
	private String sessionTk;
	private long clientId;
	private String clientDs;
	private Instant createdDt;
	private Instant updatedDt;
	private static int counter;
	
	private static ThreadLocal<ClientSession> threadLocal =
			new ThreadLocal<>() {
		
		@Override
		protected ClientSession initialValue() {
			return null;
		}
	};
	
	// returns the thread local singleton instance
	public static ClientSession getSession() {
		return threadLocal.get();
	}
	
	@SuppressWarnings("nls")
	private ClientSession(HttpServletRequest request) {
		this.transactionUtil = new TransactionUtil();
		this.host = request.getHeader("host");
		this.title = setTitle();
		this.siteId = setSiteId();
		this.sessionTk = request.getSession().getId();
		this.createdDt = Instant.ofEpochMilli(request.getSession().getLastAccessedTime());
		this.updatedDt = Instant.ofEpochMilli(request.getSession().getLastAccessedTime());
		counter++;
	}
	
	public static ClientSession getInstance(HttpServletRequest request) {
		ClientSession clientSession = null;
		
		if (request.getSession().isNew() == true) {
			clientSession = new ClientSession(request);
			threadLocal.set(clientSession);
			allSessions.put(clientSession.sessionTk, clientSession);
		}
		else {
			ClientSession oldSession = allSessions.get(request.getSession().getId());
			oldSession.updatedDt = Instant.ofEpochMilli(request.getSession().getLastAccessedTime());
			threadLocal.set(oldSession);
			return oldSession;
		}
		
		return clientSession;
	}
	
	
	public TransactionUtil getTransactionUtil() {
		return this.transactionUtil;
	}
	
	public String getHost() {
		return this.host;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	private String setTitle() {
		for (Domains domain : Domains.values()) {
			if (this.host.equals(domain.getUrl())) {
				return domain.getTitle();
			}
		}
		return Domains.DEFAULT.getTitle();
	}
	
	public long getSiteId() {
		return this.siteId;
	}
	
	private long setSiteId() {
		for (Domains domain : Domains.values()) {
			if(this.host.equals(domain.getUrl())) {
				return domain.getSiteId();
			}
		}
		return Domains.DEFAULT.getSiteId();
	}
	
	public String getSessionTk() {
		return this.sessionTk;
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
		this.clientDs = clientDs;
	}
	
	public Instant getCreatedDt() {
		return this.createdDt;
	}
	
	public Instant getUpdatedDt() {
		return this.updatedDt;
	}
	
	public static int getNumOfInstances() {
		return counter;
	}
	
	public static int getAllSessionsSize() {
		return allSessions.size();
	}

	@SuppressWarnings("nls")
	public static void refreshOnce(HttpServletRequest request, HttpServletResponse response) {
		
		// check refreshOnce before refresh
	    if (request.getSession().getAttribute("refreshOnce") == null)
	    {
	    	// 0 for refresh without delay
	        response.setHeader("REFRESH", "0");
	        request.getSession().setAttribute("refreshOnce", "false");
	    }
	}
		
}
