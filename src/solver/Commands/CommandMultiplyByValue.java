package solver.Commands;

import solver.Complex;
import solver.Main;
import solver.Row;

import java.util.stream.Collectors;

public class CommandMultiplyByValue implements Command {

    private Row row;
    private Complex value;
    private Row rawForZeroValue = new Row();

    public CommandMultiplyByValue(Row row, Complex value) {
        this.row = row;
        this.value = value;
        if (value.isZero()) {
            rawForZeroValue.setValues(row.getValues());
        }
    }

    @Override
    public void execute() {
        Main.commandHistory.offerLast(this);
        row.setValues(row.getValues().stream().map(e -> e.multiply(value)).collect(Collectors.toList()));
    }

    @Override
    public void undo() {
        if (value.isZero()) {
            row.setValues(rawForZeroValue.getValues());
        } else {
            row.setValues(row.getValues().stream().map(e -> e.divide(value)).collect(Collectors.toList()));
        }
    }
}
