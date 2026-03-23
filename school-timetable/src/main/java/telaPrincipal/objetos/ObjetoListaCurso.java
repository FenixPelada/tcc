package telaPrincipal.objetos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.curso.Curso;
import model.curso.CursoDAO;
import model.materia.Materia;
import model.materia.MateriaDAO;
import model.relacao.MateriaCursoDAO;
import telaPrincipal.colunas.ColunaCurso;

public class ObjetoListaCurso extends JPanel {

    JButton botaoExcluir;
    JButton botaoEditar;
    JButton botaoVincular;
    JPanel painelBotoes;
    JPanel painelLista;
    CursoDAO cursoDAO;
    MateriaDAO materiaDAO;
    MateriaCursoDAO cursoMateriaDAO;
    List<Materia> todasMaterias;
    List<Materia> materiasVinculadas;
    JDialog dialog;
    
    public ObjetoListaCurso(Curso curso, ColunaCurso colunaCurso) {
    	
        cursoDAO = new CursoDAO();

        setLayout(new BorderLayout(10, 0));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        add(new JLabel(curso.getNome()), BorderLayout.WEST);

        botaoEditar = new JButton("Editar");
        botaoExcluir = new JButton("Excluir");
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

            if (nomeCurso != null) {
                curso.setNome(nomeCurso);
                cursoDAO.editar(curso);
                colunaCurso.atualizarLista();
            }
        });

        botaoVincular.addActionListener(e -> {
            // busca todas as matérias disponíveis
        	materiaDAO = new MateriaDAO();
            cursoMateriaDAO = new MateriaCursoDAO();
            todasMaterias = materiaDAO.listar();
            materiasVinculadas = cursoMateriaDAO.listarMateriasPorCurso(curso);

            // monta o JDialog
            dialog = new JDialog();
            dialog.setTitle("Vincular matérias ao curso: " + curso.getNome());
            dialog.setSize(300, 400);
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BorderLayout());

            painelLista = new JPanel();
            painelLista.setLayout(new BoxLayout(painelLista, BoxLayout.Y_AXIS));

            // cria um checkbox para cada matéria
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
                        cursoMateriaDAO.vincular(curso, materia);
                    } else if (!cb.isSelected() && eraVinculada) {
                        cursoMateriaDAO.desvincular(curso, materia);
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