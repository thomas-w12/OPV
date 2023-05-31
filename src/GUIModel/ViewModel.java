package GUIModel;

import operationsverstaerker.Operationsverstärker;
import widerstand.EReihe;
import widerstand.Widerstand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ViewModel der Anwendung, ruft die update-Methoden der zugehörigen Observer
 * auf, wenn sich der Zustand ändert
 * 
 * @author Thomas Wegele
 */
public class ViewModel {

    private List<ModelObserver> opvObservers;
    private List<ModelObserver> outputObservers;

    private Double ausgangsspannung;
    private Double verstärkung;

    private String selectedOPV;

    private String selectedEReihe;

    private HashMap<String, Widerstand> widerstände;

    /**
     * Konstruktor des ViewModels, setzt die Ausgangsspannung und Verstärkung auf
     * null und den OPV auf Nichtinvertierer
     */
    public ViewModel() {
        opvObservers = new ArrayList<>();
        outputObservers = new ArrayList<>();
        ausgangsspannung = null;
        verstärkung = null;
        selectedOPV = "Nichtinvertierer";
    }

    /**
     * Methode zum Erhalt der momentan gespeicherten Ausgangsspannung im ViewModel
     * 
     * @return Ausgangsspannung
     */
    public Double getAusgangsspannung() {
        return ausgangsspannung;
    }

    /**
     * Methode zum Setzen der Ausgangsspannung im ViewModel
     * 
     * @param newAusgangsspannung Ausgangsspannung
     */
    public void setAusgangsspannung(Double newAusgangsspannung) {
        ausgangsspannung = newAusgangsspannung;
        notifyOutputObservers();
    }

    /**
     * Methode zum Erhalt der momentan gespeicherten Verstärkung im ViewModel
     * 
     * @return Verstärkung
     */
    public Double getVerstärkung() {
        return verstärkung;
    }

    /**
     * Methode zum Setzen der gespeicherten Verstärkung im ViewModel
     * 
     * @param newVerstärkung Verstärkung
     */
    public void setVerstärkung(Double newVerstärkung) {
        verstärkung = newVerstärkung;
        notifyOutputObservers();
    }

    /**
     * Methode zum Erhalt des momentan gespeicherten Operationsverstärkers im
     * ViewModel
     * 
     * @return ausgewählter Operationsverstärker
     */
    public String getSelectedOPV() {
        return selectedOPV;
    }

    /**
     * Methode zum Setzen des ausgewählten Operationsverstärkers im ViewModel
     * 
     * @param newOPV Operationsverstärker
     */
    public void setSelectedOPV(String newOPV) {
        selectedOPV = newOPV;
        notifyOPVObservers();
    }

    /**
     * Methode zum Erhalt der momentan gespeicherten E-Reihe im ViewModel
     * 
     * @return E-Reihe
     */
    public String getSelectedEReihe() {
        return selectedEReihe;
    }

    /**
     * Methode zum Setzen der E-Reihe im ViewModel.
     * Gleichzeitig wird auch die entsprechende E-Reihe in der Klasse
     * Operationsverstärker gesetzt.
     * 
     * @param newEReihe E-Reihe
     */
    public void setSelectedEReihe(String newEReihe) {
        selectedEReihe = newEReihe;
        switch (selectedEReihe) {
            case "E12":
                Operationsverstärker.setEReihe(EReihe.E12);
                break;
            case "E24":
                Operationsverstärker.setEReihe(EReihe.E24);
                break;
            case "E48":
                Operationsverstärker.setEReihe(EReihe.E48);
                break;
            case "E96":
                Operationsverstärker.setEReihe(EReihe.E96);
                break;
            case "E192":
                Operationsverstärker.setEReihe(EReihe.E192);
                break;
            default:
                Operationsverstärker.setEReihe(null);
                break;

        }
    }

    /**
     * Methode zum Erhalt der momentan gespeicherten Widerstände im ViewModel
     * 
     * @return Widerstände als String-Widerstands HashMap
     */
    public HashMap<String, Widerstand> getWiderstände() {
        return widerstände;
    }

    /**
     * Methode zum Setzen der Widerstände des ViewModels
     * 
     * @param newWiderstände Widerstände
     */
    public void setWiderstände(HashMap<String, Widerstand> newWiderstände) {
        widerstände = newWiderstände;
        notifyOutputObservers();
    }

    /**
     * Methode zum Hinzufügen eines Observers, welcher bei Änderung des
     * Operationsverstärkers aktualisiert wird
     * 
     * @param observer Observer
     */
    public void addOPVObserver(ModelObserver observer) {
        opvObservers.add(observer);
    }

    /**
     * Methode zum Hinzufügen eines Observers, welcher bei Änderung der
     * Ausgansparameter aktualisiert wird
     * 
     * @param observer Observer
     */
    public void addOutputObserver(ModelObserver observer) {
        outputObservers.add(observer);
    }

    /**
     * Methode zum Entfernen eines Observers des Operationsverstärkers
     * 
     * @param observer Observer
     */
    public void removeOPVObserver(ModelObserver observer) {
        opvObservers.remove(observer);
    }

    /**
     * Methode zum Entfernen eines Observers der Ausgangsparameter
     * 
     * @param observer Observer
     */
    public void removeOutputObserver(ModelObserver observer) {
        outputObservers.remove(observer);
    }

    /**
     * Methode updatet Observer des Operationsverstärkers
     */
    private void notifyOPVObservers() {
        for (ModelObserver observer : opvObservers) {
            observer.update();
        }
    }

    /**
     * Methode updatet Observer der Ausgangsparameter
     */
    private void notifyOutputObservers() {
        for (ModelObserver observer : outputObservers) {
            observer.update();
        }
    }

}
