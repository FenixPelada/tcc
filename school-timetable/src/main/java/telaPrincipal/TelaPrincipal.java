package telaPrincipal;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import telaPrincipal.colunas.ColunaCurso;
import telaPrincipal.colunas.ColunaMateria;
import telaPrincipal.colunas.ColunaProfessor;
import telaPrincipal.colunas.ColunaSala;

public class TelaPrincipal extends JFrame{
	
	public TelaPrincipal (){
		setTitle("Tela principal");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		add(new ColunaSala("Salas"));
		this.add(new ColunaProfessor("Professores"));
		this.add(new ColunaMateria("Materias"));
		this.add(new ColunaCurso("Cursos"));
		
		//this.add(painelPrincipal);
		setLayout(new GridLayout(1, 4, 5, 0));		

	}
	
	
}
