package at.spengergasse.hbgm.interfaces;

import at.spengergasse.hbgm.entities.Patient;

import java.util.List;

public interface IPatientRepository {

    // Patientenliste anlegen
    List<Patient> patients();

    // Patient zu Repository hinzufügen
    void add(Patient p);

    // Patient aus Repository löschen
    void remove(Patient p);

}
