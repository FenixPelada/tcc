package model.relacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void vincular(Professor professor, Materia materia) {
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

    public List<Materia> listarMateriasPorProfessor(Professor professor) {
        List<Materia> materias = new ArrayList<>();
        try {
            sql = "SELECT m.id, m.nome FROM tb_materia m " +
                  "INNER JOIN tb_professor_materia pm ON m.id = pm.id_materia " +
                  "WHERE pm.id_professor = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, professor.getId());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setId(rs.getInt("id"));
                materia.setNome(rs.getString("nome"));
                materias.add(materia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materias;
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