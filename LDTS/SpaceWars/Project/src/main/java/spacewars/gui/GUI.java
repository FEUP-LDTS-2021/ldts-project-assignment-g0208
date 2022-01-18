package spacewars.gui;

import spacewars.model.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public interface GUI {

    enum ACTION {UP, DOWN, FIRE, QUIT}

    TextGraphics createTextGraphics();

    int getWidth();

    int getHeight();

    void drawRectangle(TextGraphics textGraphics, String color, int width, int height, Position pos);

    void drawBackground(TextGraphics textGraphics, String color);

    void clear() throws IOException;

    void refresh() throws IOException;

    void close() throws IOException;

    void addMouseListener(MouseObserver obs);

    void addKeyBoardListener(KeyBoardObserver obs);

    void drawInfo(int energy);

    boolean isActive();

    void drawBullet(Position position, String elementColor, String character);

    void drawPlayer(Position position, String color);

    void drawButton(Position bPos, Position tPos, String text, String bgColor, String textColor, int width, int height);

    void drawTF(Position position, String color);

    void drawDS(Position position, String color);

    void drawSD(Position position, String color);

    void drawTitle(Position position, String text, String color, String colorText);
}
