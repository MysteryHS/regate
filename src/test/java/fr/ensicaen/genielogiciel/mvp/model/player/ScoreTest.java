package fr.ensicaen.genielogiciel.mvp.model.player;

import fr.ensicaen.genielogiciel.mvp.model.Chrono;
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

    @Test
    void testAddScoreFromChrono() throws IOException {
        Score score = new Score();
        score.registerScore();
        Assertions.assertEquals("0:0:0:0", score.getScore(0));
    }

    @Test
    void testResetScore() throws InterruptedException {
        Score score = new Score();
        score.registerScore();
        Thread.sleep(1000);
        score.registerScore();
        score.resetScore();
        Assertions.assertEquals(0, score.getSizeOfTheScore());
    }

    @Test
    void testAddScoreFromChronoAfterARestartReferenceStart() throws IOException, InterruptedException {
        Score score = new Score();
        Chrono chrono = Chrono.getInstance();
        chrono.restartReferenceTime();
        score.registerScore();
        Assertions.assertEquals("0:0:0:0", score.getScore(0));
        Thread.sleep(1000);
        chrono.restartReferenceTime();
        score.registerScore();
        Assertions.assertEquals("0:0:0:0", score.getScore(1));
    }
}