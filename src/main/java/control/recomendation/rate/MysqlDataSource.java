package control.recomendation.rate;

import javax.sql.DataSource;

import org.apache.mahout.cf.taste.common.TasteException;

public class MysqlDataSource {

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
  
}
