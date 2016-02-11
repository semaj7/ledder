import org.jcp.xml.dsig.internal.dom.DOMPGPData;

import javax.security.auth.login.Configuration;
import java.awt.*;

/**
 * Created by james on 06/02/16.
 * 
 */
public class Painter {

    Palette currentPalette;
    Sender s;
    Setup setup;


    Painter(){
        s = new Sender();
        setup = new Setup();
        currentPalette = new Palette();
    }

    void testPicture() {
        Picture temp = new Picture();
        temp.makeAll(new Composition(Color.YELLOW));
        s.sendPicture(temp);
    }

    void testNumerate1() {
        Composition comp = new Composition(Color.YELLOW);
        for(int i = Config.ADDR_LOWER; i < Config.ADDR_UPPER; i++ ) {
            s.sendCompositionTo(comp, i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void testNumerate2() {
        Composition comp = new Composition(Color.BLUE);
        for(int i = Config.ADDR_LOWER; i < Config.ADDR_UPPER; i++ ) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s.sendCompositionTo(comp, i);
        }
    }


    void testSequence() { //Does sequence work correctly?
        Sequence seq = new Sequence();
        currentPalette.makeBasic();
        for(int i = 0; i < currentPalette.size(); i++){
            seq.add(new Picture(currentPalette.get(i)));
        }

        while (true) {
            for(int i = 0; i < currentPalette.size(); i++) {
                s.sendPicture(seq.get(i));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    void spectrum() { //Should work
        Palette gradient = new Palette();
        gradient.makeSpectrum();

        while (true) {
            for(int i = 0; i < gradient.size(); i++) {
                s.sendCompositionToAll(new Composition(gradient.get(i)));
                try {
                    Thread.sleep(17);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    void numerateWanderingPoint(){  //Works

        //Initialize
        Composition initC = new Composition(Color.WHITE);
        Picture init = new Picture(initC);
        s.sendPicture(init);
        initC.setPoint(1,  Color.RED);
        initC.setPoint(2,  Color.RED);
        initC.setPoint(3,  Color.RED);




        while(true) {
            for (int i = 0; i <= Config.ADDR_UPPER; i++) {
                Composition runnerC = new Composition(initC);
                runnerC.setAddressByte(i);
                for (int j = 0; j < Config.NUMBER_OF_LEDS; j++) {
                    s.sendComposition(runnerC);
                    runnerC.shiftRightS();
                    runnerC.shiftRightS();
                    runnerC.shiftRightS();
                    runnerC.setPoint(1, Color.WHITE);
                    runnerC.setPoint(2, Color.WHITE);
                    runnerC.setPoint(3, Color.WHITE);

                    try {
                        Thread.sleep(6);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    void circleWanderingPoint(Color colBack, Color colPoint){

        //Initialize
        Composition initLeft = new Composition(colBack);
        s.sendCompositionToAll(initLeft);
        initLeft.setPoint(1,  colPoint);
        initLeft.setPoint(2,  colPoint);
        initLeft.setPoint(3,  colPoint);
        Composition initRight = new Composition(initLeft);
        initRight.invert();

        //Iterate through 'circle'
        LedBar currentBar = setup.bars.get(1);
        while(true) {
                if(currentBar.leftRow){
                    Composition runnerC = new Composition(initLeft);
                    runnerC.setAddressByte(currentBar.id);
                    for (int j = 0; j < Config.NUMBER_OF_LEDS; j++) {
                        s.sendComposition(runnerC);
                        runnerC.shiftRightS();
                        runnerC.shiftRightS();
                        runnerC.shiftRightS();
                        runnerC.setPoint(1, colBack);
                        runnerC.setPoint(2, colBack);
                        runnerC.setPoint(3, colBack);
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    Composition runnerC = new Composition(initRight);
                    runnerC.setAddressByte(currentBar.id);
                    for (int j = 0; j < Config.NUMBER_OF_LEDS; j++) {
                        s.sendComposition(runnerC);
                        runnerC.shiftLeftS();
                        runnerC.shiftLeftS();
                        runnerC.shiftLeftS();
                        runnerC.setPoint(122, colBack);
                        runnerC.setPoint(121, colBack);
                        runnerC.setPoint(120, colBack);
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            currentBar = currentBar.hnext;
            }
    }

    void somethingSequence() {
        Sequence seq = new Sequence();
        currentPalette.makeSomething();
        for(int i = 0; i < currentPalette.size(); i++){
            seq.add(new Picture(currentPalette.get(i)));
        }

        while (true) {
            for(int i = 0; i < currentPalette.size(); i++) {
                s.sendPicture(seq.get(i));

                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
