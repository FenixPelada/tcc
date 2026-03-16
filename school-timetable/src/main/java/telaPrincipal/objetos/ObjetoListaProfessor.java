package telaPrincipal.objetos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.professor.Professor;
import model.professor.ProfessorDAO;
import telaPrincipal.colunas.ColunaProfessor;

public class ObjetoListaProfessor extends JPanel{
	JButton botaoExcluir;
    JButton botaoEditar;
    JPanel painelBotoes;
    ProfessorDAO professorDAO;
    
    public ObjetoListaProfessor(Professor professor, ColunaProfessor colunaProfessor) {
    	
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
        	professorDAO.remover(professor);
        	colunaProfessor.atualizarLista();
        });
        botaoEditar.addActionListener(e -> {
        	String nomeProfessor = null;
            
            while (nomeProfessor == null) {
            	nomeProfessor = JOptionPane.showInputDialog(this, "Digite o novo nome do professor:");
                if (nomeProfessor == null) {
                    break;
                } else if (nomeProfessor.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "O nome não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
                    nomeProfessor = null;
                }
            }
            
            professor.setNome(nomeProfessor);
            professorDAO.editar(professor);
            colunaProfessor.atualizarLista();
        });
    }
}
