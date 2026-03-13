package model.sala;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SalaDAO {
	private HashMap<Integer, Sala> salaDB;
	private int id;

	public SalaDAO() {
		salaDB = new HashMap<>();
		id = 0;
	}

	public void adicionar(Sala sala) {
		id++;
		sala.setId(id);
		salaDB.put(id, sala);

	}

	public void remover(Sala sala) {
		salaDB.remove(sala.getId());
	}

	public void editar(Sala sala) {
		salaDB.put(sala.getId(), sala);
	}

	public List<Sala> listar() {

		ArrayList<Sala> salas = new ArrayList<>();
		for (Sala sala : salaDB.values()) {
			salas.add(sala);
		}
		return salas;
	}

	public Sala findById(int id) {
		return salaDB.get(id);
	}
}
