package model.sala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.ConexaoDB;

public class SalaDAO {
    private Connection conn;
    private PreparedStatement pstm;
    private String sql;

    public SalaDAO() {
        try {
            conn = ConexaoDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionar(Sala sala) {
        try {
            sql = "INSERT INTO tb_sala (numero) VALUES (?)";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, sala.getNumero());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(Sala sala) {
        try {
            sql = "DELETE FROM tb_sala WHERE id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, sala.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editar(Sala sala) {
        try {
            sql = "UPDATE tb_sala SET numero = ? WHERE id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, sala.getNumero());
            pstm.setInt(2, sala.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Sala> listar() {
        sql = "SELECT * FROM tb_sala ORDER BY id DESC";
        return executarSelect();
    }

    public List<Sala> executarSelect() {
        List<Sala> salas = new ArrayList<Sala>();
        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Sala sala = resultToObject(rs);
                salas.add(sala);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salas;
    }

    public Sala findById(int id) {
        Sala sala = null;
        try {
            sql = "SELECT * FROM tb_sala WHERE id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            sala = resultToObject(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Sala resultToObject(ResultSet rs) throws SQLException {
        Sala sala = new Sala();
        sala.setId(rs.getInt("id"));
        sala.setNumero(rs.getInt("numero"));
        return sala;
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