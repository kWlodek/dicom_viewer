package at.spengergasse.hbgm.interfaces;

import at.spengergasse.hbgm.entities.Instance;

import java.awt.image.BufferedImage;

public interface IPixelMapper {

    // übergibt den Pixeln die Werte
    BufferedImage map(Instance i);

}
