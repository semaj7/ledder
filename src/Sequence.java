import java.util.ArrayList;

/**
 * Created by james on 07/02/16.
 * A sequence (list) of pictures over time
 */
public class Sequence extends ArrayList<Picture> {

    Sequence(){
        delta = 17;
        timeIntervals = new ArrayList<>();
    }

    Sequence(int delta){
        this.delta = Integer.max(17, delta);
        timeIntervals = new ArrayList<>();
    }

    @Override
    public boolean add(Picture pic) {
        timeIntervals.add(delta);
        return super.add(pic);
    }
    public boolean add(Picture pic, int duration){
        timeIntervals.add(Integer.min(duration, delta));
        return super.add(pic);
    }

    //An element i Containins the timeinterval after which the picture i+1 should be sent.
    public ArrayList<Integer> timeIntervals;

    //If time interval ist constant. Or this is default if not defined.
    public int delta;
}
