package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.Wind;
import javafx.scene.text.Text;

public class WindView extends Text {
    private Wind _wind;

    public WindView(Wind wind) {
        _wind = wind;
    }
}
