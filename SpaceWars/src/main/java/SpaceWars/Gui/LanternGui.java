package SpaceWars.Gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;


import java.awt.*;
import java.io.IOException;
import java.util.List;

import static com.googlecode.lanterna.screen.Screen.RefreshType.AUTOMATIC;

public class LanternGui implements GUI {
    private final Screen screen;

    public LanternGui() throws IOException {
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
        while(screen.pollInput()!=null);
        if (keyStroke == null) return PressedKey.OTHER;
        if (keyStroke.getKeyType() == KeyType.Escape) return PressedKey.ESCAPE;
        else if (keyStroke.getKeyType() == KeyType.Character && (keyStroke.getCharacter()=='w' ||keyStroke.getCharacter()=='W')) return PressedKey.UP;
        else if (keyStroke.getKeyType() ==  KeyType.Character && (keyStroke.getCharacter()=='d' ||keyStroke.getCharacter()=='D')) return PressedKey.RIGHT;
        else if (keyStroke.getKeyType() ==  KeyType.Character && (keyStroke.getCharacter()=='s' ||keyStroke.getCharacter()=='S')) return PressedKey.DOWN;
        else if (keyStroke.getKeyType() ==  KeyType.Character && (keyStroke.getCharacter()=='a' ||keyStroke.getCharacter()=='A')) return PressedKey.LEFT;
        else if(keyStroke.getKeyType() == KeyType.Character && (keyStroke.getCharacter()=='l' ||keyStroke.getCharacter()=='L')) return PressedKey.SHOOT;
        else if (keyStroke.getKeyType() ==  KeyType.Enter) return PressedKey.ENTER;
        else return PressedKey.OTHER;
    }

    @Override
    public void background() {
       fill("#D3D3D3");
    }

    public void fill(String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(GlobalConfigs.TERMINAL_WIDTH, GlobalConfigs.TERMINAL_HEIGHT), ' ');
    }

    public void drawMenu() {
        fill("#fffff4");
    }

    public void drawName(List<String> name, String hexColor) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor((TextColor.Factory.fromString("#d8ac00")));
        graphics.setForegroundColor(TextColor.Factory.fromString(hexColor));
        for (int i=0; i<name.size(); i++) {
            if(name.get(i) != " ") {
                graphics.putString(15, GlobalConfigs.TERMINAL_HEIGHT/2+i-50, name.get(i), SGR.BORDERED);
            }
        }
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
