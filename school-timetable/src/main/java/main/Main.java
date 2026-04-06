package main;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tela1.Tela1;
import tela2.Tela2;
import tela3.Tela3;

public class Main {
	
	public static void main(String[] args) {
		JFrame main = new JFrame("Projeto");
		
		CardLayout cardLayout = new CardLayout();
		JPanel container = new JPanel(cardLayout);
		
		Tela1 tela1 = new Tela1(cardLayout, container);
		Tela2 tela2 = new Tela2(cardLayout, container);
		Tela3 tela3 = new Tela3(cardLayout, container);
		
		container.add(tela1, "Tela 1");
		container.add(tela2, "Tela 2");
		container.add(tela3, "Tela 3");
		
		main.add(container);
		main.pack();
		main.setVisible(true);
	}
}