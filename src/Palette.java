import java.awt.*;
import java.util.ArrayList;

/**
 * Created by james on 06/02/16.
 * 2 functionalities: - statically: deliver pre defined colors
 *                    - as object: store a list of defined colors
 */
public class Palette extends ArrayList<Color>{
    

    void makeBasic(){
        clear();
        add(Color.RED);
        add(Color.GREEN);
        add(Color.BLUE);
        add(Color.PINK);
        add(Color.YELLOW);
        add(Color.WHITE);
        add(Color.BLACK);
    }
    void makeSpectrum(){ //// 1000 entries
        clear();
        int numberOfColors = 1000;
        int d = 1/numberOfColors;
        for(float i = 0; i < 1; i+=d)
        {
            add( Color.getHSBColor(i, (float) 0.5,(float) 0.5));
        }
    }

    void makeSomething(){
        clear();
        for(int i = 0; i < 255; i+= 6){
                for(int k = 0; k < 255; k+= 6){
                    add(new RgbColor(i, i, k));
                }
            }
    }

    @Override
    public int size() {
        return super.size();
    }


}
