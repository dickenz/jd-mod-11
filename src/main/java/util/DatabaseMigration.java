package util;


import org.flywaydb.core.Flyway;

public class DatabaseMigration {

    public static void main(String[] args) {
        migrateDatabase();
    }

    private static void migrateDatabase() {
        Flyway flyway = Flyway.configure().dataSource(
                System.getProperty("hibernate.connection.url"),
                System.getProperty("hibernate.connection.username"),
                System.getProperty("hibernate.connection.password")
        ).load();
        flyway.migrate();
    }
}
