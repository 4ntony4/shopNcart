package facade;

import java.sql.Connection;
import java.time.Instant;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import data.DataAccess;
import data.Product;
import jakarta.servlet.http.HttpServletRequest;
import util.ClientSession;

/**
 * @author Diogo Antão
 *
 * 04-2022
 */

public class Facade {
	
	private ClientSession clientSession;
	private Domains domain;
	
	public Facade(HttpServletRequest request) {
		this.clientSession = ClientSession.getInstance(request);
		this.domain = Domains.getEnumByTitle(this.clientSession.getTitle());
		
		if (request.getSession().isNew() == true) {
			//saveNewGuestClientAndSession();
		}
		else {
			//updateSiteSession();
		}
	}
	
	public String getTitle() {
		return this.clientSession.getTitle();
	}
	
	public String getStylePath() {
		return this.domain.getStylePath();
	}
	
	public String getLogoPath() {
		return this.domain.getLogoPath(false);
	}
	
	public String getLogoPath(boolean customColor) {
		return this.domain.getLogoPath(customColor);
	}
	
	public long getSiteId() {
		return this.clientSession.getSiteId();
	}
	
	public String getSessionTk() {
		return this.clientSession.getSessionTk();
	}
	
	public Instant getCreatedDt() {
		return this.clientSession.getCreatedDt();
	}
	
	public Instant getUpdatedDt() {
		return this.clientSession.getUpdatedDt();
	}
	
	public static int getNumOfInstances() {
		return ClientSession.getNumOfInstances();
	}
	
	public static int getAllSessionsSize() {
		return ClientSession.getAllSessionsSize();
	}
	
	
	@SuppressWarnings("resource")
	/*private*/public void saveNewGuestClientAndSession() {
		
		this.clientSession.getTransactionUtil().startTransaction(Connection.TRANSACTION_READ_UNCOMMITTED);
		
		if (this.clientSession.getTransactionUtil().getConnection() != null) {
			Long lastClientId = DataAccess.getLastClientId();
			if (lastClientId != null) {
				this.clientSession.setClientId(lastClientId.longValue() + 1);
			}
			DataAccess.insertSiteClient();
			DataAccess.insertSiteSession();
			this.clientSession.getTransactionUtil().endTransaction();
		}
	}
	
	@SuppressWarnings("resource")
	/*private*/public void updateSiteSession() {
		
		this.clientSession.getTransactionUtil().startTransaction(Connection.TRANSACTION_READ_UNCOMMITTED);
		
		if (this.clientSession.getTransactionUtil().getConnection() != null) {
			DataAccess.updateSiteSession();
			this.clientSession.getTransactionUtil().endTransaction();
		}
	}
	
	@SuppressWarnings("resource")
	public JSONArray getAllAvlProductsDs() {
		
		this.clientSession.getTransactionUtil().startTransaction(Connection.TRANSACTION_READ_UNCOMMITTED);
		
		if (this.clientSession.getTransactionUtil().getConnection() != null) {
			ArrayList<String> products = DataAccess.selectAllAvlSiteProductsDs();
			this.clientSession.getTransactionUtil().endTransaction();
			
			if (products != null) {
				JSONArray jArray = new JSONArray();
				for (String p : products) {
					jArray.put(p);
				}
				
				///// test
				//System.out.println(jArray.toString(4));
				//System.out.println("JSON Array length = " + jArray.length()); //$NON-NLS-1$
				//System.out.println();
				/////
				
				return jArray;
			}
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("resource")
	public JSONArray getAllAvlProducts() {

		this.clientSession.getTransactionUtil().startTransaction(Connection.TRANSACTION_READ_UNCOMMITTED);
		
		if (this.clientSession.getTransactionUtil().getConnection() != null) {
			ArrayList<Product> products = DataAccess.selectAllAvlSiteProducts();
			this.clientSession.getTransactionUtil().endTransaction();
			
			if (products != null) {
				JSONArray jArray = new JSONArray();
				for (Product p : products) {
					JSONObject obj = new JSONObject(p);
					jArray.put(obj);
				}
				
				///// test
				//System.out.println(jArray.toString(4));
				//System.out.println("JSON Array length = " + jArray.length()); //$NON-NLS-1$
				//System.out.println();
				/////
				
				return jArray;
			}
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("resource")
	public JSONArray getInitialProducts() {

		this.clientSession.getTransactionUtil().startTransaction(Connection.TRANSACTION_READ_UNCOMMITTED);
		
		if (this.clientSession.getTransactionUtil().getConnection() != null) {
			ArrayList<Product> products = DataAccess.initialProducts();
			this.clientSession.getTransactionUtil().endTransaction();
			
			if (products != null) {
				JSONArray jArray = new JSONArray();
				for (Product p : products) {
					JSONObject obj = new JSONObject(p);
					jArray.put(obj);
				}
				
				///// test
				//System.out.println(jArray.toString(4));
				//System.out.println("JSON Array length = " + jArray.length()); //$NON-NLS-1$
				//System.out.println();
				/////
				
				return jArray;
			}
			return null;
		}
		return null;
	}
	
	
}
