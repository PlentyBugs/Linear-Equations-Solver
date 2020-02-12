package solver.Commands;

import solver.Main;
import solver.Matrix;
import solver.Row;

public class CommandSwapRow implements Command {

    private Matrix matrix;
    int indexOfFirstRow;
    int indexOfSecondRow;

    /**
     * Constructor
     * @param matrix - Matrix which columns will be swapped
     * @param indexOfFirstRow - index of the first column
     * @param indexOfSecondRow - index of the second column
     */
    public CommandSwapRow(Matrix matrix, int indexOfFirstRow, int indexOfSecondRow) {
        this.matrix = matrix;
        this.indexOfFirstRow = indexOfFirstRow;
        this.indexOfSecondRow = indexOfSecondRow;
    }

    @Override
    public void execute() {
        Main.commandHistory.offerLast(this);
        swap();
    }

    @Override
    public void undo() {
        swap();
    }

    private void swap() {
        if (matrix.size() > indexOfFirstRow && matrix.size() > indexOfSecondRow) {
            Row temp = matrix.getRow(indexOfFirstRow);
            matrix.setRow(indexOfFirstRow, matrix.getRow(indexOfSecondRow));
            matrix.setRow(indexOfSecondRow, temp);
        }
    }
}
