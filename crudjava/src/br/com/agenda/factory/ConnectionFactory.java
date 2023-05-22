package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	//nome do usuário do mysql
	private static final String USERNAME = "root";
	
	//senha do banco
	private static final String PASSWORD = "";
	
	//caminho do banco de dados, porta, nome do banco
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
	
	//conexão
	public static Connection createConnectionToMySQL() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
	
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}

	public static void main(String[] args) throws Exception {
		
		//recuperar a conexão com o banco
		Connection con = createConnectionToMySQL();
		
		//Testar se a conexão é nula
		if(con!=null) {
			System.out.println("Conexão obtida");
			con.close();
		}
	}
}
