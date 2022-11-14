package fr.ensicaen.regate.mvp.view.game;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BuoyView extends ImageView {
    private final double _caseHeightInPixel;
    private final double _caseWidthInPixel;
    private final int _x;
    private final int _y;

    public BuoyView(double caseWidthInPixel, double caseHeightInPixel, int xCoordinate, int yCoordinate) {
        _x = xCoordinate;
        _y = yCoordinate;
        _caseHeightInPixel = caseHeightInPixel;
        _caseWidthInPixel = caseWidthInPixel;
        setImage(new Image("file:src/main/resources/fr/ensicaen/regate/mvp/images/map/flag.png"));
    }

    public void draw(AnchorPane pane) {
        setLayoutX(_caseWidthInPixel*(_x+1));
        setLayoutY(_caseHeightInPixel*(_y+1));
        setFitWidth(_caseWidthInPixel);
        setFitHeight(_caseHeightInPixel);
        pane.getChildren().add(this);
    }

    public void passed() {
        this.setImage(new Image("file:src/main/resources/fr/ensicaen/regate/mvp/images/map/flag.png"));
    }

    public void isNext() {
        this.setImage(new Image("file:src/main/resources/fr/ensicaen/regate/mvp/images/map/flag_red.png"));
    }
}
