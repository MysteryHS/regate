package fr.ensicaen.regate.mvp.view.game;

import javafx.scene.text.Text;

public class WindView {

    public WindView() {
    }

    public void draw( Text textView, String direction, double knot ) {
        textView.setText( "direction:" + direction + "  knot:"+Math.round( knot * 100 ) / 100.0 );
    }
}
