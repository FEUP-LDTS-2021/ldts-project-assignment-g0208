package spacewars.viewer.element;

import spacewars.gui.GUI;
import spacewars.model.element.enemy.TFEnemy;
import spacewars.model.element.enemy.Enemy;
import spacewars.model.element.enemy.DSEnemy;
import spacewars.model.element.enemy.SDEnemy;

public class EnemyViewer implements ElementViewer<Enemy> {
    @Override
    public void drawElement(Enemy element, GUI gui) {
        if (element instanceof SDEnemy) drawSD(element, gui);
        else if ( element instanceof DSEnemy) drawDS(element, gui);
        else if ( element instanceof TFEnemy) drawTF(element, gui);
    }

    public void drawSD(Enemy element, GUI gui) {
        gui.drawSD(element.getPosition(), element.getColor());
    }
    public void drawDS(Enemy element, GUI gui) {
        gui.drawDS(element.getPosition(), element.getColor());
    }
    public void drawTF(Enemy element, GUI gui) { gui.drawTF(element.getPosition(), element.getColor());
    }

}
