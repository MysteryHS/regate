package fr.ensicaen.genielogiciel.mvp.view.game;

import fr.ensicaen.genielogiciel.mvp.model.map.wind.WeatherStation;
import javafx.scene.text.Text;


public class WindView {
    private final WeatherStation _weatherStationModel;
    private Text _windView;

    public WindView(WeatherStation weatherStation, Text windView) {
        _weatherStationModel = weatherStation;
        _windView = windView;
    }

    public void draw() {
        _windView.setText("direction:"+ _weatherStationModel.getWindDirection().name()+"  knot:"+ _weatherStationModel.getSpeedWindInKnot());
    }
}
