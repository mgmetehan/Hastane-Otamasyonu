package Helper;

import java.sql.*;

public class DBConnection {
	Connection c = null;

	public DBConnection() {
	}

	public Connection connDb() {
		try {// Yıldızları değiştir
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:****/hospital?user=*****&password=********");
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

}
