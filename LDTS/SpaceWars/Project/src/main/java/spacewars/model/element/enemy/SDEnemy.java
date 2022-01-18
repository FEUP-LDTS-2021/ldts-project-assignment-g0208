package spacewars.model.element.enemy;

import spacewars.model.Position;
import spacewars.model.item.Gun;

import java.util.Arrays;

public class SDEnemy extends Enemy {

    public SDEnemy(){
        super(Arrays.asList("#FFB7AD", "#DC4A46"));
        this.gun = new Gun(1, 15);
    }
    public SDEnemy(Position position) {
        super(Arrays.asList("#FFB7AD", "#DC4A46"));
        this.position = position;
        this.gun = new Gun(1, 15);
    }
}
