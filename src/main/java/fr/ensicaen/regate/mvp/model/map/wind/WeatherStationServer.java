package fr.ensicaen.regate.mvp.model.map.wind;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class WeatherStationServer implements WeatherStation {
    private final double _longitude;
    private final double _latitude;
    private final WindData _windData;

    public WeatherStationServer(double longitude, double latitude) throws IOException {
        _longitude = longitude;
        _latitude = latitude;
        _windData = queryWindDataFromJSON();
    }

    protected WindData queryWindDataFromJSON( ) throws IOException {
        String strUrl = "https://www.prevision-meteo.ch/services/json/lat="+_latitude+"lng="+_longitude;
        URL url = new URL(strUrl);

        Scanner scanner = new Scanner(url.openStream());
        StringBuilder result = new StringBuilder();
        while (scanner.hasNext() ) {
            result.append(scanner.nextLine());
        }
        scanner.close();

        JSONObject jason = new JSONObject(result.toString());
        JSONObject objJason = jason.getJSONObject("current_condition");
        return new WindData(stringToWindDirection(objJason.getString("wnd_dir")), kilometersToKnot(objJason.getDouble("wnd_spd")));
    }

    protected double kilometersToKnot( double kmSpeed ) {
        return kmSpeed / 1.852;
    }

    protected WindDirection stringToWindDirection( String stringDirection ) {
        switch (stringDirection) {
            case "N":
                return WindDirection.NORTH;
            case "NE":
                return WindDirection.NORTH_EAST;
            case "E":
                return WindDirection.EAST;
            case "SE":
                return WindDirection.SOUTH_EAST;
            case "S":
                return WindDirection.SOUTH;
            case "SO":
                return WindDirection.SOUTH_WEST;
            case "O":
                return WindDirection.WEST;
            case "NO":
                return WindDirection.NORTH_WEST;
            default:
                break;
        }
        return null;
    }

    @Override
    public double getSpeedWindInKnot() {
        return _windData.getSpeedWindInKnot();
    }

    @Override
    public WindDirection getWindDirection() {
        return _windData.getDirection();
    }
}
