package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	 private static final String URL =
	            "jdbc:mysql://localhost:3306/sistemaescolar?useSSL=false&serverTimezone=UTC";
	 private static final String USUARIO = "root";
	 private static final String SENHA = "admin";

	 public static Connection conectar() throws SQLException { 
		 try { 
			 Class.forName("com.mysql.cj.jdbc.Driver"); 
		 } catch (ClassNotFoundException e) { 
			 throw new SQLException("MySQL Connector/J não foi encontrado!", e); 
		 } return DriverManager.getConnection(URL, USUARIO, SENHA); }
}
