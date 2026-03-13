package telaPrincipal.colunas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.sala.Sala;
import model.sala.SalaDAO;
import telaPrincipal.objetos.ObjetoListaSala;

public class ColunaSala extends CriarColuna {

    private SalaDAO salaDAO;
    private JPanel painelDeItens;
    private JScrollPane lista;
    private JButton botaoAdicionar;
    private JPanel painelScroll;

    public ColunaSala(String titulo) {
        super(titulo);

        salaDAO = new SalaDAO();
        painelDeItens = new JPanel();
        painelDeItens.setLayout(new BoxLayout(painelDeItens, BoxLayout.Y_AXIS));

        botaoAdicionar = new JButton("Nova sala");
        botaoAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelScroll = new JPanel(new BorderLayout());
        painelScroll.add(painelDeItens, BorderLayout.NORTH);
        lista = new JScrollPane(painelScroll);
        lista.setPreferredSize(new Dimension(190, 400));

        botaoAdicionar.addActionListener(e -> {
            validadorForm();
        });

        add(Box.createVerticalStrut(5));
        add(botaoAdicionar);
        add(Box.createVerticalStrut(10));
        add(lista);
    }

    public void validadorForm() {
        String numeroSala = null;

        while (numeroSala == null) {
            numeroSala = JOptionPane.showInputDialog(this, "Digite o número da sala:");
            if (numeroSala == null) {
                break;
            }
            try {
                int numeroSalaFormat = Integer.parseInt(numeroSala.trim());
                adicionar(numeroSalaFormat);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Digite apenas números!", "Erro", JOptionPane.ERROR_MESSAGE);
                numeroSala = null;
            }
        }
    }

    public void adicionar(int numSala) {
        Sala novaSala = new Sala();
        novaSala.setNumero(numSala);
        salaDAO.adicionar(novaSala);
        atualizarLista();
    }

    public void atualizarLista() {
        painelDeItens.removeAll();
        for (Sala sala : salaDAO.listar()) {
            ObjetoListaSala objetoListaSala = new ObjetoListaSala(sala);
            painelDeItens.add(objetoListaSala);
        }
        painelDeItens.revalidate();
        painelDeItens.repaint();
    }
}