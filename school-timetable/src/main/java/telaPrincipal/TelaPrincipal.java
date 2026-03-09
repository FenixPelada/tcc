package telaPrincipal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TelaPrincipal extends JFrame{
	
	public TelaPrincipal (){
		setTitle("Tela principal");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel painelPrincipal = new JPanel();
		painelPrincipal.add(criarColuna("Curso"));
		painelPrincipal.add(criarColuna("Prof"));
		painelPrincipal.add(criarColuna("Sala"));
		painelPrincipal.add(criarColuna("Matéria"));
		
		this.add(painelPrincipal);
		painelPrincipal.setLayout(new GridLayout(1, 4, 5, 0));		

	}
	
	private JPanel criarColuna (String titulo) {
	    
	    JPanel painel = new JPanel ();
	    painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
	    painel.setBorder(new LineBorder(Color.BLACK));
	    painel.setPreferredSize(new Dimension(200, 500));
	    
	    painel.add(Box.createVerticalStrut(15));
	    
	    JLabel labelTitulo = new JLabel(titulo);
	    labelTitulo.setSize(50, 50);
	    //labelTitulo.setFont();
	    labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
	    painel.add(labelTitulo);
	    
	    painel.add(Box.createVerticalStrut(20));
	    
	    JButton btnNovo = new JButton("+ NOVO");
	    btnNovo.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    btnNovo.setAlignmentX(Component.CENTER_ALIGNMENT);
	    painel.add(btnNovo);
	    
	    
	    
	    return painel;
	}
}
