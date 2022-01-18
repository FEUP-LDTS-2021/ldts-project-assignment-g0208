package spacewars.model.level;

import spacewars.model.Position;
import spacewars.model.element.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {
    private LevelLoader bL;
    private Player player;

    @BeforeEach
    void setUp() {
        bL = Mockito.mock(LevelLoader.class);
        player = Mockito.mock(Player.class);
    }

    @Test
    void isEmpty() throws IOException {
        Level b = new LevelLoader(1).createLevel(20, 20);

        assertTrue(b.isEmpty(new Position(20, 20)));
        assertTrue(b.isEmpty(new Position(0, 0)));
    }
}