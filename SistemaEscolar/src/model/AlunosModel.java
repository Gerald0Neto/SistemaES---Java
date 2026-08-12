
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Classes.Aluno;
import util.Conexao;

public class AlunosModel {
	
	//Atualiza os dados pessoais do Aluno
	public boolean updateAluno( 
				int id,
				String nome,
	            String cpf,
	            String dataNasc,
	            String telefone,
	            String email,
	            HttpServletRequest request) throws SQLException{
		
		String sql = "UPDATE aluno SET nome = ? , cpf = ? , data_nascimento = ? , telefone = ? , email = ? WHERE id = ?";
		Connection conn = Conexao.conectar();
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, nome);
		stmt.setString(2, cpf);
		stmt.setString(3, dataNasc);
		stmt.setString(4, telefone);
		stmt.setString(5, email);
		stmt.setInt(6, id);
		
		int linhas = stmt.executeUpdate();
		
		stmt.close();
        conn.close();

        return linhas > 0;

	}
	
	public List<Aluno> pesquisarAluno(
	        String nome,
	        String cpf,
	        HttpServletRequest request) throws SQLException {

	    List<Aluno> alunos = new ArrayList<>();

	    String sql;

	    if (!nome.isEmpty() && cpf.isEmpty()) {

	        sql = "SELECT * FROM aluno " +
	              "WHERE nome LIKE ? " +
	              "ORDER BY id DESC";

	    } else if (nome.isEmpty() && !cpf.isEmpty()) {

	        sql = "SELECT * FROM aluno " +
	              "WHERE cpf LIKE ? " +
	              "ORDER BY id DESC";

	    } else {

	        sql = "SELECT * FROM aluno " +
	              "WHERE nome LIKE ? AND cpf LIKE ? " +
	              "ORDER BY id DESC";
	    }

	    Connection conn = Conexao.conectar();
	    PreparedStatement stmt = conn.prepareStatement(sql);

	    if (!nome.isEmpty() && cpf.isEmpty()) {

	        stmt.setString(1, "%" + nome + "%");

	    } else if (nome.isEmpty() && !cpf.isEmpty()) {

	        stmt.setString(1, "%" + cpf + "%");

	    } else {

	        stmt.setString(1, "%" + nome + "%");
	        stmt.setString(2, "%" + cpf + "%");
	    }

	    ResultSet rs = stmt.executeQuery();

	    while (rs.next()) {

	        Aluno aluno = new Aluno();

	        aluno.setId(rs.getInt("id"));
	        aluno.setNome(rs.getString("nome"));
	        aluno.setCpf(rs.getString("cpf"));
	        aluno.setEmail(rs.getString("email"));
	        aluno.setTelefone(rs.getString("telefone"));
	        aluno.setDataNascimento(rs.getString("data_nascimento"));
	        aluno.setStatus(rs.getInt("ativo"));

	        alunos.add(aluno);
	    }

	    rs.close();
	    stmt.close();
	    conn.close();

	    return alunos;
	}

	
	public boolean deleteAluno(int id) throws SQLException {
		String sql = "DELETE FROM aluno WHERE id = ?";
		Connection conn = Conexao.conectar();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		int linhas = stmt.executeUpdate();
		
		stmt.close();
        conn.close();

        return linhas > 0;
	}
	
	public boolean alteraStatusAluno(int id, int ativo) throws SQLException {
		
		String sql = "UPDATE aluno SET ativo = ? WHERE id = ?";
		Connection conn = Conexao.conectar();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, ativo);
		stmt.setInt(2, id);
		int linhas = stmt.executeUpdate();
		
		stmt.close();
        conn.close();

        return linhas > 0;
		
	}

    public boolean insertAlunos(
            String nome,
            String cpf,
            String dataNasc,
            String telefone,
            String email,
            HttpServletRequest request) throws SQLException {

        String sql =
            "INSERT INTO aluno " +
            "(nome, cpf, email, telefone, data_nascimento) " +
            "VALUES (?, ?, ?, ?, ?)";

        Connection conn = Conexao.conectar();

        PreparedStatement stmt =
            conn.prepareStatement(sql);

        stmt.setString(1, nome);
        stmt.setString(2, cpf);
        stmt.setString(3, email);
        stmt.setString(4, telefone);
        stmt.setString(5, dataNasc);

        int linhas = stmt.executeUpdate();

        stmt.close();
        conn.close();

        return linhas > 0;
    }

    public List<Aluno> listarAlunos() throws SQLException {

        List<Aluno> alunos =   new ArrayList<>();

        String sql = "SELECT * FROM aluno ORDER BY id DESC";

        Connection conn =  Conexao.conectar();

        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Aluno aluno =  new Aluno();

            aluno.setId( rs.getInt("id") );

            aluno.setNome(  rs.getString("nome") );

            aluno.setCpf( rs.getString("cpf")  );

            aluno.setEmail( rs.getString("email") );

            aluno.setTelefone( rs.getString("telefone") );

            aluno.setDataNascimento(  rs.getString("data_nascimento") );
            
            aluno.setStatus( rs.getInt("ativo"));

            alunos.add(aluno);
        }

        rs.close();
        stmt.close();
        conn.close();

        return alunos;
    }
}

