package tela1;

import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tela1.colunas.ColunaCurso;
import tela1.colunas.ColunaMateria;
import tela1.colunas.ColunaProfessor;
import tela1.colunas.ColunaSala;

public class Tela1 extends JPanel {
	
	public Tela1 (CardLayout cardLayout, JPanel container){
		setSize(1000, 1000);
		
		
		add(new ColunaSala("Salas"));
		add(new ColunaProfessor("Professores"));
		add(new ColunaMateria("Materias"));
		add(new ColunaCurso("Cursos", cardLayout, container));
		
		//this.add(painelPrincipal);
		setLayout(new GridLayout(1, 4, 5, 0));		
	}
}
