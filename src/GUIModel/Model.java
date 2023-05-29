package GUIModel;

import java.util.ArrayList;
import java.util.List;

import operationsverstaerker.NichtInvertierer;

public class Model {

    private List<ModelObserver> observers;

    private Double ausgangsspannung;
    private Double verstärkung;

    private String selectedOPV;

    private String selectedEReihe;

    public Model() {
        observers = new ArrayList<>();
        ausgangsspannung = null;
        verstärkung = null;
        selectedOPV = "Nichtinvertierer";
    }

    public Double getAusgangsspannung() {
        return ausgangsspannung;
    }

    public void setAusgangsspannung(Double newAusgangsspannung) {
        ausgangsspannung = newAusgangsspannung;
        notifyObservers();
    }

    public Double getVerstärkung() {
        return verstärkung;
    }

    public void setVerstärkung(Double newVerstärkung) {
        verstärkung = newVerstärkung;
        notifyObservers();
    }

    public String getSelectedOPV() {
        return selectedOPV;
    }

    public void setSelectedOPV(String newOPV) {
        selectedOPV = newOPV;
        notifyObservers();
    }

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    public String getSelectedEReihe() {
        return selectedEReihe;
    }

    public void setSelectedEReihe(String newEReihe) {
        selectedEReihe = newEReihe;
    }

    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.update();
        }
    }

    public void calculate() {
        switch(selectedOPV) {
            case "Nichtinvertierer":
                calculateNichtinvertierer();
                break;
            case "Invertierer":
                calculateInvertierer();
                break;
            case "Subtrahierer":
                calculateSubtrahierer();
                break;
            case "Summierer":
                calculateSummierer();
                break;
            default:
            ausgangsspannung = null;
            verstärkung = null;
            break;
        }
    }

    private void calculateNichtinvertierer() {

        Double eingangsspannung = 

        NichtInvertierer opv = new NichtInvertierer(0, 0, 0)
    }
}

