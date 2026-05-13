package edu.br.crm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {

    private static final String URL= "jdbc:mysql://localhost:3306/crm?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "[DIGITE O USUÁRIO AQUI]";
    private static final String PASS = "[DIGITE A SENHA AQUI]";

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
