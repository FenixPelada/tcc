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

import model.materia.Materia;
import model.materia.MateriaDAO;
import telaPrincipal.objetos.ObjetoListaMateria;

public class ColunaMateria extends CriarColuna {

    private MateriaDAO materiaDAO;
    private JPanel painelDeItens;
    private JScrollPane lista;
    private JButton botaoAdicionar;
    private JPanel painelScroll;

    public ColunaMateria(String titulo) {
        super(titulo);

        materiaDAO = new MateriaDAO();
        painelDeItens = new JPanel();
        painelDeItens.setLayout(new BoxLayout(painelDeItens, BoxLayout.Y_AXIS));

        botaoAdicionar = new JButton("Nova materia");
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
        String nomeMateria = null;

        while (nomeMateria == null) {
            nomeMateria = JOptionPane.showInputDialog(this, "Digite o nome da matéria:");
            if (nomeMateria == null) {
                break;
            }
            if (nomeMateria.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
                nomeMateria = null;
            } else {
                adicionar(nomeMateria.trim());
            }
        }
    }

    public void adicionar(String nome) {
        Materia novaMateria = new Materia();
        novaMateria.setNome(nome);
        materiaDAO.adicionar(novaMateria);
        atualizarLista();
    }

    public void atualizarLista() {
        painelDeItens.removeAll();
        for (Materia materia : materiaDAO.listar()) {
            ObjetoListaMateria objetoListaMateria = new ObjetoListaMateria(materia);
            painelDeItens.add(objetoListaMateria);
        }
        painelDeItens.revalidate();
        painelDeItens.repaint();
    }
}