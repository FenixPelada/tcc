package telaPrincipal.objetos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.materia.Materia;
import model.materia.MateriaDAO;
import telaPrincipal.colunas.ColunaMateria;

public class ObjetoListaMateria extends JPanel{
	
	JButton botaoExcluir;
	JButton botaoEditar;
	JPanel painelBotoes;
	MateriaDAO materiaDAO;
	
	public ObjetoListaMateria(Materia materia, ColunaMateria colunaMateria) {

		materiaDAO = new MateriaDAO();
		
		setLayout(new BorderLayout(10, 0));
		setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

		add(new JLabel(materia.getNome()), BorderLayout.WEST);

		painelBotoes = new JPanel();
		painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

		botaoEditar = new JButton("Editar");
		botaoExcluir = new JButton("Excluir");

		painelBotoes.add(botaoEditar);
		painelBotoes.add(botaoExcluir);

		add(painelBotoes, BorderLayout.EAST);

		botaoExcluir.addActionListener(e -> {
			materiaDAO.remover(materia);
        	colunaMateria.atualizarLista();
		});
		botaoEditar.addActionListener(e -> {
        	String nomeMateria = null;
            
            while (nomeMateria == null) {
            	nomeMateria = JOptionPane.showInputDialog(this, "Digite o novo nome da matéria:");
                if (nomeMateria == null) {
                    break;
                } else if (nomeMateria.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "O nome não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
                    nomeMateria = null;
                }
            }
            
            materia.setNome(nomeMateria);
            materiaDAO.editar(materia);
            colunaMateria.atualizarLista();
		});
	}
}
