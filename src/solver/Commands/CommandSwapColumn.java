package solver.Commands;

import solver.Complex;
import solver.Main;
import solver.Matrix;
import solver.Row;

import java.util.List;

public class CommandSwapColumn implements Command {

    private Matrix matrix;
    private int indexOfFirstColumn;
    private int indexOfSecondColumn;

    /**
     * Constructor
     * @param matrix - Matrix which columns will be swapped
     * @param indexOfFirstColumn - index of the first column
     * @param indexOfSecondColumn - index of the second column
     */
    public CommandSwapColumn(Matrix matrix, int indexOfFirstColumn, int indexOfSecondColumn) {
        this.matrix = matrix;
        this.indexOfFirstColumn = indexOfFirstColumn;
        this.indexOfSecondColumn = indexOfSecondColumn;
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
        int size = matrix.size() > 0 ? matrix.getRow(0).size(): -1;
        if (size > indexOfFirstColumn && size > indexOfSecondColumn && size > 0) {
            for (Row row : matrix.getRows()){
                List<Complex> list = row.getValues();
                Complex temp = list.get(indexOfFirstColumn);
                list.set(indexOfFirstColumn, list.get(indexOfSecondColumn));
                list.set(indexOfSecondColumn, temp);
            }
        }
    }

    public int getIndexOfFirstColumn() {
        return indexOfFirstColumn;
    }

    public int getIndexOfSecondColumn() {
        return indexOfSecondColumn;
    }
}
