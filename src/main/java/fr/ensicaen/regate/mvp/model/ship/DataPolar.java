package fr.ensicaen.regate.mvp.model.ship;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataPolar {
    private double[][] _polarValues;
    private int _minKnot;
    private int _maxKnot;
    private final int _minAngle;
    private final int _maxAngle;
    private final File _file;
    private final String _strDataPolarFromResourcesDirectory;

    public DataPolar( String strDataPolarFromResourcesDirectory ) throws FileNotFoundException {
        _strDataPolarFromResourcesDirectory = strDataPolarFromResourcesDirectory;
        String workspacePolarResources = "src/main/resources/fr/ensicaen/regate/mvp/datapolar/";
        _file = new File(workspacePolarResources + strDataPolarFromResourcesDirectory);
        _minAngle = 0;
        _maxAngle = 180;
        readPolarValues();
    }

    public double getPolarValues(double angle, double knot) {
        double approximateAngleIndex = getApproximateAngleIndex(angle);
        double approximateKnotIndex = getApproximateKnotIndex(knot);
        double polarValuesInf =
                _polarValues[(int)Math.floor(approximateKnotIndex)][(int)Math.floor(approximateAngleIndex)];
        double polarValuesSupp =
                _polarValues[(int)Math.ceil(approximateKnotIndex)][(int)Math.ceil(approximateAngleIndex)];
        return (polarValuesSupp-polarValuesInf) / 2. + polarValuesInf;
    }

    private double getApproximateKnotIndex(double knot) {
        return (knot - (double)_minKnot) * ((double)_polarValues.length - 1) / ((double)_maxKnot - (double)_minKnot);
    }

    private double getApproximateAngleIndex(double angle) {
        return ((angle - (double)_minAngle) * ((double)_polarValues[0].length - 1.)) /
                ((double)_maxAngle - (double)_minAngle);
    }

    private void readPolarValues() throws FileNotFoundException {
        Scanner input = new Scanner(_file);
        String[] strTab = input.nextLine().split("\\t");
        initializeData(strTab);
        int angleIndex = 0;
        while (input.hasNextLine()) {
            int knotIndex = 0;
            strTab = input.nextLine().split("\\t");
            insertLineToPolarValues(strTab, angleIndex, knotIndex);
            angleIndex++;
        }
    }

    private void initializeData(String[] strTab) {
        _minKnot = Integer.parseInt(strTab[1]);
        _maxKnot = Integer.parseInt(strTab[strTab.length-1]);
        _polarValues = new double[strTab.length-1][(_maxAngle-_minAngle)/10 + 1];
    }

    private void insertLineToPolarValues(String[] strTab, int angleIndex, int knotIndex) {
        for ( String str : strTab) {
            if (!isFirstColumn(knotIndex)) {
                _polarValues[knotIndex -1][angleIndex] = Double.parseDouble(str);
            }
            knotIndex++;
        }
    }

    private static boolean isFirstColumn(int knotIndex) {
        return knotIndex == 0;
    }

    public String getPolarName() {
        return _strDataPolarFromResourcesDirectory;
    }
}
