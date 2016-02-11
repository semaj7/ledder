
/**
 * Created by james on 07/02/16.
 */
public class Config {


    static String IP = "10.6.66.10";

    static int PORT = 1337;

    static int DATA_SIZE = 367; //How many bytes are sent

    static int NUMBER_OF_BARS = 15;
    static int BYTES_PER_LED = 3;
    static int NUMBER_OF_LEDS = 122;

    //Assumption: 1 address-byte
    //The position of the byte in the bytestring that is for adressing the led-bars
    static int ADDR_BYTE = 0;
    static int ADDR_UPPER = NUMBER_OF_BARS - 1;
    static int ADDR_LOWER = 0; // usually:      0


    static void checkbound(int i){ //Check is addr is in bound
        if(i > ADDR_UPPER || i < ADDR_LOWER){
            throw new IndexOutOfBoundsException();
        }
    }
}
