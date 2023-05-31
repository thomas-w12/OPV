package exceptions;

/**
 * Exception, wenn die Farbringe des Widerstandes nicht verfügbar sind
 * @author Thomas Wegele
 */
public class ResistorColorsNotAvailableException extends Exception {
    public ResistorColorsNotAvailableException(String message) {
        super(message);
    }
}