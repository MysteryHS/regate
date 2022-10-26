package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.map.Buoy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BuoyView extends ImageView {
    private double _caseHeightInPixel;
    private double _caseWidthInPixel;
    private int _x;
    private int _y;



    public BuoyView(double caseWidthInPixel, double caseHeightInPixel, int Xcoordinate, int Ycoordinate) {
        _x = Xcoordinate;
        _y = Ycoordinate;
        _caseHeightInPixel = caseHeightInPixel;
        _caseWidthInPixel = caseWidthInPixel;

        this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/map/flag_red.png"));

    }

    public void draw(AnchorPane pane) {
        this.setLayoutX(_caseWidthInPixel*_x);
        this.setLayoutY(_caseHeightInPixel*_y);

        this.setFitWidth(20);
        this.setFitHeight(20);
        pane.getChildren().add(this);
    }

    public void passed() {
        this.setImage(new Image("file:src/main/resources/fr/ensicaen/genielogiciel/mvp/images/map/flag_red.png"));
    }


}
