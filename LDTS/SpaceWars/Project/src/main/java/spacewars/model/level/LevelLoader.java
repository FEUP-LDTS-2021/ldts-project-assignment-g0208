package spacewars.model.level;

import spacewars.model.Position;
import spacewars.model.element.Player;
import spacewars.model.element.enemy.DSEnemy;
import spacewars.model.element.enemy.Enemy;
import spacewars.model.element.enemy.TFEnemy;
import spacewars.model.item.Gun;
import spacewars.model.element.enemy.SDEnemy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LevelLoader extends LevelBuilder {
    private final List<String> lines;

    public LevelLoader(int level) throws IOException {
        URL resource = LevelBuilder.class.getResource("/levels/level" + level);
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        this.lines = readLines(br);
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    @Override
    protected Player createPlayer() {
        return new Player(new Position(1, 10), Arrays.asList("#FF00FF","#FF69B4", "#FF4500","#008B8B", "#7B68EE")
                , new Gun(3, 4), 10);
    }

    @Override
    protected List<Enemy> createEnemies(int width, int height) {
        List<Enemy> enemies = new ArrayList<>();
        boolean found = false;
        for (String line : lines) {
            if (!line.isEmpty() && line.charAt(0) == 'E') found = true;

            if (found) {
                if (line.isEmpty() || line.charAt(0) != 'E') break;
                String[] lineArray = line.split(" ");

                Enemy enemy;

                switch (lineArray[0]) {
                    case "EDS":
                        enemy = new SDEnemy();
                        break;
                    case "ESD":
                        enemy = new DSEnemy();
                        break;
                    case "ETF":
                        enemy = new TFEnemy();
                        break;
                    default:
                        continue;

                }

                int damage = 0;
                int speed = 0;
                switch (lineArray[1]) {
                    case "W": {
                        damage = 1;
                        speed = 1;
                        break;
                    }
                    case "M": {
                        damage = 2;
                        speed = 1;
                        break;
                    }
                    case "S": {
                        damage = 3;
                        speed = 1;
                        break;
                    }
                    default:
                        System.out.println("Erro ao determinar tipo de inimigo");
                        break;
                }

                enemy.setPosition(new Position(Integer.parseInt(lineArray[2]),
                        Integer.parseInt(lineArray[3])));

                enemy.setEnergy(Integer.parseInt(lineArray[4]));
                enemy.setGun(new Gun(damage, Integer.parseInt(lineArray[5])));
                enemy.setSpeed(speed);

                enemies.add(enemy);
            }
        }
        return enemies;
    }
}
