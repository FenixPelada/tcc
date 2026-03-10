package telaPrincipal.colunas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import model.professor.Professor;
import model.professor.ProfessorDAO;

public class ColunaProfessor  extends CriarColuna{
	
	private Professor professor;
	private ProfessorDAO professorDAO;
	
	public ColunaProfessor(String titulo) {
		super(titulo);
		
		JButton botaoAdicionar = new JButton("Novo curso");
		botaoAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
		JScrollPane lista = new JScrollPane();
		lista.setBackground(Color.black);
		
		
		lista.setPreferredSize(new Dimension (190, 400));
		
		botaoAdicionar.addActionListener(e -> {
			String nomeProfessor = JOptionPane.showInputDialog(this, "Digite o nome do professor:");
			adicionar(nomeProfessor.trim());
		});
		
		//add(Box.createVerticalStrut(5));
		add(botaoAdicionar);
		add(Box.createVerticalStrut(10));
		add(lista);
	}
	
	public void adicionar(String nome) {
		professor.setNome(nome);
		professorDAO.adicionar(professor);
	}
}
