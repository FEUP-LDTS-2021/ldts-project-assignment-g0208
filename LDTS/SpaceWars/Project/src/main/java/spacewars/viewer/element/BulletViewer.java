package spacewars.viewer.element;

import spacewars.gui.GUI;
import spacewars.model.element.bullet.Bullet;

public class BulletViewer implements ElementViewer<Bullet>{
    @Override
    public void drawElement(Bullet element, GUI gui) {
        gui.drawBullet(element.getPosition(), element.getColor(), ".");
    }
}
