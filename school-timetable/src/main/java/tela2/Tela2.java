package tela2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.professor.DiasIndisponiveis;
import model.professor.Professor;
import model.professor.ProfessorDAO;

public class Tela2 extends JPanel {

    private ProfessorDAO professorDAO;
    private JPanel painelProfessores;
    private JPanel painelDias;

    public Tela2(CardLayout cardLayout, JPanel container) {

        professorDAO = new ProfessorDAO();

        setLayout(new BorderLayout());

        // Painel esquerdo — dias da semana
        painelDias = new JPanel();
        painelDias.setLayout(new BoxLayout(painelDias, BoxLayout.Y_AXIS));
        painelDias.setPreferredSize(new Dimension(250, 0));
        painelDias.setBorder(BorderFactory.createTitledBorder("Disponibilidade"));

        // Painel direito — lista de professores + botões
        JPanel painelDireito = new JPanel(new BorderLayout());
        painelDireito.setPreferredSize(new Dimension(200, 0));

        painelProfessores = new JPanel();
        painelProfessores.setLayout(new BoxLayout(painelProfessores, BoxLayout.Y_AXIS));
        JScrollPane scrollProfessores = new JScrollPane(painelProfessores);

        // Botões de navegação
        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 5, 0));
        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoProximo = new JButton("Próximo");

        botaoVoltar.addActionListener(e -> cardLayout.show(container, "Tela 1"));
        botaoProximo.addActionListener(e -> cardLayout.show(container, "Tela 3"));

        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoProximo);

        painelDireito.add(scrollProfessores, BorderLayout.CENTER);
        painelDireito.add(painelBotoes, BorderLayout.SOUTH);

        add(painelDias, BorderLayout.CENTER);
        add(painelDireito, BorderLayout.EAST);

        carregarProfessores();
    }

    private void carregarProfessores() {
        painelProfessores.removeAll();
        for (Professor professor : professorDAO.listar()) {
            JButton botaoProfessor = new JButton(professor.getNome());
            botaoProfessor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            botaoProfessor.addActionListener(e -> mostrarDias(professor));
            painelProfessores.add(botaoProfessor);
        }
        painelProfessores.revalidate();
        painelProfessores.repaint();
    }

    private void mostrarDias(Professor professor) {
        painelDias.removeAll();

        List<DiasIndisponiveis> diasIndisponiveis = professorDAO.carregarDisponibilidade(professor);
        professor.setDiasIndisponiveis(diasIndisponiveis);

        JLabel labelNome = new JLabel(professor.getNome());
        labelNome.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelNome.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        painelDias.add(labelNome);

        JPanel gridDias = new JPanel(new GridLayout(5, 1, 5, 5));
        gridDias.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (DiasIndisponiveis dia : DiasIndisponiveis.values()) {
            boolean indisponivel = diasIndisponiveis.contains(dia);

            JButton botaoDia = new JButton(dia.name());
            botaoDia.setBackground(indisponivel ? Color.RED : Color.GREEN);
            botaoDia.setOpaque(true);
            botaoDia.setForeground(Color.WHITE);
            botaoDia.setFocusPainted(false);

            botaoDia.addActionListener(e -> {
                List<DiasIndisponiveis> atual = professor.getDiasIndisponiveis();
                if (atual == null) atual = new ArrayList<>();

                if (atual.contains(dia)) {
                    atual.remove(dia);
                    botaoDia.setBackground(Color.GREEN);
                } else {
                    atual.add(dia);
                    botaoDia.setBackground(Color.RED);
                }

                professor.setDiasIndisponiveis(atual);
                professorDAO.salvarDisponibilidade(professor);
            });

            gridDias.add(botaoDia);
        }

        painelDias.add(gridDias);
        painelDias.revalidate();
        painelDias.repaint();
    }
}