package telaPrincipal.colunas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import model.materia.Materia;
import model.materia.MateriaDAO;

public class ColunaMateria extends CriarColuna{
	
	private Materia materia;
	private MateriaDAO materiaDAO;
	
	public ColunaMateria(String titulo) {
		super(titulo);
		
		JButton botaoAdicionar = new JButton("Nova materia");
		botaoAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
		JScrollPane lista = new JScrollPane();
		lista.setBackground(Color.black);
		
		
		lista.setPreferredSize(new Dimension (190, 400));
		
		botaoAdicionar.addActionListener(e -> {
			String nomeMateria = JOptionPane.showInputDialog(this, "Digite o nome da matéria:");
			adicionar(nomeMateria.trim());
		});
		
		//add(Box.createVerticalStrut(5));
		add(botaoAdicionar);
		add(Box.createVerticalStrut(10));
		add(lista);
	}
	
	public void adicionar(String nome) {
		materia.setNome(nome);
		materiaDAO.adicionar(materia);
	}
}
