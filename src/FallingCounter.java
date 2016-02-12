import java.awt.*;
import java.util.Collection;

/**
 * Created by james on 12/02/16.
 */
public class FallingCounter {

    FallingCounter(Color backColor, Color pointColor, int barID, int delta){
        height = 0;
        this.barID = barID;
        this.delta = delta;
        this.backColor = backColor;
        this.pointColor = pointColor;
        currentComposition = new Composition(backColor);
        currentComposition.setAddressByte(barID);
    }

    int barID;

    int delta; //not used yet. could be important later for asynchronous use.

    Color backColor;

    Color pointColor;

    int height;

    Composition currentComposition;

    Composition insert(){
        currentComposition.setPoint(1, pointColor);

        return new Composition(currentComposition);
    }
    Composition fall(){
        //TODO: point falls out.. why?
        if(full()){
            return currentComposition;
        }
        if(currentComposition.getPoint(122-height).equals(pointColor)){
            height++;
        }
        currentComposition.shiftRight();
        currentComposition.setPoint(1, backColor);
        for(int i = 0; i < height; i++){
            currentComposition.setPoint(122-i, pointColor);
        }
        return new Composition(currentComposition);
    }
    boolean full(){
        return height == 122;
    }

}
