package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import business.DBConProperties;

/**
 * @author Diogo Antão
 *
 * 04-2022
 */

public class TransactionUtil {
	
	private static final int maxRetryCount = 5;
	private int retryCount;
	private boolean flagError = false;
	private Connection conn;
		
	public Connection getConnection() {
		return this.conn;
	}
	
	public boolean getFlagError() {
		return this.flagError;
	}
	
	public void setFlagError(boolean flagError) {
		this.flagError = flagError;
	}
	
	public void startTransaction(int isolationLevel) {
		this.conn = null;
		this.retryCount = maxRetryCount;
		
		do {
			try {
				this.conn = DriverManager.getConnection(
						DBConProperties.URL.getString(),
						DBConProperties.USER.getString(),
						DBConProperties.PASSWORD.getString());
				
				this.conn.setTransactionIsolation(isolationLevel);
				
				this.conn.setAutoCommit(false);
				
				this.retryCount = 0;
			}
			catch (SQLException e) {
				
				//
	            // The two SQL states that are 'retry-able' are 08S01
	            // for a communications error, and 40001 for deadlock.
	            //
	            // Only retry if the error was due to a stale connection,
	            // communications problem or deadlock
	            //
				
				String sqlState = e.getSQLState();
				
				if ("08S01".equals(sqlState) || "40001".equals(sqlState)) { //$NON-NLS-1$ //$NON-NLS-2$
					this.retryCount -= 1;
				}
				else {
					this.retryCount = 0;
				}
			}
		}
		while (this.retryCount > 0);
	}
	
	public void endTransaction() {		
		this.retryCount = maxRetryCount;
		boolean transactionCompleted = false;

		if (!this.flagError && this.conn != null) {
			do {
				try {
					this.retryCount = 0;
					this.conn.commit();
					this.conn.close();
					this.conn = null;
					transactionCompleted = true;
				} catch (SQLException e) {
					
					//
		            // The two SQL states that are 'retry-able' are 08S01
		            // for a communications error, and 40001 for deadlock.
		            //
		            // Only retry if the error was due to a stale connection,
		            // communications problem or deadlock
		            //
					
					String sqlState = e.getSQLState();
					
					if ("08S01".equals(sqlState) || "40001".equals(sqlState)) { //$NON-NLS-1$ //$NON-NLS-2$
						this.retryCount -= 1;
					}
					else {
						this.retryCount = 0;
					}
				} finally {
					if (this.conn != null) {
						try {
							try {
								this.conn.rollback();
							}
							finally {
								this.conn.close();
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
			while (!transactionCompleted && this.retryCount > 0);
		}
		
		//when an error occurred outside this method
		else if (this.conn != null) {
			try {
				this.conn.rollback();
				this.conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
