package tela1.objetos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.materia.Materia;
import model.materia.MateriaDAO;
import model.professor.Professor;
import model.professor.ProfessorDAO;
import model.relacao.ProfessorMateriaDAO;
import tela1.colunas.ColunaProfessor;

public class ObjetoListaProfessor extends JPanel {

    JButton botaoExcluir;
    JButton botaoEditar;
    JButton botaoVincular;
    JPanel painelBotoes;
    ProfessorDAO professorDAO;

    public ObjetoListaProfessor(Professor professor, ColunaProfessor colunaProfessor) {

        professorDAO = new ProfessorDAO();

        setLayout(new BorderLayout(10, 0));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        add(new JLabel(professor.getNome()), BorderLayout.WEST);

        botaoEditar   = new JButton("Editar");
        botaoExcluir  = new JButton("Excluir");
        botaoVincular = new JButton("Vincular");

        botaoEditar.setPreferredSize(new Dimension(80, 25));
        botaoEditar.setMaximumSize(new Dimension(80, 25));
        botaoExcluir.setPreferredSize(new Dimension(80, 25));
        botaoExcluir.setMaximumSize(new Dimension(80, 25));
        botaoVincular.setPreferredSize(new Dimension(80, 50));
        botaoVincular.setMaximumSize(new Dimension(80, 50));

        JPanel painelEditarExcluir = new JPanel();
        painelEditarExcluir.setLayout(new BoxLayout(painelEditarExcluir, BoxLayout.Y_AXIS));
        painelEditarExcluir.add(botaoEditar);
        painelEditarExcluir.add(botaoExcluir);

        painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.X_AXIS));
        painelBotoes.add(painelEditarExcluir);
        painelBotoes.add(botaoVincular);

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

            if (nomeProfessor != null) {
                professor.setNome(nomeProfessor);
                professorDAO.editar(professor);
                colunaProfessor.atualizarLista();
            }
        });

        botaoVincular.addActionListener(e -> {
            MateriaDAO materiaDAO = new MateriaDAO();
            ProfessorMateriaDAO professorMateriaDAO = new ProfessorMateriaDAO();
            List<Materia> todasMaterias = materiaDAO.listar();
            List<Materia> materiasVinculadas = professorMateriaDAO.listarMateriasPorProfessor(professor);

            JDialog dialog = new JDialog();
            dialog.setTitle("Vincular matérias ao professor: " + professor.getNome());
            dialog.setSize(300, 400);
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BorderLayout());

            JPanel painelLista = new JPanel();
            painelLista.setLayout(new BoxLayout(painelLista, BoxLayout.Y_AXIS));

            List<JCheckBox> checkboxes = new ArrayList<>();
            for (Materia materia : todasMaterias) {
                boolean jaVinculada = materiasVinculadas.stream()
                    .anyMatch(m -> m.getId() == materia.getId());
                JCheckBox cb = new JCheckBox(materia.getNome(), jaVinculada);
                cb.putClientProperty("materia", materia);
                checkboxes.add(cb);
                painelLista.add(cb);
            }

            JButton botaoSalvar = new JButton("Salvar");
            botaoSalvar.addActionListener(ev -> {
                for (JCheckBox cb : checkboxes) {
                    Materia materia = (Materia) cb.getClientProperty("materia");
                    boolean eraVinculada = materiasVinculadas.stream()
                        .anyMatch(m -> m.getId() == materia.getId());

                    if (cb.isSelected() && !eraVinculada) {
                        professorMateriaDAO.vincular(professor, materia);
                    } else if (!cb.isSelected() && eraVinculada) {
                        professorMateriaDAO.desvincular(professor, materia);
                    }
                }
                dialog.dispose();
            });

            dialog.add(new JScrollPane(painelLista), BorderLayout.CENTER);
            dialog.add(botaoSalvar, BorderLayout.SOUTH);
            dialog.setVisible(true);
        });
    }
}