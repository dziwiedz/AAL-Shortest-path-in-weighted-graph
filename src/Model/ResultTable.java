package Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gulgulgulgul on 27.05.2016.
 */
public class ResultTable extends AbstractTableModel {

    private List<String[]> rows;
    private ArrayList<String> columnNames;
    private String tableName;

    public ResultTable(List<String[]> rows)
    {
        ArrayList<String[]> r = new ArrayList<String[]>();


        String[] tmpStr = new String[6];
        for (int i=0; i<6;i++)
        {
            tmpStr[i]="";
        }
        rows.add(tmpStr);
        this.rows = rows;
        columnNames = new ArrayList<>();
        columnNames.add("Alg.");
        columnNames.add("N");
        columnNames.add("M");
        columnNames.add("n");
        columnNames.add("t(n)");
        columnNames.add("q(n)");

        tableName = "Tabela :v";
    }
    public String getTableName() {
        return tableName;
    }


    public String[] getColumnNames() {
        return columnNames.stream().toArray(String[]::new);
    }

    public void setRows(List<String[]> rows) {
        this.rows = rows;
    }

    public void removeRow(int row) {
        int rows = getRowCount();
        if (rows>0)
        {
            this.rows.clear();
            fireTableRowsDeleted(0, this.getRowCount()-1);
        }
    }

    public void updateRow(String[] newRow, int rowIndex) {
        // Sets new row
        this.rows.set(rowIndex, newRow);

        this.fireTableRowsUpdated(rowIndex, rowIndex);
    }
    @Override
    public int getRowCount()
    {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
       if (rows.size()>0)  return rows.get(0).length;
        else return 0;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        String[] row = rows.get(rowIndex);
        return row[columnIndex];
    }
}
