package spacewars.model.level;

import org.junit.jupiter.api.Test;

class LevelBuilderTest {
    LevelBuilder LevelBuilder;

    @Test
    void createLevel() {
    }

    /*@Test
    void createPlayer() {
        Player player = LevelBuilder.createLevel(25,25).getPlayer();

        assertEquals(new Position(4,4), player.getPosition());
        assertEquals("#FFFFFF", player.getColor());
        assertEquals(1,player.getSpeed());
        assertEquals(3,player.getEnergy());
    }
*/
    /*@Test
    void createWalls() throws IOException {
        LevelBuilder LevelBuilder = Mockito.mock(LevelBuilder.class);

        LanternaGUI gui = Mockito.mock(LanternaGUI.class);

        LevelLoader LevelLoader = new LevelLoader(1);
        LevelLoader.createLevel(25,25);
        Mockito.verify(LevelBuilder,Mockito.times(1)).createWalls(25,25);
    }*/
}