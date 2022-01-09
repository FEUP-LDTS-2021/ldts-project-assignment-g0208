package SpaceWars.Controller;

import SpaceWars.Game;
import SpaceWars.GUI.GUI;

import java.io.IOException;

public abstract class Controller<T> {
    public final T model;

    public Controller(T model) {this.model = model;}

    public T getModel() {return model;}

    public abstract void step(Game game, GUI.PressedKey action, long time) throws IOException;
}
