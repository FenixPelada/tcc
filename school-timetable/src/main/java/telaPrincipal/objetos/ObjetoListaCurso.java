package telaPrincipal.objetos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.curso.Curso;
import model.curso.CursoDAO;
import telaPrincipal.colunas.ColunaCurso;

public class ObjetoListaCurso extends JPanel {

    JButton botaoExcluir;
    JButton botaoEditar;
    JPanel painelBotoes;
    CursoDAO cursoDAO;
    
    public ObjetoListaCurso(Curso curso, ColunaCurso colunaCurso) {
    	
    	cursoDAO = new CursoDAO();
    	
        setLayout(new BorderLayout(10, 0));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        add(new JLabel(curso.getNome()), BorderLayout.WEST);

        painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

        botaoEditar  = new JButton("Editar");
        botaoExcluir = new JButton("Excluir");

        painelBotoes.add(botaoEditar);	
        painelBotoes.add(botaoExcluir);

        add(painelBotoes, BorderLayout.EAST);
        
        botaoExcluir.addActionListener(e -> {
        	cursoDAO.remover(curso);
        	colunaCurso.atualizarLista();
        });
        
        botaoEditar.addActionListener(e -> {
        	String nomeCurso = null;
            
            while (nomeCurso == null) {
                nomeCurso = JOptionPane.showInputDialog(this, "Digite o novo nome do curso:");
                if (nomeCurso == null) {
                    break;
                } else if (nomeCurso.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "O nome não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
                    nomeCurso = null;
                }
            }
            
            curso.setNome(nomeCurso);
            cursoDAO.editar(curso);
            colunaCurso.atualizarLista();
        	
        });
    }
}