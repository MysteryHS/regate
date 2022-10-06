package fr.ensicaen.genielogiciel.mvp.model.player;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ScoreTest {
    @Test
    void testGetScore() {
        Score score = new Score();
        Assertions.assertThrows(IOException.class,() -> { score.getScore(4);});
        score.addScore(25469834);
        score.addScore(25469834);
        Assertions.assertThrows(IOException.class,() -> { score.getScore(4);});
    }


    @Test
    void testAddScore() throws IOException {
        Score score = new Score();
        score.addScore(25469834);
        Assertions.assertEquals("7:4:29:834", score.getScore(0));
    }
}