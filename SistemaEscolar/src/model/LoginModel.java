package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.Conexao;

public class LoginModel {
	
	
	public boolean verificarUsuario(String email, String senha, HttpServletRequest request) throws SQLException {
		
		String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
		Connection conn = Conexao.conectar();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);
		stmt.setString(2, senha);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			String emaill = rs.getString("email");
			
			HttpSession session = request.getSession();
			session.setAttribute("idusuario", id);
			session.setAttribute("nome", nome);
			session.setAttribute("email", emaill);
			
			stmt.close(); 
			conn.close();
			return true;
			
		}
		
		stmt.close(); 
		conn.close();
		
		return false;
		
	}

}
