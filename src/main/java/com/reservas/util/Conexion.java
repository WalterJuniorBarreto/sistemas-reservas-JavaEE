package com.reservas.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

    private static final Logger logger = LoggerFactory.getLogger(Conexion.class);
    private static final HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();
            Properties properties = new Properties();

            try (java.io.InputStream input = Conexion.class.getClassLoader().getResourceAsStream("database.properties")) {
                if (input != null) {
                    properties.load(input);
                } else {
                    logger.warn("Archivo database.properties no encontrado. Se dependerá 100% de Variables de Entorno.");
                }
            }

            String dbUrl = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : properties.getProperty("db.url");
            String dbUser = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : properties.getProperty("db.user");
            String dbPass = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : properties.getProperty("db.password");
            String dbDriver = System.getenv("DB_DRIVER") != null ? System.getenv("DB_DRIVER") : properties.getProperty("db.driver", "org.postgresql.Driver");

            config.setJdbcUrl(dbUrl);
            config.setUsername(dbUser);
            config.setPassword(dbPass);
            config.setDriverClassName(dbDriver);

            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setConnectionTimeout(30000);

            dataSource = new HikariDataSource(config);
            logger.info("HikariCP inicializado correctamente. Pool de conexiones listo.");

        } catch (Exception e) {
            logger.error("ERROR CRÍTICO: Fallo al inicializar la base de datos.", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}