package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private static DataSource dataSource;

    static {
        //        final String url = "jdbc:mysql://localhost:3306/employee";
        //        final String user = "root";
        //        final String password = "hieu112004";
        config.setJdbcUrl("jdbc:mysql://localhost:3306/exercise");
        config.setUsername("root");
        config.setPassword("hieu112004");
        config.setAutoCommit(false);
        config.setMaximumPoolSize(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    private DataSource() {
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static DataSource getInstance() {
        if (Objects.isNull(dataSource)) {
            dataSource = new DataSource();
        }
        return dataSource;
    }

}


