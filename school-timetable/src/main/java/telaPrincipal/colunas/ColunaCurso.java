package telaPrincipal.colunas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import model.curso.Curso;
import model.curso.CursoDAO;

public class ColunaCurso  extends CriarColuna{
	
	private Curso curso;
	private CursoDAO cursoDAO;
	
	public ColunaCurso(String titulo) {
		super(titulo);
		
		JButton botaoAdicionar = new JButton("Novo curso");
		botaoAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
		JScrollPane lista = new JScrollPane();
		lista.setBackground(Color.black);
		
		
		lista.setPreferredSize(new Dimension (190, 400));
		
		botaoAdicionar.addActionListener(e -> {
			String nomeCurso = JOptionPane.showInputDialog(this, "Digite o nome do curso:");
			adicionar(nomeCurso.trim());
		});
		
		//add(Box.createVerticalStrut(5));
		add(botaoAdicionar);
		add(Box.createVerticalStrut(10));
		add(lista);
	}
	
	public void adicionar(String nome) {
		curso.setNome(nome);
		cursoDAO.adicionar(curso);
	}
	
}