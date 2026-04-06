package tela1.colunas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.professor.Professor;
import model.professor.ProfessorDAO;
import tela1.objetos.ObjetoListaProfessor;

public class ColunaProfessor extends CriarColuna {

    private ProfessorDAO professorDAO;
    private JPanel painelDeItens;
    private JScrollPane lista;
    private JButton botaoAdicionar;
    private JPanel painelScroll;

    public ColunaProfessor(String titulo) {
        super(titulo);

        professorDAO = new ProfessorDAO();
        painelDeItens = new JPanel();
        painelDeItens.setLayout(new BoxLayout(painelDeItens, BoxLayout.Y_AXIS));

        botaoAdicionar = new JButton("Novo professor");
        botaoAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelScroll = new JPanel(new BorderLayout());
        painelScroll.add(painelDeItens, BorderLayout.NORTH);
        lista = new JScrollPane(painelScroll);
        lista.setPreferredSize(new Dimension(190, 400));
        
        atualizarLista();

        
        botaoAdicionar.addActionListener(e -> {
            validadorForm();
        });

        add(Box.createVerticalStrut(5));
        add(botaoAdicionar);
        add(Box.createVerticalStrut(10));
        add(lista);
    }

    public void validadorForm() {
        String nomeProfessor = null;

        while (nomeProfessor == null) {
            nomeProfessor = JOptionPane.showInputDialog(this, "Digite o nome do professor:");
            if (nomeProfessor == null) {
                break;
            }
            if (nomeProfessor.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
                nomeProfessor = null;
            } else {
                adicionar(nomeProfessor.trim());
            }
        }
    }

    public void adicionar(String nome) {
        Professor novoProfessor = new Professor();
        novoProfessor.setNome(nome);
        professorDAO.adicionar(novoProfessor);
        atualizarLista();
    }

    public void atualizarLista() {
        painelDeItens.removeAll();
        for (Professor professor : professorDAO.listar()) {
            ObjetoListaProfessor objetoListaProfessor = new ObjetoListaProfessor(professor, this);
            painelDeItens.add(objetoListaProfessor);
        }
        painelDeItens.revalidate();
        painelDeItens.repaint();
    }
}