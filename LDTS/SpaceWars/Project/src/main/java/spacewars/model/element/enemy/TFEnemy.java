package spacewars.model.element.enemy;

import spacewars.model.Position;
import spacewars.model.item.Gun;

import java.util.Arrays;

public class TFEnemy extends Enemy {

    public TFEnemy(){
        super(Arrays.asList("#FF6961", "#A34741"));
        this.gun = new Gun(4, 5);
    }

    public TFEnemy(Position position) {
        super(Arrays.asList("#FF6961", "#A34741"));
        this.position = position;
        this.gun = new Gun(4, 5);
    }

}
