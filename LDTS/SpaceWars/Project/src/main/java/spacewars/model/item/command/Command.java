package spacewars.model.item.command;

public interface Command {
    boolean execute();
    void undo();
}
