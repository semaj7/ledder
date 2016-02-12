import java.awt.*;

/**
 * Created by james on 07/02/16.
 * A composition is a bytestring for one led-bar
 *
 * When accessing a specific led (not byte): Location is the number of the LED. (there's no 0!)
 */


public class Composition{

    byte[] bytes;


    Composition(){ //Default: all off (black)
        bytes = new byte[367];
        makeAllOne(0  ,0,0);
    }
    Composition(Composition source){
        this.bytes = source.bytes.clone();
    }

    Composition(Color col){
        bytes = new byte[367];
        makeAllOne(col);
    }
    Composition(int r, int g, int b){
        bytes = new byte[367];
        makeAllOne(r,g,b);
    }



    void setAddressByte(int i){
        Config.checkbound(i);
        bytes[Config.ADDR_BYTE] = (byte) i;
    }
    byte getAddressByte(){
        return bytes[Config.ADDR_BYTE];
    }


    void invert(){ //invert the colors
        Composition temp = new Composition(this);
        for(int i = 1; i <= 122; i++){
            setPoint(i, temp.getPoint(122-i+1));
        }
    }

    void clear(){
        makeAllOne(0,0,0);
    }

    //Sets a single Led-Point to a color.
    void setPoint(int location, Color color){
        zeroCheck(location);
        int address = location*3;
        bytes[address] = (byte) color.getBlue();
        bytes[address-1] = (byte) color.getGreen();
        bytes[address-2] = (byte) color.getRed();
    }

    Color getPoint(int location){ //Location is the number of the LED. (there's no 0!)
        zeroCheck(location);
        int address = location*3;
        int blue = Byte.toUnsignedInt( bytes[address]);
        int green = Byte.toUnsignedInt(bytes[address-1]);
        int red = Byte.toUnsignedInt(bytes[address-2]);
        return new Color(red, green, blue);
    }
    //Sets a area around a Led-Point to a color
    void setBand(int location, int radius, Color color){ //Location is the number of the LED. (there's no 0!)
        zeroCheck(location);
        setPoint(location, color);
        for(int i = 1; i <= radius; i++){
            int left = location - i;
            int right = location + i;
            if(left > 0) setPoint(left, color);
            if(right <= Config.NUMBER_OF_LEDS) setPoint(right,color);
        }
    }
    //Shift the colors of the leds to left. Fill the empty one with the 'sign'
    void shiftLeftS(){
        Color sign = getPoint(122);
        shiftLeft();
        setPoint(122, sign);
    }
    //Shift the colors of the leds to right. Fill the empty one with the 'sign'
    void shiftRightS(){
        Color sign = getPoint(1);
        shiftRight();
        setPoint(1, sign);
    }
    void shiftLeft(){
        byte tempb0, tempb1, tempb2;
        byte tempa0 = 0, tempa1 = 0, tempa2 = 0;
        for(int j = 366; j > 2; j-=3){
            tempb0 = bytes[j];
            tempb1 = bytes[j-1];
            tempb2 = bytes[j-2];
            bytes[j] = tempa0;
            bytes[j-1] = tempa1;
            bytes[j-2] = tempa2;
            tempa0 = tempb0;
            tempa1 = tempb1;
            tempa2 = tempb2;
        }
    }
    void shiftRight(){
        byte tempb0, tempb1, tempb2;
        byte tempa0 = 0, tempa1 = 0, tempa2 = 0;
        for(int j = 3; j <= 366; j+=3){
            tempb0 = bytes[j];
            tempb1 = bytes[j-1];
            tempb2 = bytes[j-2];
            bytes[j] = tempa0;
            bytes[j-1] = tempa1;
            bytes[j-2] = tempa2;
            tempa0 = tempb0;
            tempa1 = tempb1;
            tempa2 = tempb2;
        }

    }
    //Shift the colors of the leds to left.
    void shiftAmtLeft(int amount){
        //TODO
        int bound = amount*3;
        int[] temp = new int[bound];
        for(int j = 0; j < bound; j++){
            temp[j] = bytes[366-j];
        }
        for(int j = 0,i = bound; j < 366; j++){
        }
    }

    //Shift the colors of the leds to right.
    void shiftAmtRight(int i){
        //TODO
    }

    void makeAllOne(Color color){ //Creates a composition of only one color //Assuming 3 bytes per led
        for(int i = 3; i < Config.NUMBER_OF_LEDS * 3; i+=3 ){
            bytes[i] = (byte) color.getBlue();
            bytes[i-1] = (byte) color.getGreen();
            bytes[i-2] = (byte) color.getRed();
        }

    }
    void makeAllOne(int r, int g, int b){ //Creates a composition of only one color
        Color color = new Color(r,g,b);
        makeAllOne(color);
    }

    static void zeroCheck(int x){
        // Location is the number of the LED. (there's no 0!)
         if(x == 0){
                throw new IndexOutOfBoundsException();
            }

    }


}
