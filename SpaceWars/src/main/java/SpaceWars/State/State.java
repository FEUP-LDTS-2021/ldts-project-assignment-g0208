package SpaceWars.State;

import SpaceWars.Game;
import SpaceWars.Controller.Controller;
import SpaceWars.GUI.GUI;
import SpaceWars.Viewer.Viewer;

import java.io.IOException;

public abstract class State <T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public State(T model) throws IOException {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    protected abstract Viewer<T> getViewer() throws IOException;

    protected abstract Controller<T> getController() throws IOException;

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.PressedKey action = gui.getKeyInput();
        controller.step(game, action, time);
        viewer.draw(gui);
    }

    //public abstract void processKey(GUI.PressedKey keyP);

}
