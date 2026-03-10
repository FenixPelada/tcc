package telaPrincipal.colunas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import model.sala.Sala;
import model.sala.SalaDAO;

public class ColunaSala extends CriarColuna{
	
	private Sala sala;
	private SalaDAO salaDAO;
	
	public ColunaSala(String titulo) {
		super(titulo);
		
		JButton botaoAdicionar = new JButton("Nova sala");
		botaoAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
		JScrollPane lista = new JScrollPane();
		lista.setBackground(Color.black);
		
		
		lista.setPreferredSize(new Dimension (190, 400));
		
		botaoAdicionar.addActionListener(e -> {
			String numeroSala = JOptionPane.showInputDialog(this, "Digite o número da sala:");
			int numeroSalaFormat = Integer.parseInt(numeroSala);
			adicionar(numeroSalaFormat);
		});
		
		//add(Box.createVerticalStrut(5));
		add(botaoAdicionar);
		add(Box.createVerticalStrut(10));
		add(lista);
	}
	
	public void adicionar(int numSala) {
		sala.setNumero(numSala);
		salaDAO.adicionar(sala);
	}

}
