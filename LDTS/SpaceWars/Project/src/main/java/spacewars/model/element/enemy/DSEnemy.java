package spacewars.model.element.enemy;

import spacewars.model.Position;
import spacewars.model.item.Gun;

import java.util.Arrays;

public class DSEnemy extends Enemy {

    public DSEnemy(){
        super(Arrays.asList("#EF7771", "#FF261B"));
        this.gun = new Gun(3, 10);
    }
    public DSEnemy(Position position) {
        super(Arrays.asList("#EF7771", "#FF261B"));
        this.gun = new Gun(3, 10);
    }

}
