package SpaceWars.GUI;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;

import static com.googlecode.lanterna.screen.Screen.RefreshType.AUTOMATIC;

public class LanternGUI implements GUI {
    private final Screen screen;

    public LanternGUI() throws IOException {
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
    public void background() {
       fill("#D3D3D3");
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
