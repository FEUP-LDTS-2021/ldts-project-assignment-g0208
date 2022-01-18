package spacewars.viewer.element;

import spacewars.gui.GUI;
import spacewars.gui.LanternaGUI;
import spacewars.model.Position;
import spacewars.model.element.bullet.Bullet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BulletViewerTest {
    Bullet bullet;
    BulletViewer bulletViewer;
    GUI gui;

    @BeforeEach
    void setUp() {
        Position position = new Position(3,5);
        this.bullet = new Bullet(4,5,"#FFFFFF",true);
        this.bulletViewer= new BulletViewer();
        this.gui = Mockito.mock(LanternaGUI.class);

    }

    @Test
    void drawElement() {
        bulletViewer.drawElement(bullet,gui);
        Mockito.verify(gui, Mockito.times(1)).drawBullet(bullet.getPosition(), bullet.getColor(),".");
    }
}