package at.spengergasse.hbgm;

import at.spengergasse.hbgm.entities.*;
import at.spengergasse.hbgm.IO.ReadDICOM;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.io.DicomInputStream;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;

public class App {
    public static final File filepath = new File("images");
    public static void main(String[] args) throws Exception{

        EntityManager em = Persistence
                .createEntityManagerFactory("viewer")
                .createEntityManager();

        ReadDICOM reader = new ReadDICOM();

        for(File f : reader.findDicomFiles(filepath)) {
            DicomInputStream inputStream = new DicomInputStream(f);
            DicomObject dicomObject = inputStream.readDicomObject();

            Patient patient = new Patient(dicomObject);
            Study study = new Study(dicomObject);
            Series series = new Series(dicomObject);
            Instance instance = new Instance(dicomObject, filepath);

            if(em.find(Patient.class, patient.getPatientID()) == null) {
                study = patient.findStudy(study);
                series = study.findSeries(series);
                instance = series.findInstance(instance);

                patient.add(study);
                study.add(series);
                series.add(instance);
            } else {
                patient = em.find(Patient.class, patient.getPatientID());
                study = patient.findStudy(study);
                series = study.findSeries(series);
                instance = series.findInstance(instance);

                patient.add(study);
                study.add(series);
                series.add(instance);
            }

            em.persist(patient);
            em.getTransaction().begin();
            em.getTransaction().commit();
        }

    }
}
