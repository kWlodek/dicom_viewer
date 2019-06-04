package at.spengergasse.hbgm.interfaces;

import at.spengergasse.hbgm.entities.Instance;
import at.spengergasse.hbgm.entities.Patient;
import at.spengergasse.hbgm.entities.Series;
import at.spengergasse.hbgm.entities.Study;

public interface IPatientBrowser {

    // übergibt ausgewählten Patienten
    Patient selectedPatient();

    // übergibt ausgewählte Study
    Study selectedStudy();

    // übergibt ausgewählte Series
    Series selectedSeries();

    // übergibt ausgewählte Instance
    Instance selectedInstance();

}
