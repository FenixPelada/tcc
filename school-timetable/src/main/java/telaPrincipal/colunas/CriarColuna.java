package telaPrincipal.colunas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CriarColuna extends JPanel {
	private JLabel titulo;
	private JButton botaoNovo;

	public CriarColuna(String titulo) {
			
		    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		    setBorder(new LineBorder(Color.BLACK));
		    setPreferredSize(new Dimension(200, 500));
		    
		    add(Box.createVerticalStrut(15));
		    
		    JLabel labelTitulo = new JLabel(titulo);
		    labelTitulo.setSize(50, 50);
		    //labelTitulo.setFont();
		    labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		    add(labelTitulo);
		    
		    add(Box.createVerticalStrut(20));	    

		    
		    customizar();
		}

	public void customizar() {

	}

}
