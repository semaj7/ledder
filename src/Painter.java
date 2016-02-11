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

    void testPicture1() {
        Composition comp = new Composition(Color.YELLOW);
        Picture temp = new Picture();
        temp.makeAll(comp);
        s.sendPicture(temp);

    }
    void testPicture2(){
        Composition comp = new Composition(Color.RED);
        Picture temp = new Picture();
        temp.makeAll(comp);
        s.sendPicture(temp);
    }

    void testNumerate1() {
        Composition comp = new Composition(Color.YELLOW);
        for(int i = Config.ADDR_LOWER; i < Config.ADDR_UPPER; i++ ) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s.sendCompositionTo(comp, i);
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
    void spectrumSequence() { //Should work
        Sequence seq = new Sequence();
        currentPalette.makeSpectrum();
        for(int i = 0; i < currentPalette.size(); i++){
            seq.add(new Picture(currentPalette.get(i)));
        }

        while (true) {
            for(int i = 0; i < currentPalette.size(); i++) {
                s.sendPicture(seq.get(i));

                try {
                    Thread.sleep(100);
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
                    try {
                        Thread.sleep(6);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    void circleWanderingPoint(Color colBack, Color colPoint){  //Not tested yet
        //Would be faster to create sequence instead of calculating each circle again.

        //Initialize
        Composition initLeft = new Composition(colBack);
        Picture init = new Picture(initLeft);
        s.sendPicture(init);

        initLeft.setBand(2, 1, colPoint);
        Composition initRight = new Composition(initLeft);
        initRight.invert();



        while(true) {
            for (int i = 0; i <= Config.ADDR_UPPER; i++) {
                if(!setup.bars.get(i).horizontal){
                    continue;
                }
                if(setup.bars.get(i).leftRow){
                    Composition runnerC = new Composition(initLeft);
                    runnerC.setAddressByte(i);
                    for (int j = 0; j < Config.NUMBER_OF_LEDS; j++) {
                        s.sendComposition(runnerC);
                        runnerC.shiftRightS();
                        runnerC.shiftRightS();
                        runnerC.shiftRightS();
                        runnerC.setPoint(1, colBack);
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    Composition runnerC = new Composition(initRight);
                    runnerC.setAddressByte(i);
                    for (int j = 0; j < Config.NUMBER_OF_LEDS; j++) {
                        s.sendComposition(runnerC);
                        runnerC.shiftLeftS();
                        runnerC.shiftLeftS();
                        runnerC.shiftLeftS();
                        runnerC.setPoint(122, colBack);
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
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
