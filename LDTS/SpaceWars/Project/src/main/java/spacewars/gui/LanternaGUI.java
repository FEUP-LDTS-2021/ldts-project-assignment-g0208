package spacewars.gui;

import spacewars.model.Position;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;


import static java.awt.Cursor.DEFAULT_CURSOR;

public class LanternaGUI implements GUI {
    private final TerminalScreen screen;
    private final int width;
    private final int height;

    public LanternaGUI(int width, int height) throws IOException, FontFormatException {
        AWTTerminalFontConfiguration fontConfig = loadOverkillFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
        addCloseScreenListener();
        this.height=height;
        this.width=width;
    }

    public LanternaGUI(TerminalScreen screen) {
        this.screen = screen;
        this.width = 20;
        this.height = 20;
    }

    public TerminalScreen createScreen(Terminal terminal) throws IOException {
        final TerminalScreen terminalScreen;
        terminalScreen = new TerminalScreen(terminal);

        terminalScreen.setCursorPosition(null);
        terminalScreen.startScreen();
        terminalScreen.doResizeIfNecessary();
        return terminalScreen;
    }

    public Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {//, AWTTerminalFontConfiguration fontConfig)
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);

        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        ((AWTTerminalFrame)terminal).setCursor(new Cursor(DEFAULT_CURSOR));
        Image image = Toolkit.getDefaultToolkit().getImage("");
        ((AWTTerminalFrame)terminal).setCursor(Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(32,22), "cursor1"));
        return terminal;
    }

    public AWTTerminalFontConfiguration loadOverkillFont() throws FontFormatException, IOException {
        File fontFile = new File("src/main/resources/fonts/square.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    @Override
    public boolean isActive() {
        return ((AWTTerminalFrame) screen.getTerminal()).isDisplayable();
    }

    @Override
    public void drawBullet(Position position, String color, String character) {
        drawText( screen.newTextGraphics() , position, character, color);
    }

    @Override
    public void drawPlayer(Position position, String color) {
        drawText(screen.newTextGraphics() , position, "c", color);
    }

    @Override
    public void drawButton(Position bPos, Position tPos, String text, String bgColor, String textColor, int width, int height){
        TextGraphics textGraphics=screen.newTextGraphics();
        drawRectangle(textGraphics, bgColor, width, height, bPos);
        drawText(textGraphics, tPos, text, textColor);
    }

    @Override
    public void drawTF(Position position, String color) {
        drawText( screen.newTextGraphics() , position, "t", color);
    }

    @Override
    public void drawDS(Position position, String color) {
        drawText( screen.newTextGraphics() , position, "d", color);
    }

    @Override
    public void drawSD(Position position, String color) {
        drawText( screen.newTextGraphics() , position, "s", color);
    }

    @Override
    public void drawTitle(Position position, String text, String color, String colorText) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(color));
        drawText(textGraphics, position, text,colorText);
    }

    public void addKeyBoardListener(KeyBoardObserver obs) {
        ((AWTTerminalFrame) screen.getTerminal()).getComponent(0).addKeyListener(obs);
    }

    public void addMouseListener(MouseObserver obs) {
        ((AWTTerminalFrame) screen.getTerminal()).getComponent(0).addMouseListener(obs);
        ((AWTTerminalFrame) screen.getTerminal()).getComponent(0).addMouseMotionListener(obs);
     }

     private void addCloseScreenListener(){
         ((AWTTerminalFrame) screen.getTerminal()).addWindowListener(new WindowAdapter(){
             @Override
             public void windowClosing(WindowEvent e) {
                 ((AWTTerminalFrame) screen.getTerminal()).dispose();
                 System.exit(0);
             }
         });
     }

    @Override
    public void drawInfo(int energy) {
        TextGraphics textGraphics=screen.newTextGraphics();
        int index = 0;
        drawText(textGraphics,new Position(index,height),String.valueOf(energy) ,"#FFFFFF");
        index += String.valueOf(energy).length();
        drawText(textGraphics,new Position(index,height),"energy" ,"#FF0000");
    }

    @Override
    public TextGraphics createTextGraphics() {
        return screen.newTextGraphics();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void drawRectangle(TextGraphics textGraphics, String color, int width, int height, Position pos){
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(color));
        textGraphics.fillRectangle(new TerminalPosition(pos.getX(), pos.getY()), new TerminalSize(width, height), ' ');
    }

    @Override
    public void drawBackground(TextGraphics textGraphics, String color) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(color));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
    }

    private void drawText(TextGraphics textGraphics, Position position, String text, String color) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString(color));
        textGraphics.enableModifiers(SGR.BORDERED);
        textGraphics.putString(position.getX(),position.getY(),text);
    }


    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}