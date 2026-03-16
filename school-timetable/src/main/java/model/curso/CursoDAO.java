package model.curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConexaoDB;

public class CursoDAO {

	private Connection conn;
	private PreparedStatement pstm;
	private String sql;

	public CursoDAO() {
		try {
			conn = ConexaoDB.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void adicionar(Curso curso) {
		try {
			sql = "INSERT INTO tb_curso (nome) VALUES (?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, curso.getNome());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remover(Curso curso) {
		try {
			sql = "DELETE FROM tb_curso WHERE id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, curso.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editar(Curso curso) {
		try {
			sql = "UPDATE tb_curso SET nome = ? WHERE id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, curso.getNome());
			pstm.setInt(2, curso.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Curso> listar() {
		sql = "SELECT * FROM tb_curso ORDER BY id DESC";
		return executarSelect();
	}
	
	public List<Curso> executarSelect(){
		List <Curso> cursos = new ArrayList<Curso>();
		try {
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Curso curso = resultToObject(rs);
				cursos.add(curso);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return cursos;
	}

	public Curso findById(int id) {
		Curso curso = null;
		try {
			sql = "SELECT * FROM tb_curso WHERE id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			rs.next();
			curso = resultToObject(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Curso resultToObject(ResultSet rs) throws SQLException{
		Curso curso = new Curso();
		curso.setId(rs.getInt("id"));
		curso.setNome(rs.getString("nome"));
		return curso;
	}
	
	public void fechar() {
		try {
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}