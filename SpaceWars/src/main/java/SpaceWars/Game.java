package SpaceWars;

import SpaceWars.Gui.GUI;
import SpaceWars.Gui.LanternGui;

import java.io.IOException;
import java.util.logging.Level;

public class Game {
    private final GUI gui;
    private boolean exit;
    private Level level;
    private State sate;

    public Game() throws IOException {
        gui = new LanternGui();
        this.exit = false;
    }

    public static void main (String[] args) throws IOException {
        Game game = new Game();
        game.start();
    }

    public void exit() {
        exit = true;
    }

    private void start() throws IOException {
        int FPS = 60;
        int frameTime = 1000/FPS;

        while (!exit) {

            long timeStart = System.currentTimeMillis();

            /*state.step(this, gui, startTime);*/

            long elapsedTime = System.currentTimeMillis() - timeStart;
            long sleepTime = frameTime - elapsedTime;

            try {
                if(sleepTime > 0 ) Thread.sleep(sleepTime);
            }catch (InterruptedException e){}

            /*gui.close();*/
        }

        public Level getLevel() {
            return this.level;
        }
    }
}
