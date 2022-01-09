package SpaceWars;

import SpaceWars.GUI.GUI;
import SpaceWars.GUI.LanternGUI;

import java.io.IOException;

public class Game {
    private final GUI gui;
    private boolean exit;

    public Game() throws IOException {
        gui = new LanternGUI();
        this.exit = false;
    }

    public static void main (String[] args) throws IOException {
        Game game = new Game();
    }

    public void exit() {
        exit = true;
    }
}
