package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.JSONObject;

import util.ClientSession;

/**
 * @author Diogo Antão
 *
 * 04-2022
 */

@SuppressWarnings({"nls", "resource"})
public class DataAccess {
	
	public static Long getLastClientId() {
		ClientSession clientSession = ClientSession.getSession();
		
		if (clientSession != null) {
			Connection conn = clientSession.getTransactionUtil().getConnection();
			
			if (conn != null) {
				String query = Bundles.Queries.getString("getLastClientId");
				Long lastClientId;
				
				try (Statement stmt = conn.createStatement()) {
					try (ResultSet rs = stmt.executeQuery(query)) {
						while(rs.next()) {
							lastClientId = Long.valueOf(rs.getLong(1));
							// returns 0 if there's no value
							return lastClientId;
						}
					} catch (SQLException e) {
						e.printStackTrace();
						clientSession.getTransactionUtil().setFlagError(true);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					clientSession.getTransactionUtil().setFlagError(true);
				}
				
				return null;
			}
			return null;
		}
		return null;
	}
	
	public static void insertSiteClient() {
		ClientSession clientSession = ClientSession.getSession();
		
		if (clientSession != null) {
			Connection conn = clientSession.getTransactionUtil().getConnection();
			
			if (conn != null) {
				String query = Bundles.Queries.getString("insertSiteClient");
				
				try (Statement stmt = conn.createStatement()) {
					try (PreparedStatement pstmt = conn.prepareStatement(query)) {
						pstmt.setLong(1, clientSession.getSiteId());
						pstmt.setLong(2, clientSession.getClientId());
						pstmt.setString(3, clientSession.getClientDs());					
						pstmt.execute();
					} catch (SQLException e) {
						e.printStackTrace();
						clientSession.getTransactionUtil().setFlagError(true);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					clientSession.getTransactionUtil().setFlagError(true);
				}
			}
		}
	}
	
	public static void insertSiteSession() {
		ClientSession clientSession = ClientSession.getSession();
		
		if (clientSession != null) {
			Connection conn = clientSession.getTransactionUtil().getConnection();
			
			if (conn != null) {
				String query = Bundles.Queries.getString("insertSiteSession");
				
				try (Statement stmt = conn.createStatement()) {
					try (PreparedStatement pstmt = conn.prepareStatement(query)) {
						pstmt.setLong(1, clientSession.getSiteId());
						pstmt.setString(2, clientSession.getSessionTk());
						pstmt.setLong(3, clientSession.getClientId());
						pstmt.setTimestamp(4, Timestamp.from(clientSession.getCreatedDt()));
						pstmt.setTimestamp(5, Timestamp.from(clientSession.getUpdatedDt()));
						pstmt.execute();
					} catch (SQLException e) {
						e.printStackTrace();
						clientSession.getTransactionUtil().setFlagError(true);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					clientSession.getTransactionUtil().setFlagError(true);
				}
			}
		}
	}
	
	public static void updateSiteSession() {
		ClientSession clientSession = ClientSession.getSession();
		
		if (clientSession != null) {
			Connection conn = clientSession.getTransactionUtil().getConnection();
			
			if (conn != null) {
				String query = Bundles.Queries.getString("updateSiteSession");
				
				try (Statement stmt = conn.createStatement()) {
					try (PreparedStatement pstmt = conn.prepareStatement(query)) {
						pstmt.setTimestamp(1, Timestamp.from(clientSession.getUpdatedDt()));
						pstmt.setLong(2, clientSession.getSiteId());
						pstmt.setString(3, clientSession.getSessionTk());				
						pstmt.execute();
					} catch (SQLException e) {
						e.printStackTrace();
						clientSession.getTransactionUtil().setFlagError(true);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					clientSession.getTransactionUtil().setFlagError(true);
				}
			}
		}
	}
	
	public static ArrayList<String> selectAllAvlSiteProductsDs() {
		ClientSession clientSession = ClientSession.getSession();
		
		if (clientSession != null) {
			Connection conn = clientSession.getTransactionUtil().getConnection();
			
			if (conn != null) {
				String query = Bundles.Queries.getString("selectAllAvlSiteProductsDs");
				ArrayList<String> products = new ArrayList<>();
				
				try (Statement stmt = conn.createStatement()){
					try (PreparedStatement pstmt = conn.prepareStatement(query)) {
						pstmt.setLong(1, clientSession.getSiteId());
						try (ResultSet rs = pstmt.executeQuery()) {
							while (rs.next()) {
								products.add(rs.getString(Bundles.ColumnLabels.getString("productDs")));
							}
						} catch (SQLException e) {
							e.printStackTrace();
							clientSession.getTransactionUtil().setFlagError(true);
						}
					} catch (SQLException e) {
						e.printStackTrace();
						clientSession.getTransactionUtil().setFlagError(true);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					clientSession.getTransactionUtil().setFlagError(true);
				}
		
				return products;
			}
			return null;
		}
		return null;
	}
	
	public static ArrayList<Product> selectAllAvlSiteProducts() {
		ClientSession clientSession = ClientSession.getSession();
		
		if (clientSession != null) {
			Connection conn = clientSession.getTransactionUtil().getConnection();
			
			if (conn != null) {
				String query = Bundles.Queries.getString("selectAllAvlSiteProducts");
				ArrayList<Product> products = new ArrayList<>();
				
				try (Statement stmt = conn.createStatement()){
					try (PreparedStatement pstmt = conn.prepareStatement(query)) {
						pstmt.setLong(1, clientSession.getSiteId());
						try (ResultSet rs = pstmt.executeQuery()) {
							while (rs.next()) {
								Product product = new Product(
										rs.getLong(Bundles.ColumnLabels.getString("productId")),
										rs.getString(Bundles.ColumnLabels.getString("productDs")),
										ProductStatus.valueOf(rs.getString(Bundles.ColumnLabels.getString("productSt"))),
										new JSONObject(rs.getString(Bundles.ColumnLabels.getString("productJd"))),
										rs.getString(Bundles.ColumnLabels.getString("productImageCd"))
										);				
								products.add(product);
							}
						} catch (SQLException e) {
							e.printStackTrace();
							clientSession.getTransactionUtil().setFlagError(true);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
							clientSession.getTransactionUtil().setFlagError(true);
						}
					} catch (SQLException e) {
						e.printStackTrace();
						clientSession.getTransactionUtil().setFlagError(true);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					clientSession.getTransactionUtil().setFlagError(true);
				}
		
				return products;
			}
			return null;
		}
		return null;
	}
	
	public static ArrayList<Product> initialProducts() {
		ClientSession clientSession = ClientSession.getSession();
		
		if (clientSession != null) {
			Connection conn = clientSession.getTransactionUtil().getConnection();
			
			if (conn != null) {
				String query = Bundles.Queries.getString("initialProducts");
				ArrayList<Product> products = new ArrayList<>();
				
				try (Statement stmt = conn.createStatement()){
					try (PreparedStatement pstmt = conn.prepareStatement(query)) {
						pstmt.setLong(1, clientSession.getSiteId());
						try (ResultSet rs = pstmt.executeQuery()) {
							while (rs.next()) {
								Product product = new Product(
										rs.getLong(Bundles.ColumnLabels.getString("productId")),
										rs.getString(Bundles.ColumnLabels.getString("productDs")),
										ProductStatus.valueOf(rs.getString(Bundles.ColumnLabels.getString("productSt"))),
										new JSONObject(rs.getString(Bundles.ColumnLabels.getString("productJd"))),
										rs.getString(Bundles.ColumnLabels.getString("productImageCd")),
										CurrencyCode.valueOf(rs.getString(Bundles.ColumnLabels.getString("currencyCd"))),
										rs.getTimestamp(Bundles.ColumnLabels.getString("startDt")).toInstant(),
										rs.getFloat(Bundles.ColumnLabels.getString("priceVl")),
										rs.getFloat(Bundles.ColumnLabels.getString("previousPriceVl")),
										PriceStatus.valueOf(rs.getString(Bundles.ColumnLabels.getString("priceSt"))),
										PriceStatus.valueOf(rs.getString(Bundles.ColumnLabels.getString("previousPriceSt")))
										);				
								products.add(product);
							}
						} catch (SQLException e) {
							e.printStackTrace();
							clientSession.getTransactionUtil().setFlagError(true);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
							clientSession.getTransactionUtil().setFlagError(true);
						}
					} catch (SQLException e) {
						e.printStackTrace();
						clientSession.getTransactionUtil().setFlagError(true);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					clientSession.getTransactionUtil().setFlagError(true);
				}
		
				return products;
			}
			return null;
		}
		return null;
	}	
	
	
}
