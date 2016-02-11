import java.util.ArrayList;

/**
 * Created by james on 07/02/16.
 * A sequence (list) of pictures over time
 */
public class Sequence extends ArrayList<Picture> {

    //An element i Containins the timeinterval after which the picture i+1 should be sent.
    public int[] timeIntervals;
}
