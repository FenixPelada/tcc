package telaPrincipal.objetos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.sala.Sala;
import model.sala.SalaDAO;
import telaPrincipal.colunas.ColunaSala;

public class ObjetoListaSala extends JPanel {
	JButton botaoExcluir;
	JButton botaoEditar;
	JPanel painelBotoes;
	SalaDAO salaDAO;

	public ObjetoListaSala(Sala sala, ColunaSala colunaSala) {

		setLayout(new BorderLayout(10, 0));
		setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

		salaDAO = new SalaDAO();

		String numeroSala = Integer.toString(sala.getNumero());

		add(new JLabel(numeroSala), BorderLayout.WEST);

		painelBotoes = new JPanel();
		painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

		botaoEditar = new JButton("Editar");
		botaoExcluir = new JButton("Excluir");

		painelBotoes.add(botaoEditar);
		painelBotoes.add(botaoExcluir);

		add(painelBotoes, BorderLayout.EAST);

		botaoExcluir.addActionListener(e -> {
			salaDAO.remover(sala);
			colunaSala.atualizarLista();
		});

		botaoEditar.addActionListener(e -> {
			String numSala = null;

			while (numSala == null) {
				numSala = JOptionPane.showInputDialog(this, "Digite o número da sala:");

				try {
					int numeroSalaFormat = Integer.parseInt(numSala.trim());
					if (numSala == null) {
						break;
					} else if (numSala.trim().isEmpty()) {
						JOptionPane.showMessageDialog(this, "O nome não pode ser vazio!", "Erro",
								JOptionPane.ERROR_MESSAGE);
						numSala = null;
					}
				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(this, "Digite apenas números!", "Erro", JOptionPane.ERROR_MESSAGE);
					numSala = null;
				}
			}
			if (numSala != null) {
				sala.setNumero(Integer.parseInt(numSala));
				salaDAO.editar(sala);
				colunaSala.atualizarLista();
			}
		});
	}
}
