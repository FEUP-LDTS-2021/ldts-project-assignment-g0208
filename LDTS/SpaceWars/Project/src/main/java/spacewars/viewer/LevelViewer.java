package spacewars.viewer;

import spacewars.gui.GUI;
import spacewars.model.element.Element;
import spacewars.model.level.Level;
import spacewars.viewer.element.ElementViewer;
import spacewars.viewer.element.EnemyViewer;
import spacewars.viewer.element.PlayerViewer;
import spacewars.viewer.element.BulletViewer;

import java.io.IOException;
import java.util.List;

public class LevelViewer {
    private final GUI gui;
    private final Level level;


    public LevelViewer(GUI gui, Level level) {
        this.gui = gui;
        this.level = level;
    }

    public void draw() throws IOException {
        gui.clear();

        drawBackground();
        drawElements(this.level.getEnemies(), new EnemyViewer());
        drawElements(this.level.getBullets(), new BulletViewer());
        drawElement(this.level.getPlayer(), new PlayerViewer());

        gui.refresh();
    }

    private void drawBackground() {
    }


    private <T extends Element> void drawElements(List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(element, viewer);
    }

    private <T extends Element> void drawElement(T element, ElementViewer<T> viewer) {
        viewer.drawElement(element, gui);
    }


}
