package control.recomendation.rate;

import javax.sql.DataSource;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.jdbc.AbstractJDBCComponent;

public class MysqlDataSource {

private String hostName;
private String user;
private String Password;
private String databaseName;
public void MySQLJDBCDataModel() throws TasteException {
  }
  
  public void MySQLJDBCDataModel(String dataSourceName) throws TasteException {
  }
  
  public void MySQLJDBCDataModel(DataSource dataSource) {
  }

  public void MySQLJDBCDataModel(DataSource dataSource,
                            String preferenceTable,
                            String userIDColumn,
                            String itemIDColumn,
                            String preferenceColumn,
                            String timestampColumn) {
  }
 
  protected int getFetchSize() {
	  
    return Integer.MIN_VALUE;
  }

  public  void setServerName(String serverName) 
	 {
	 		this.hostName = serverName;
	 }
	public  void setUser(String userID) 
	{
			this.user = userID;
	}
	public  void setPassword(String pass) 
	{
			this.Password = pass;
	}
	public  void setDatabaseName(String dbName) 
	{
			this.databaseName = dbName;
	}
  
}
