package GUIModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import operationsverstaerker.Operationsverstärker;
import widerstand.EReihe;
import widerstand.Widerstand;

public class Model {

    private List<ModelObserver> opvObservers;
    private List<ModelObserver> outputObservers;

    private Double ausgangsspannung;
    private Double verstärkung;

    private String selectedOPV;

    private String selectedEReihe;

    private HashMap<String, Widerstand> widerstände;

    public Model() {
        opvObservers = new ArrayList<>();
        outputObservers = new ArrayList<>();
        ausgangsspannung = null;
        verstärkung = null;
        selectedOPV = "Nichtinvertierer";
    }

    public Double getAusgangsspannung() {
        return ausgangsspannung;
    }

    public void setAusgangsspannung(Double newAusgangsspannung) {
        ausgangsspannung = newAusgangsspannung;
        notifyOutputObservers();
    }

    public Double getVerstärkung() {
        return verstärkung;
    }

    public void setVerstärkung(Double newVerstärkung) {
        verstärkung = newVerstärkung;
        notifyOutputObservers();
    }

    public String getSelectedOPV() {
        return selectedOPV;
    }

    public void setSelectedOPV(String newOPV) {
        selectedOPV = newOPV;
        notifyOPVObservers();
    }

    public void addOPVObserver(ModelObserver observer) {
        opvObservers.add(observer);
    }

    public void addOutputObserver(ModelObserver observer) {
        outputObservers.add(observer);
    }

    public String getSelectedEReihe() {
        return selectedEReihe;
    }

    public void setSelectedEReihe(String newEReihe) {
        selectedEReihe = newEReihe;
        switch (selectedEReihe) {
            case "E12":
                Operationsverstärker.setEReihe(EReihe.E12);
                break;
            case "E24":
                Operationsverstärker.setEReihe(EReihe.E24);
                break;
            default:
                Operationsverstärker.setEReihe(null);
                break;

        }
    }

    public HashMap<String, Widerstand> getWiderstände() {
        return widerstände;
    }

    public void setWiderstände(HashMap<String, Widerstand> newWiderstände) {
        widerstände = newWiderstände;
        notifyOutputObservers();
    }

    public void removeOPVObserver(ModelObserver observer) {
        opvObservers.remove(observer);
    }

    public void removeOutputObserver(ModelObserver observer) {
        outputObservers.remove(observer);
    }

    private void notifyOPVObservers() {
        for (ModelObserver observer : opvObservers) {
            observer.update();
        }
    }

    private void notifyOutputObservers() {
        for (ModelObserver observer : outputObservers) {
            observer.update();
        }
    }

}
