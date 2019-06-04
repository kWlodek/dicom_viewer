package at.spengergasse.hbgm.entities;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * contains data of the study and a list of series
 */

@Entity
@Table(name="s_study")
public class Study {
    @Id
    @Column(name="s_id")
    private String studyInstanceUID;

    @Column(name="s_desc")
    private String studyDescription;

    @Temporal(TemporalType.DATE)
    @Column(name = "s_datum")
    private Date studyDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "s_id")
    private List<Series> seriesList = new ArrayList<Series>();

    public Study(){}

    public Study(DicomObject dcm){
        // TODO: read all the attribute values from DICOM object
        studyInstanceUID = dcm.getString(Tag.StudyInstanceUID);
        studyDate = dcm.getDate(Tag.StudyDate);
        studyDescription = dcm.getString(Tag.StudyDescription);
    }

    public Series findSeries(Series series) {
        for (Series s: seriesList){
            if(series.getSeriesInstanceUID().equals(s.getSeriesInstanceUID())) {
                return s;
            }
        }
        return series;
    }

   /* @Override
    public String toString() {
        return "Study{" +
                "studyInstanceUID='" + studyInstanceUID + '\'' +
                ", studyDate=" + studyDate +
                '}';
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Study study = (Study) o;

        return studyInstanceUID != null ? studyInstanceUID.equals(study.studyInstanceUID) : study.studyInstanceUID == null;
    }

    @Override
    public int hashCode() {
        return studyInstanceUID != null ? studyInstanceUID.hashCode() : 0;
    }

    public Series add(Series series) {
        int inx = seriesList.indexOf(series);
        if (inx >= 0)
            return seriesList.get(inx);
        seriesList.add(series);
        return series;
    }

    public String getStudyInstanceUID() {
        return studyInstanceUID;
    }

    public Date getStudyDate() {
        return studyDate;
    }

    public List<Series> getSeriesList() {
        return seriesList;
    }
}
