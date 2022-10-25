package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.Wind; // FIXME ne respecte pas l'architecture MVP couplage vue - modele
import javafx.scene.image.Image; // FIXME jamais utilisé...
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class WindView {
    private Wind _windModel; // FIXME attributs final
    private Text _windView;

    public WindView(Wind wind,Text windView) {
        _windModel = wind;
        _windView = windView;
    }

    public void draw() {
        _windView.setText("direction:"+_windModel.getWindDirection().name()+"  knot:"+_windModel.getWindKnot());
    }
}
