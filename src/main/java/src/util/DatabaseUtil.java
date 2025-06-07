package src.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseUtil {

    static {
        HikariConfig dbConfig = new HikariConfig();
        dbConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dbConfig.setUsername(Config.get("db.username"));
        dbConfig.setPassword(Config.get("db.password"));
        dbConfig.setJdbcUrl(Config.get("db.jdbc.url"));
        dbConfig.setMaximumPoolSize(Integer.valueOf(Config.get("db.maxpool")));
        dbConfig.setMinimumIdle(Integer.valueOf(Config.get("db.idle")));
        dbConfig.setIdleTimeout(60_000);
        dbConfig.setMaxLifetime(60 * 60 * 1000);

        dbPool = new HikariDataSource(dbConfig);
    }

    private static HikariDataSource dbPool;

    public static HikariDataSource getDataSource() {
        return dbPool;
    }
}
