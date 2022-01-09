package SpaceWars;

import SpaceWars.GUI.GUI;
import SpaceWars.GUI.LanternaGUI;
import SpaceWars.Model.Menu.StartMenu;
import SpaceWars.State.State;
import SpaceWars.Model.Game.Level;
import SpaceWars.State.StateMenu;

import java.io.IOException;

public class Game {
    private final GUI gui;
    private State state;
    private Level level;
    private boolean exit;

    public Game() throws IOException {
        this.state = new StateMenu(new StartMenu(this));
        gui = new LanternaGUI();
        this.exit = false;
    }

    public static void main (String[] args) throws IOException {
        Game game = new Game();
        game.start();
    }

    public void changeState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void exit() {
        exit = true;
    }

    private void start() throws IOException {
        int FPS = 60;
        int frameTime = 1000/FPS;

        while(!exit) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;
        }
        gui.close();
    }

    public Level getLevel() {
        return this.level;
    }
}
