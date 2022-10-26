package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.Wind;
import fr.ensicaen.genielogiciel.mvp.model.map.wind.WindDirection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class WindView {

    public WindView() {
    }

    public void draw(Text textView, String direction, double knot) {
        textView.setText("direction:"+direction+"  knot:"+knot);

    }
}
