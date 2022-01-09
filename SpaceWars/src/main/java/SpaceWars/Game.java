package SpaceWars;

import SpaceWars.Model.Menu.StartMenu;
import SpaceWars.State.State;
import SpaceWars.State.StateMenu;
import SpaceWars.GUI.GUI;
import SpaceWars.GUI.LanternaGUI;

import java.io.IOException;

public class Game {
    private final GUI gui;
    private State state;
    private boolean exit;
    public Game() throws IOException{

        gui = new LanternaGUI();
        this.state = new StateMenu(new StartMenu(this));
        this.exit = false;
    }

    public static void main(String[] args) throws IOException {
        Game game=new Game();
        game.start();
    }

    public void exit(){
        exit = true;
    }

    private void start() throws IOException {
        int FPS = 60;
        int frameTime = 1000 / FPS;

        while (!exit) {

            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }

        }

        gui.close();
    }
}

