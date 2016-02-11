import java.awt.*;

/**
 * Created by james on 07/02/16.
 *
 * turns out this class is kind of redundant.. :P
 */
public class RgbColor extends Color{

    RgbColor(int a, int b, int c){
        super(a, b, c);
    }
    RgbColor(){
        super(255, 255, 255);
    }

    public byte[] getBytes(){
        byte[] ret = new byte[3];
        ret[0] = (byte) getRed();
        ret[1] = (byte) getGreen();
        ret[2] = (byte) getBlue();
        return ret;
    }

}
