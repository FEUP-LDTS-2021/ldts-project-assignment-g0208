package spacewars.controller;

import spacewars.model.level.Level;

public abstract class GameController extends Controller<Level> {
    public GameController(Level level) { super(level); }
}
