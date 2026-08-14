package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//import javax.servlet.http.HttpServletRequest;

//import javax.servlet.http.HttpServletRequest;

//import javax.servlet.http.HttpServletRequest;

//import Classes.Aluno;
import Classes.Curso;
import util.Conexao;

public class CursoModel {
	
	public List<Curso> pesquisarCurso(
	        String nome,
	        Boolean status) throws SQLException {

	    List<Curso> cursos = new ArrayList<>();

	    String sql;

	    if (!nome.isEmpty() && status == null) {

	        sql = "SELECT * FROM curso " +
	              "WHERE nome LIKE ? " +
	              "ORDER BY id DESC";

	    } else if (nome.isEmpty() && status != null) {

	        sql = "SELECT * FROM curso " +
	              "WHERE ativo = ? " +
	              "ORDER BY id DESC";

	    } else {

	        sql = "SELECT * FROM curso " +
	              "WHERE nome LIKE ? AND ativo = ? " +
	              "ORDER BY id DESC";
	    }

	    Connection conn = Conexao.conectar();
	    PreparedStatement stmt = conn.prepareStatement(sql);

	    if (!nome.isEmpty() && status == null) {

	        stmt.setString(1, "%" + nome + "%");

	    } else if (nome.isEmpty() && status != null) {

	        stmt.setBoolean(1, status);

	    } else {

	        stmt.setString(1, "%" + nome + "%");
	        stmt.setBoolean(2, status);
	    }

	    ResultSet rs = stmt.executeQuery();

	    while (rs.next()) {

	        Curso curso = new Curso();

	        curso.setId(rs.getInt("id"));
	        curso.setNome(rs.getString("nome"));
	        curso.setDescricao(rs.getString("descricao"));
	        curso.setCarga_horaria(rs.getFloat("carga_horaria"));
	        curso.setValor(rs.getInt("valor"));
	        curso.setAtivo(rs.getBoolean("ativo"));

	        cursos.add(curso);
	    }

	    rs.close();
	    stmt.close();
	    conn.close();

	    return cursos;
	}
	
	public boolean updateCurso( 
			int id,
			String nome,
            String descricao,
            String cargaHoraria,
            String valor) throws SQLException{
	
			String sql = "UPDATE curso SET nome = ? , descricao = ? , carga_horaria = ? , valor = ? WHERE id = ?";
			Connection conn = Conexao.conectar();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, nome);
			stmt.setString(2, descricao);
			stmt.setString(3, cargaHoraria);
			stmt.setString(4, valor);
			stmt.setInt(5, id);
			
			int linhas = stmt.executeUpdate();
			
			stmt.close();
		    conn.close();
		
		    return linhas > 0;
		
		}
	
	public boolean deleteCurso(int id) throws SQLException {
		String sql = "DELETE FROM curso WHERE id = ?";
		Connection conn = Conexao.conectar();
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, id);
		int linhas = stmt.executeUpdate();
		
		stmt.close();
	    conn.close();
	
	    return linhas > 0;
		
	}
	
	public boolean alteraStatusCurso(int id, boolean status) throws SQLException {
		
		String sql = "UPDATE curso SET ativo = ? WHERE id = ?";
		Connection conn = Conexao.conectar();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setBoolean(1, status);
		stmt.setInt(2, id);
		int linhas = stmt.executeUpdate();
		
		stmt.close();
	    conn.close();
	
	    return linhas > 0;
	}
	
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
	
	public boolean insertCurso(
            String nome,
            String descricao,
            String cargaHoraria,
            String valor) throws SQLException {

        String sql =
            "INSERT INTO curso " +
            "(nome, descricao, carga_horaria, valor) " +
            "VALUES (?, ?, ?, ?)";

        Connection conn = Conexao.conectar();

        PreparedStatement stmt =
            conn.prepareStatement(sql);

        stmt.setString(1, nome);
        stmt.setString(2, descricao);
        stmt.setString(3, cargaHoraria);
        stmt.setString(4, valor);

        int linhas = stmt.executeUpdate();

        stmt.close();
        conn.close();

        return linhas > 0;
    }

}
