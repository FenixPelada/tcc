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

import model.curso.Curso;
import model.curso.CursoDAO;
import telaPrincipal.objetos.ObjetoListaCurso;

public class ColunaCurso extends CriarColuna {

    //private Curso curso;
    private CursoDAO cursoDAO;
    private JPanel painelDeItens;
    private JScrollPane lista;
    private JButton botaoAdicionar;
    private JPanel painelScroll;
    
    public ColunaCurso(String titulo) {
        super(titulo);
        
        //curso = new Curso();
        cursoDAO = new CursoDAO();
        painelDeItens = new JPanel();
        painelDeItens.setLayout(new BoxLayout(painelDeItens, BoxLayout.Y_AXIS));
        
        botaoAdicionar = new JButton("Novo curso");
        botaoAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        painelScroll = new JPanel(new BorderLayout());
        painelScroll.add(painelDeItens, BorderLayout.NORTH);

        lista = new JScrollPane(painelScroll);
        lista.setPreferredSize(new Dimension(190, 400));

        botaoAdicionar.addActionListener(e -> {
            //nomeCurso = JOptionPane.showInputDialog(this, "Digite o nome do curso:");
            validadorForm();
            //adicionar(nomeCurso.trim());
        });

        add(Box.createVerticalStrut(5));
        add(botaoAdicionar);
        add(Box.createVerticalStrut(10));
        add(lista);
    }
    
    public void validadorForm() {
        String nomeCurso = null;

        while (nomeCurso == null) {
            nomeCurso = JOptionPane.showInputDialog(this, "Digite o nome do curso:");
            if (nomeCurso == null) {
                break;
            }
            if (nomeCurso.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
                nomeCurso = null;
            } else {
                adicionar(nomeCurso.trim());
            }
        }
    }
    
    public void adicionar(String nome) {
        Curso novoCurso = new Curso();
        novoCurso.setNome(nome);
        cursoDAO.adicionar(novoCurso);
        atualizarLista();
    }

    public void atualizarLista() {
        painelDeItens.removeAll();
        for (Curso curso : cursoDAO.listar()) {
            ObjetoListaCurso objetoListaCurso = new ObjetoListaCurso(curso);
            painelDeItens.add(objetoListaCurso);
        }
        painelDeItens.revalidate();
        painelDeItens.repaint();
    }
}