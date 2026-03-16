package model.materia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.ConexaoDB;

public class MateriaDAO {
    private Connection conn;
    private PreparedStatement pstm;
    private String sql;

    public MateriaDAO() {
        try {
            conn = ConexaoDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionar(Materia materia) {
        try {
            sql = "INSERT INTO tb_materia (nome) VALUES (?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, materia.getNome());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(Materia materia) {
        try {
            sql = "DELETE FROM tb_materia WHERE id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, materia.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editar(Materia materia) {
        try {
            sql = "UPDATE tb_materia SET nome = ? WHERE id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, materia.getNome());
            pstm.setInt(2, materia.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Materia> listar() {
        sql = "SELECT * FROM tb_materia ORDER BY id DESC";
        return executarSelect();
    }

    public List<Materia> executarSelect() {
        List<Materia> materias = new ArrayList<Materia>();
        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Materia materia = resultToObject(rs);
                materias.add(materia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materias;
    }

    public Materia findById(int id) {
        Materia materia = null;
        try {
            sql = "SELECT * FROM tb_materia WHERE id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            materia = resultToObject(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Materia resultToObject(ResultSet rs) throws SQLException {
        Materia materia = new Materia();
        materia.setId(rs.getInt("id"));
        materia.setNome(rs.getString("nome"));
        return materia;
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