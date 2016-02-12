
import java.util.ArrayList;

/**
 * Created by james on 08/02/16.
 * Specific setup for the VIS/AMIV aufenthaltsraum
 *  Horizontal bars are arranged in a circular linked list (with 'next')
 *  in such a way that they form a circle in the room.
 */
public class Setup {


    public Setup(){
        setupBars();
    }


    // the position in the array is also the id of the bar
    public ArrayList<LedBar> bars = new ArrayList<LedBar>();


   public ArrayList<Integer> verticals = new ArrayList<>();
    //Inside the bars, the last LED is on the entrance side/faces upwards


     void setupBars() {

                     //      ID, Horiz, Left
        bars.add(new LedBar(0, false, true));
        bars.add(new LedBar(1, true, true));
        bars.add(new LedBar(2, false, false));
        bars.add(new LedBar(3, false, true));
        bars.add(new LedBar(4, true, true));
        bars.add(new LedBar(5, true, false));
        bars.add(new LedBar(6, true, false));
        bars.add(new LedBar(7, true, false));
        bars.add(new LedBar(8, true, false));
        bars.add(new LedBar(9, true, true));
        bars.add(new LedBar(10, false, true));
        bars.add(new LedBar(11, false, false));
        bars.add(new LedBar(12, true, true));
        bars.add(new LedBar(13, false, false));
        bars.add(new LedBar(14, false, true));

                           // hnext, hprev, vbro
        bars.get(0).setLinks(null, null, bars.get(4));
        bars.get(1).setLinks(bars.get(4), bars.get(8), bars.get(3));
        bars.get(2).setLinks(null, null, bars.get(5));
        bars.get(3).setLinks(null, null, bars.get(1));
        bars.get(4).setLinks(bars.get(9), bars.get(1), bars.get(0));
        bars.get(5).setLinks(bars.get(6), bars.get(12), bars.get(2));
        bars.get(6).setLinks(bars.get(7), bars.get(5), bars.get(11));
        bars.get(7).setLinks(bars.get(8), bars.get(6), bars.get(13));
        bars.get(8).setLinks(bars.get(1), bars.get(7), null); //Only one that hasn't got a vertical 'brother'
        bars.get(9).setLinks(bars.get(12), bars.get(4), bars.get(14));
        bars.get(10).setLinks(null, null, bars.get(12));
        bars.get(11).setLinks(null, null, bars.get(6));
        bars.get(12).setLinks(bars.get(5), bars.get(9), bars.get(10));
        bars.get(13).setLinks(null, null, bars.get(7));
        bars.get(14).setLinks(null, null, bars.get(9));

        verticals.add(0);
        verticals.add(2);
        verticals.add(3);
        verticals.add(10);
        verticals.add(11);
        verticals.add(13);
        verticals.add(14);
     }

}
