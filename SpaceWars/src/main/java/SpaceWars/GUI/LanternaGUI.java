package SpaceWars.GUI;

import SpaceWars.Model.Position;
import SpaceWars.Model.Menu.Button;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static com.googlecode.lanterna.screen.Screen.RefreshType.AUTOMATIC;

public class LanternaGUI implements GUI {
    private final Screen screen;

    public LanternaGUI() throws IOException {
        Terminal terminal = createTerminal();
        screen = createScreen(terminal);
    }

    public Screen getScreen() {
        return screen;
    }

    private Terminal createTerminal() throws IOException {
        Font font = new Font(GlobalConfigs.LANTERNA_FONT, Font.PLAIN, GlobalConfigs.LANTERNA_FONT_SIZE);
        SwingTerminalFontConfiguration cfg = SwingTerminalFontConfiguration.newInstance(font);
        return new DefaultTerminalFactory().setTerminalEmulatorFontConfiguration(cfg).setInitialTerminalSize(new TerminalSize(GlobalConfigs.TERMINAL_WIDTH, GlobalConfigs.TERMINAL_HEIGHT)).createTerminal();
    }

    private Screen createScreen(Terminal terminal) throws IOException{
        final Screen screen;
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    @Override
    public PressedKey getKeyInput() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        while(screen.pollInput() != null);
        if(keyStroke == null) return PressedKey.OTHER;
        if(keyStroke.getKeyType() == KeyType.Escape) return PressedKey.ESCAPE;
        else if (keyStroke.getKeyType() == KeyType.Character && (keyStroke.getCharacter()=='w' ||keyStroke.getCharacter()=='W')) return PressedKey.UP;
        else if (keyStroke.getKeyType() ==  KeyType.Character && (keyStroke.getCharacter()=='s' ||keyStroke.getCharacter()=='S')) return PressedKey.DOWN;
        else if (keyStroke.getKeyType() ==  KeyType.Enter) return PressedKey.ENTER;
        else return PressedKey.OTHER;
    }

    public void drawButton(Button button) {
        TextGraphics graphics = screen.newTextGraphics();
        if(button.isActive()) {
            graphics.setBackgroundColor(TextColor.Factory.fromString(button.getBackgroundColor()));
        }
        else {
            graphics.setBackgroundColor(TextColor.Factory.fromString(button.getBackgroundDark()));
        }
        graphics.setForegroundColor(TextColor.Factory.fromString(button.getTextColor()));
        drawRectangle(graphics, button.getUpperLeft(), button.getLowerRight());
        int Yset = (button.getUpperLeft().getY() - button.getLowerRight().getY() - button.getButtonText().size())/2;
        int Xset = (button.getLowerRight().getX() - button.getUpperLeft().getX() - button.getButtonText().get(0).length())/2;

        for(int i=0; i < button.getButtonText().size(); i++) {
            if(button.getButtonText().get(i) != " ") {
                graphics.putString(button.getUpperLeft().getX() + Xset,
                        GlobalConfigs.TERMINAL_HEIGHT-button.getUpperLeft().getY() + Yset + i,
                        button.getButtonText().get(i), SGR.BORDERED);
            }
        }
    }

    private void drawRectangle (TextGraphics graphics, Position UpperLeft, Position LowerRight) {
        for(int i = UpperLeft.getX(); i <= LowerRight.getX(); i++) {
            for(int j = GlobalConfigs.TERMINAL_HEIGHT-UpperLeft.getY(); j <= GlobalConfigs.TERMINAL_HEIGHT-LowerRight.getY(); j++) {
                graphics.putString(new TerminalPosition(i, j), " ");
            }
        }
    }

    public void draw(Position position, List<String> appearance, String hexColor, String backgroundColor) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
        graphics.setForegroundColor(TextColor.Factory.fromString(hexColor));
        for(int i=0; i<appearance.size(); i++) {
            if(appearance.get(i) != " ") {
                graphics.putString(position.getX(),
                        GlobalConfigs.TERMINAL_HEIGHT-1- position.getY()+i, appearance.get(i),
                        SGR.BORDERED);
            }
        }
    }

    @Override
    public void drawbackground() {
       fill("#FFFFFF");
    }

    public void drawTitle(List<String> Title, String hexColor) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#D3D3D3"));
        graphics.setForegroundColor(TextColor.Factory.fromString(hexColor));
        for(int i = 0; i<Title.size(); i++) {
            graphics.putString(20, GlobalConfigs.TERMINAL_HEIGHT/2 + i - 50, Title.get(i), SGR.BORDERED);
        }

    }

    public void fill(String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(GlobalConfigs.TERMINAL_WIDTH, GlobalConfigs.TERMINAL_HEIGHT), ' ');
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh(AUTOMATIC);
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
