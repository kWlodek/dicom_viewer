package at.spengergasse.hbgm.entities;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * contains informations of series and a list of images
 */
@Entity
@Table(name="se_series")
public class Series {
    @Id
    @Column(name="se_id")
    private String seriesInstanceUID;

    @Column(name="se_desc")
    private String seriesDescription;

    @Temporal(TemporalType.DATE)
    @Column(name = "se_datum")
    private Date acquisitionTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "se_id")
    private List<Instance> instanceList = new ArrayList<Instance>();


    public Series(){}

    public Series(DicomObject dcm){
        // TODO: read all the attribute values from DICOM object
        seriesInstanceUID = dcm.getString(Tag.SeriesInstanceUID);
        acquisitionTime = dcm.getDate(Tag.AcquisitionDateTime);
        seriesDescription = dcm.getString(Tag.SeriesDescription);
    }

    public Instance findInstance(Instance instance) {
        for (Instance i: instanceList){
            if(instance.getSopInstanceUID().equals(i.getSopInstanceUID())) {
                return i;
            }
        }
        return instance;
    }

    /*@Override
    public String toString() {
        return "Series{" +
                "seriesInstanceUID='" + seriesInstanceUID + '\'' +
                ", acquisitionTime=" + acquisitionTime +
                '}';
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Series series = (Series) o;

        return seriesInstanceUID != null ? seriesInstanceUID.equals(series.seriesInstanceUID) : series.seriesInstanceUID == null;
    }

    @Override
    public int hashCode() {
        return seriesInstanceUID != null ? seriesInstanceUID.hashCode() : 0;
    }

    public Instance add(Instance image){
        int inx = instanceList.indexOf(image);
        if (inx >= 0)
            return instanceList.get(inx);
        instanceList.add(image);
        return image;
    }

    public String getSeriesInstanceUID() {
        return seriesInstanceUID;
    }

    public Date getAcquisitionTime() {
        return acquisitionTime;
    }

    public List<Instance> getInstanceList() {
        return instanceList;
    }
}
