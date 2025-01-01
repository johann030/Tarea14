package pool;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class MyDataSource {

	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource dataSource;

	// Patron de diseño Singleton
	// El "constructor" de esta clase
	static {
		config.setJdbcUrl("jdbc:mysql://localhost/alumno?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&serverTime=Europe/Madrid");
		config.setUsername("johann");
		config.setPassword("manager");
		config.addDataSourceProperty("maximumPoolSize", 1);

		// propiedades propuestas en la web de HikariCP para MySQL
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		dataSource = new HikariDataSource(config);
	}

	private MyDataSource() {
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}