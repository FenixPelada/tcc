package model.professor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfessorDAO {

	private HashMap<Integer, Professor> professorDB;
	private int id;

	public ProfessorDAO() {
		professorDB = new HashMap<>();
		id = 0;
	}

	public void adicionar(Professor professor) {
		id++;
		professor.setId(id);
		professorDB.put(id, professor);

	}

	public void remover(Professor professor) {
		professorDB.remove(professor.getId());
	}

	public void editar(Professor professor) {
		professorDB.put(professor.getId(), professor);
	}

	public List<Professor> listar() {

		ArrayList<Professor> professors = new ArrayList<>();
		for (Professor professor : professorDB.values()) {
			professors.add(professor);
		}
		return null;
	}

	public Professor findById(int id) {
		return professorDB.get(id);
	}

}
