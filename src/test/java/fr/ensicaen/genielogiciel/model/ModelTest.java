package fr.ensicaen.genielogiciel.model;

import fr.ensicaen.genielogiciel.mvp.model.PlayerModel;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WindProxy;
import fr.ensicaen.genielogiciel.mvp.model.ship.ShipModel;
import fr.ensicaen.genielogiciel.mvp.model.ship.crew.NormalCrew;
import fr.ensicaen.genielogiciel.mvp.model.ship.sail.NormalSail;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @ParameterizedTest
    @MethodSource("usernames")
    void should_get_nickname_when_set_nickname( String name ) {
        // TODO This test is of no interest. It only shows the use of a parameterized test

        ShipModel ship = new ShipModel(new NormalSail(), new NormalCrew(),new WindProxy(0., 0.), "polaire-figaro.pol");
        PlayerModel loginModel = new PlayerModel(name, ship);
        assertEquals(name, loginModel.getNickname());
    }

    public static Stream<Arguments> usernames() {
        return Stream.of(
                Arguments.of("Toto"),
                Arguments.of("Un nom avec espace")
        );
    }
}