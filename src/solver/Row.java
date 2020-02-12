package solver;

import java.util.ArrayList;
import java.util.List;

public class Row {

    private List<Complex> values = new ArrayList<>();

    public void add(Complex value) {
        values.add(value);
    }

    public void set(int index, Complex value) {
        if (values.size() > index) {
            values.set(index, value);
        }
    }

    public int size() {
        return values.size();
    }

    public Complex get(int index) {
        return values.size() > index && index >= 0 ? values.get(index): new Complex(0.0, 0.0);
    }

    public List<Complex> getValues() {
        return values;
    }

    public void setValues(List<Complex> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Row{" +
                "values=" + values +
                '}';
    }
}
