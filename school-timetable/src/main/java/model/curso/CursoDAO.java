package model.curso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CursoDAO {

	private HashMap<Integer, Curso> cursoDB;
	private int id;

	public CursoDAO() {
		cursoDB = new HashMap<>();
		id = 0;
	}

	public void adicionar(Curso curso) {
		id++;
		curso.setId(id);
		cursoDB.put(id, curso);

	}

	public void remover(Curso curso) {
		cursoDB.remove(curso.getId());
	}

	public void editar(Curso curso) {
		cursoDB.put(curso.getId(), curso);
	}

	public List<Curso> listar() {

		ArrayList<Curso> cursos = new ArrayList<>();
		for (Curso curso : cursoDB.values()) {
			cursos.add(curso);
		}
		return null;
	}

	public Curso findById(int id) {
		return cursoDB.get(id);
	}

}
