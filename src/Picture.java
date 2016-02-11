import java.awt.*;
import java.util.ArrayList;

/**
 * Created by james on 07/02/16.
 * A combination of compositions which should be active simultaneously.
 */
public class Picture extends ArrayList<Composition>{

    Picture(Color color){
        makeAll(color);
    }
    Picture(Composition comp){
        makeAll(comp);
    }
    Picture(){

    }
    Picture(int r, int g, int b){
        makeAll(r, g, b);
    }

    void makeAll(Composition comp){
        //TODO.. does weird stuff: first time sending: only to 14
        for(int i = 0; i < Config.NUMBER_OF_BARS; i++){
            add(new Composition(comp));
            get(i).setAddressByte(i); //address might not be i for every config
        }
    }
    void makeAll(Color color){
        makeAll(new Composition(color));
    }
    void makeAll(int r, int  g, int b){
        makeAll(new Composition(r, g, b));
    }

}
