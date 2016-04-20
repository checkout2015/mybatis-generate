package cn.xiaoniu.generate.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler<T> {
	
	public T doInvoke(ResultSet rs) throws SQLException ;
}
