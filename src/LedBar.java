import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by james on 08/02/16.
 */
public class LedBar {

    //Positioning
    int id;
    boolean horizontal;
    boolean leftRow; //true for bars 1 4 9 12 3 0 14 10

    LedBar hnext; //Horizontal chain of 'next' creates a circle in negative angle
    LedBar hprevious;
    LedBar vbro; //The vertical/horizontal 'brother'

    void setLinks(LedBar hnxt, LedBar hprev, LedBar vbro){
        hnext = hnxt;
        hprevious = hprev;
        this.vbro = vbro;
    }

    LedBar(int id , boolean horizontal, boolean leftRow){
        this.id = id;
        this.horizontal = horizontal;
        this.leftRow = leftRow;

    }
}
