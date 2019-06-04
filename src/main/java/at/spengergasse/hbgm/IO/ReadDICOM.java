package at.spengergasse.hbgm.IO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadDICOM {
    public List<File> findDicomFiles(File dir){
        List<File> files = new ArrayList<>();
        for(File f : dir.listFiles()){
            if (f.isDirectory())
                files.addAll(findDicomFiles(f));
            else{
                if (isDicom(f))
                    files.add(f);
            }
        }
        return files;
    }

    protected boolean isDicom(File f) {
        return f.getName().toLowerCase().endsWith(".dcm");
    }
}
