package solver;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private List<Row> rows = new ArrayList<>();
    private String verdict = "";

    public Row getRow(int index) {
        return rows.size() > index ? rows.get(index): new Row();
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRow(int index, Row row) {
        if (rows.size() > index && index >= 0) {
            rows.set(index, row);
        }
    }

    public void addRow(Row row) {
        rows.add(row);
    }

    public int size() {
        return rows.size();
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }
}
