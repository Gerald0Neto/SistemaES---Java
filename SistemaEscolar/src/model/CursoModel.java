package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;

//import Classes.Aluno;
import Classes.Curso;
import util.Conexao;

public class CursoModel {
	
	public List<Curso> listarCursos() throws SQLException{
		String sql = "SELECT * FROM curso";
		Connection conn = Conexao.conectar();
		PreparedStatement stmt = conn.prepareStatement(sql);
		

		ResultSet rs = stmt.executeQuery();
		
		List<Curso> cursos = new ArrayList<Curso>();
		
		while(rs.next()) {
			
			Curso materias = new Curso();
			
			materias.setId(rs.getInt("id"));
			materias.setNome(rs.getString("nome"));
			materias.setDescricao(rs.getString("descricao"));
			materias.setCarga_horaria(rs.getFloat("carga_horaria"));
			materias.setValor(rs.getInt("valor"));
			materias.setAtivo(rs.getBoolean("ativo"));
			
			cursos.add(materias);
			
			
		}
		rs.close();
	    stmt.close();
	    conn.close();
		return cursos;
	}

}
