package solver.Commands;

import solver.Main;
import solver.Row;

import java.util.stream.IntStream;

public class CommandSubtract implements Command {

    private Row rowFrom;
    private Row rowTo;

    /**
     * Constructor
     * @param rowTo - row that will contain result
     * @param row - the row which elements will be subtracted
     */
    public CommandSubtract(Row rowTo, Row row) {
        rowFrom = row;
        this.rowTo = rowTo;
    }

    @Override
    public void execute() {
        Main.commandHistory.offerLast(this);
        if (rowFrom.size() == rowTo.size()) {
            IntStream.range(0, rowFrom.size()).parallel().forEach(i -> rowTo.set(i, rowTo.get(i).subtract(rowFrom.get(i))));
        }
    }

    @Override
    public void undo() {
        if (rowFrom.size() == rowTo.size()) {
            IntStream.range(0, rowFrom.size()).parallel().forEach(i -> rowTo.set(i, rowFrom.get(i).add(rowTo.get(i))));
        }
    }
}
