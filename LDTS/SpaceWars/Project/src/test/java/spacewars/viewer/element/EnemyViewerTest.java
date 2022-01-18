package spacewars.viewer.element;

import spacewars.gui.GUI;
import spacewars.gui.LanternaGUI;
import spacewars.model.Position;
import spacewars.model.element.enemy.DSEnemy;
import spacewars.model.element.enemy.Enemy;
import spacewars.model.element.enemy.SDEnemy;
import spacewars.model.element.enemy.TFEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EnemyViewerTest {
    TFEnemy tfEnemy;
    DSEnemy dsEnemy;
    SDEnemy sdEnemy;
    EnemyViewer enemyViewer;
    GUI gui;

    @BeforeEach
    void setUp() {
        Position position = new Position(3,5);
        this.tfEnemy = new TFEnemy();
        this.dsEnemy = new DSEnemy();
        this.sdEnemy = new SDEnemy();
        this.enemyViewer= new EnemyViewer();
        this.gui = Mockito.mock(LanternaGUI.class);
    }

    @Test
    void drawElement() {
        Enemy enemy = tfEnemy;
        enemyViewer.drawElement(enemy,gui);
        Mockito.verify(gui, Mockito.times(1)).drawTF(enemy.getPosition(), enemy.getColor());

        enemy = dsEnemy;
        enemyViewer.drawElement(enemy,gui);
        Mockito.verify(gui, Mockito.times(1)).drawDS(enemy.getPosition(), enemy.getColor());

        enemy = sdEnemy;
        enemyViewer.drawElement(enemy,gui);
        Mockito.verify(gui, Mockito.times(1)).drawSD(enemy.getPosition(), enemy.getColor());
    }
}