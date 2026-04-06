package model.professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.ConexaoDB;

public class ProfessorDAO {
    private Connection conn;
    private PreparedStatement pstm;
    private String sql;

    public ProfessorDAO() {
        try {
            conn = ConexaoDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionar(Professor professor) {
        try {
            sql = "INSERT INTO tb_professor (nome) VALUES (?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, professor.getNome());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(Professor professor) {
        try {
            sql = "DELETE FROM tb_professor WHERE id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, professor.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editar(Professor professor) {
        try {
            sql = "UPDATE tb_professor SET nome = ? WHERE id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, professor.getNome());
            pstm.setInt(2, professor.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Professor> listar() {
        sql = "SELECT * FROM tb_professor ORDER BY id DESC";
        return executarSelect();
    }

    public List<Professor> executarSelect() {
        List<Professor> professores = new ArrayList<Professor>();
        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Professor professor = resultToObject(rs);
                professores.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professores;
    }

    public Professor findById(int id) {
        Professor professor = null;
        try {
            sql = "SELECT * FROM tb_professor WHERE id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            professor = resultToObject(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Professor resultToObject(ResultSet rs) throws SQLException {
        Professor professor = new Professor();
        professor.setId(rs.getInt("id"));
        professor.setNome(rs.getString("nome"));
        return professor;
    }
    
    public void salvarDisponibilidade(Professor professor) {
        try {
            sql = "DELETE FROM tb_professor_disponibilidade WHERE id_professor = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, professor.getId());
            pstm.executeUpdate();

            sql = "INSERT INTO tb_professor_disponibilidade (id_professor, dia) VALUES (?, ?)";
            pstm = conn.prepareStatement(sql);
            for (DiasIndisponiveis dia : professor.getDiasIndisponiveis()) {
                pstm.setInt(1, professor.getId());
                pstm.setString(2, dia.name());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DiasIndisponiveis> carregarDisponibilidade(Professor professor) {
        List<DiasIndisponiveis> dias = new ArrayList<>();
        try {
            sql = "SELECT dia FROM tb_professor_disponibilidade WHERE id_professor = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, professor.getId());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                dias.add(DiasIndisponiveis.valueOf(rs.getString("dia")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dias;
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