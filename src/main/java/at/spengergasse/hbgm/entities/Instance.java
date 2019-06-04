package at.spengergasse.hbgm.entities;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;
import java.io.IOException;

/**
 * data of an image instance
 */
@Entity
@Table(name = "i_instance")
public class Instance {
    @Id
    @Column(name = "i_id")
    private String sopInstanceUID;

    @Column(name="i_transfersyntax")
    private String transferSyntax;

    @Column(name="i_number")
    private String instanceNumber;

    @Column(name="i_row")
    private int rows;

    @Column(name="i_column")
    private int columns;

    @Column(name="i_maxpixel")
    private short maxValue;

    @Column(name="i_filename")
    private File dicomFile;

    private short minValue;

    public Instance(){}

    public Instance(DicomObject dcm, File dicomFile) throws IOException {
        this.dicomFile = dicomFile;
        // TODO: read all the attribute values from DICOM object
        sopInstanceUID = dcm.getString(Tag.SOPInstanceUID);
        transferSyntax = dcm.getString(Tag.TransferSyntaxUID);
        instanceNumber = dcm.getString(Tag.InstanceNumber);
        rows = dcm.getInt(Tag.Rows);
        columns = dcm.getInt(Tag.Columns);
        minValue = (short)dcm.getInt(Tag.SmallestImagePixelValue);
        maxValue = (short)dcm.getInt(Tag.LargestImagePixelValue);
    }


    /*@Override
    public String toString() {
        return "Instance{" +
                "sopInstanceUID='" + sopInstanceUID + '\'' +
                ", dicomFile=" + dicomFile +
                ", rows=" + rows +
                ", columns=" + columns +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Instance instance = (Instance) o;

        return sopInstanceUID != null ? sopInstanceUID.equals(instance.sopInstanceUID) : instance.sopInstanceUID == null;
    }

    @Override
    public int hashCode() {
        return sopInstanceUID != null ? sopInstanceUID.hashCode() : 0;
    }



    public String getSopInstanceUID() {
        return sopInstanceUID;
    }

    public File getDicomFile() {
        return dicomFile;
    }

    public short[] getPixelValues() throws IOException {
        try(DicomInputStream inp = new DicomInputStream(dicomFile)) {
            DicomObject dcm = inp.readDicomObject();
            return dcm.getShorts(Tag.PixelData);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public short getMinValue() {
        return minValue;
    }

    public short getMaxValue() {
        return maxValue;
    }
}
