package spacewars.state.listener;

import spacewars.model.Position;

import java.io.IOException;

public interface MouseListener {
    void click(Position position) throws IOException;
    void move(Position position);


     default Position getRealPosition(Position position, int width, int height) {
        return new Position(position.getX()/width, position.getY()/height);
    }

    default Boolean buttonPosition(Position cPos, Position bPos, int width, int height){
        if (cPos.getX() >= bPos.getX() && cPos.getX() < bPos.getX() + width){
            return (cPos.getY() >= bPos.getY() && cPos.getY() < bPos.getY() + height);
        }
        return false;
    }
}
