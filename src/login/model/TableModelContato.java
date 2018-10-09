package login.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Programinhas bruno.chagas
 */
public class TableModelContato extends AbstractTableModel {

    protected ArrayList<Contato> listaDeContatos;
    String[] colunas = {"Nome", "Telefone", "Email"};

    public TableModelContato() {
        this.listaDeContatos = new ArrayList<>();

    }

    public String[] getColunas() {
        return this.colunas;
    }

    /**
     * Retorna uma linha completa da tabela
     *
     * @param rowIndex
     * @return Pessoa
     */
    public Contato getContato(int rowIndex) {
        return this.listaDeContatos.get(rowIndex);
    }

    public void removeAll() {
       this.listaDeContatos = new ArrayList();
       fireTableDataChanged();
    }

    /**
     * Adiciona um contato a tabela (cria uma linha)
     *
     * @param pPessoa
     */
    public void addContato(Contato pPessoa) {
        this.listaDeContatos.add(pPessoa);

        this.fireTableDataChanged();
    }

    /**
     * Remove um contato da tabela (remove uma linha)
     *
     * @param rowIndex
     */
    public void removePessoa(int rowIndex) {
        this.listaDeContatos.remove(rowIndex);
        fireTableDataChanged();
    }

    /**
     * Retorna a quantidade de linhas da tabela
     *
     * @return int
     */
    @Override
    public int getRowCount() {

        return this.listaDeContatos.size();
    }

    /**
     * Retorna o numero de colunas da tabela
     *
     * @return int
     */
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    public ArrayList getArrayList() {
        return this.listaDeContatos;
    }

    /**
     * Retorna o valor especifico de uma celula
     *
     * @param rowIndex
     * @param columnIndex
     * @return Object
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        //switch na coluna
        switch (columnIndex) {

            //coluna do nome da Contato
            case 0:
                return this.listaDeContatos.get(rowIndex).getNome();

            //coluna da telefone da Contato
            case 1:
                return this.listaDeContatos.get(rowIndex).getTelefone();
            //coluna da email da Contato
            case 2:
                return this.listaDeContatos.get(rowIndex).getEmail();
                //coluna oculta de id
            case 3:
                return this.listaDeContatos.get(rowIndex).getId();
            default:
                return this.listaDeContatos.get(rowIndex);
        }
    }
    /**
     * Pega o nome de uma coluna
     * @param columnIndex
     * @return 
     */
    @Override
    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }
}
