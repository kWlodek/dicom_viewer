package at.spengergasse.hbgm.interfaces;

public interface IControlPanel {

    // ermittelt die Mitte eines Bildes
    int getCenter();

    // ermittelt die Breite eines Bildes
    int getWidth();

    // ermittelt den Aplha-Wert (Intensität / Kontrast) einesBildes
    int getAlpha();

}
