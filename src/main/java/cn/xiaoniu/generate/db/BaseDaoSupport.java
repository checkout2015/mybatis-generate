package cn.xiaoniu.generate.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xiaoniu.generate.config.ConfigFactory;
import cn.xiaoniu.generate.config.DbConfig;

public class BaseDaoSupport {
	protected static final Logger log = LoggerFactory.getLogger(BaseDaoSupport.class);

	public BaseDaoSupport() {

	}

	public Connection getConnection() {
		DbConfig dbConfigVo = ConfigFactory.config.getDb();
		try {
			String driveName = null;
			if (dbConfigVo.getUrl().contains("oracle")) {
				driveName = "oracle.jdbc.driver.OracleDriver";
			} else if (dbConfigVo.getUrl().contains("mysql")) {
				driveName = "com.mysql.jdbc.Driver";
			}
			Class.forName(driveName);
			return DriverManager.getConnection(dbConfigVo.getUrl(), dbConfigVo.getUsername(), dbConfigVo.getPassword());
		} catch (SQLException | ClassNotFoundException e) {
			log.error("", e);
		}
		return null;
	}

	public <T> List<T> list(String sql, ResultHandler<T> handler) {
		Connection con = getConnection();
		if (con == null) {
			return null;
		}
		List<T> list = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			list = new ArrayList<T>();
			while (rs.next()) {
				list.add(handler.doInvoke(rs));
			}
		} catch (SQLException e) {
			log.error("", e);
		} finally {
			close(con, ps, rs);
		}
		return list;
	}

	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			log.error("", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				log.error("", e);
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
					log.error("", e);
				}
			}
		}
	}

}
