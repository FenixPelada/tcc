package model.professor;

import java.util.List;

import model.materia.Materia;

public class Professor {
	private int id;
	private String nome;
	private List<Materia> materia;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Materia> getMateria() {
		return materia;
	}
	public void setMateria(List<Materia> materia) {
		this.materia = materia;
	}
	
}
