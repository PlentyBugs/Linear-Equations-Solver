package solver.Commands;

import solver.Complex;
import solver.Main;
import solver.Matrix;
import solver.Row;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class CommandClear implements Command {

    private Matrix matrix;
    private Deque<Integer> stack = new ArrayDeque<>();

    public CommandClear(Matrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        Main.commandHistory.offerLast(this);
        int index = 0;
        List<Row> rows = matrix.getRows();
        while (index < rows.size()) {
            if (rows.get(index).getValues().parallelStream().allMatch(Complex::isZero)) {
                rows.remove(index);
                stack.offerLast(index);
            } else {
                index += 1;
            }
        }
    }

    @Override
    public void undo() {
        List<Row> rows = matrix.getRows();
        for (int i : stack) {
            Complex[] vals = new Complex[rows.size() > 0 ? rows.get(0).size(): 0];
            Arrays.fill(vals, new Complex(0.0, 0.0));
            Row row = new Row();
            row.setValues(Arrays.asList(vals));
            rows.add(i, row);
        }
    }
}
