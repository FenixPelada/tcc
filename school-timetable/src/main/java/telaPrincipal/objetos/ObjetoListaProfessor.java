package telaPrincipal.objetos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.professor.Professor;
import model.professor.ProfessorDAO;
import telaPrincipal.colunas.ColunaCurso;

public class ObjetoListaProfessor extends JPanel{
	JButton botaoExcluir;
    JButton botaoEditar;
    JPanel painelBotoes;
    ProfessorDAO professorDAO;
    
    public ObjetoListaProfessor(Professor professor, ColunaCurso colunaCurso) {
    	
        setLayout(new BorderLayout(10, 0));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        add(new JLabel(professor.getNome()), BorderLayout.WEST);

        painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

        botaoEditar  = new JButton("Editar");
        botaoExcluir = new JButton("Excluir");

        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoExcluir);

        add(painelBotoes, BorderLayout.EAST);

        botaoExcluir.addActionListener(e -> {
        	
        });
        botaoEditar.addActionListener(e -> { });
    }
}
