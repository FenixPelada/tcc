package model.materia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MateriaDAO {
	private HashMap<Integer, Materia> materiaDB;
	private int id;

	public MateriaDAO() {
		materiaDB = new HashMap<>();
		id = 0;
	}

	public void adicionar(Materia materia) {
		id++;
		materia.setId(id);
		materiaDB.put(id, materia);

	}

	public void remover(Materia materia) {
		materiaDB.remove(materia.getId());
	}

	public void editar(Materia materia) {
		materiaDB.put(materia.getId(), materia);
	}

	public List<Materia> listar() {

		ArrayList<Materia> materias = new ArrayList<>();
		for (Materia materia : materiaDB.values()) {
			materias.add(materia);
		}
		return null;
	}

	public Materia findById(int id) {
		return materiaDB.get(id);
	}
}
