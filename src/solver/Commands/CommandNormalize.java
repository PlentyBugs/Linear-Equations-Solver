package solver.Commands;

import solver.Main;
import solver.Matrix;
import solver.Row;

import java.util.List;

public class CommandNormalize implements Command {

    private Matrix matrix;

    public CommandNormalize(Matrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        Main.commandHistory.offerLast(this);
        List<Row> rows = matrix.getRows();
        int index = -1;
        int size = rows.size() > 0 ? rows.get(0).size() - 1: -1;
        for (int i = 0; i < rows.size(); i++) {
            Row row = rows.get(i);
            int searchValueIndex = Math.min(size, i);
            if (row.get(searchValueIndex).isZero() && index == -1) {
                index = searchValueIndex;
            }
            if (!row.get(index).isZero() && index != -1) {
                new CommandSwapRow(matrix, index, i).execute();
                i = 0;
                index = -1;
            }
            if (size == i && index != -1) {
                for (int j = index + 1; j < size; j++) {
                    if (!row.get(j).isZero()) {
                        new CommandSwapColumn(matrix, index, j).execute();
                        i = 0;
                        index = -1;
                        break;
                    }
                }
            }
        }

    }

    @Override
    public void undo() {

    }
}
