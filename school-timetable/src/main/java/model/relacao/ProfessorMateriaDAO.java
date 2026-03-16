package model.relacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.ConexaoDB;
import model.materia.Materia;
import model.professor.Professor;

public class ProfessorMateriaDAO {
	
	private Connection conn;
	private PreparedStatement pstm;
	private String sql;
	
	public ProfessorMateriaDAO() {
		try {
			conn = ConexaoDB.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void vincular (Professor professor, Materia materia) {
		try {
			sql = "INSERT INTO tb_professor_materia (id_professor, id_materia) VALUES (?, ?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, professor.getId());
			pstm.setInt(2, materia.getId());
			pstm.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void desvincular(Professor professor, Materia materia) {
		try {
			sql = "DELETE FROM tb_professor_materia WHERE id_professor = ? AND id_materia = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, professor.getId());
			pstm.setInt(2, materia.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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