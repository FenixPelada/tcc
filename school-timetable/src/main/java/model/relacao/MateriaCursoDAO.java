package model.relacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConexaoDB;
import model.curso.Curso;
import model.materia.Materia;

public class MateriaCursoDAO {

    private Connection conn;
    private PreparedStatement pstm;
    private String sql;

    public MateriaCursoDAO() {
        try {
            conn = ConexaoDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void vincular(Curso curso, Materia materia) {
        try {
            sql = "INSERT INTO tb_curso_materia (id_curso, id_materia) VALUES (?, ?)";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, curso.getId());
            pstm.setInt(2, materia.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void desvincular(Curso curso, Materia materia) {
        try {
            sql = "DELETE FROM tb_curso_materia WHERE id_curso = ? AND id_materia = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, curso.getId());
            pstm.setInt(2, materia.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Materia> listarMateriasPorCurso(Curso curso) {
        List<Materia> materias = new ArrayList<>();
        try {
            sql = "SELECT m.id, m.nome FROM tb_materia m " +
                  "INNER JOIN tb_curso_materia cm ON m.id = cm.id_materia " +
                  "WHERE cm.id_curso = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, curso.getId());
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