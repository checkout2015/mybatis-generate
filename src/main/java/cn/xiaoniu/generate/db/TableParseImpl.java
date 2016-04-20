package cn.xiaoniu.generate.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.xiaoniu.generate.config.ConfigFactory;
import cn.xiaoniu.generate.config.DbConfig;
import cn.xiaoniu.generate.model.Column;
import cn.xiaoniu.generate.model.Table;
import cn.xiaoniu.generate.utils.JavaTypeResolverUtils;

public class TableParseImpl extends BaseDaoSupport  implements TableParse{
	
	@SuppressWarnings("resource")
	public Table getTable(String tableName){
		Table table = new Table();
		table.setName(tableName);
		Connection con = null;
		ResultSet rs = null;
		List<Column> columns = null;
		List<String> primaryKeys = null;
		//List<Column> primaryKeysList = new ArrayList<Column>();
		try {
			 con = this.getConnection();
			 columns = new ArrayList<Column>();
			 primaryKeys = new ArrayList<String>();
			 
			DatabaseMetaData databaseMetaData = this.getConnection().getMetaData();
			DbConfig db = ConfigFactory.config.getDb();
			//System.out.println(databaseMetaData.getDatabaseProductName());
			rs = databaseMetaData.getPrimaryKeys(db.getCatalog(),
					db.getSchema(),tableName);
			while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                primaryKeys.add(columnName);
            }
			rs = databaseMetaData.getColumns(db.getCatalog(),
					db.getSchema(),tableName, null);
			/*int count = rs.getMetaData().getColumnCount();
			for(int i=1;i<=count;i++){
				System.out.println(rs.getMetaData().getColumnName(i));
			}*/
			while (rs.next()) {
		            String name = rs.getString("COLUMN_NAME");
					int type = rs.getInt("DATA_TYPE");
					String comment = rs.getString("REMARKS");
					int scale = rs.getInt("DECIMAL_DIGITS");
					int length = rs.getInt("COLUMN_SIZE");
					boolean nullable = rs.getInt("NULLABLE") == DatabaseMetaData.columnNullable;
					String typeName = rs.getString("TYPE_NAME");
					boolean autoIncrement = "YES".equalsIgnoreCase(rs.getString("IS_AUTOINCREMENT"));
					
					Column cd = new Column();
					cd.setAutoIncrement(autoIncrement);
					cd.setColumnName(name);
					cd.setType(JavaTypeResolverUtils.calculateJavaType(type,typeName, scale, length));
					cd.setColumnComment(comment);
					cd.setScale(scale);
					cd.setLength(length);
					cd.setNullable(nullable);
					cd.setJdbcType(cd.getType().getJdbcTypeName());
					for(String key:primaryKeys){
						if(key.equals(name)){
							cd.setPrimaryKey(true);
							table.setPrimaryKey(cd);
							//primaryKeysList.add(cd);
							break;
						}
					}
					columns.add(cd);
		        }
		} catch (SQLException e) {
			log.error("", e);
		}finally{
			this.close(con, null, rs);
		}
		table.setColumns(columns);
		//table.setPrimaryKeys(primaryKeysList);
		return table;
	}
	
}
