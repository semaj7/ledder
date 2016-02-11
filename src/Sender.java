import java.io.IOException;
import java.net.*;

/**
 * Created by james on 06/02/16.
 */
public class Sender {

    DatagramSocket socket;
    InetAddress aHost;

    Sender() {
        try {
            aHost = InetAddress.getByName(Config.IP);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    void send(byte[] data) {
        try {
            socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(data, Config.DATA_SIZE, aHost, Config.PORT);
            socket.send(packet);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    void sendCompositionTo(Composition c, int dest) {
        c.setAddressByte(dest);
        sendComposition(c);
    }

    void sendComposition(Composition c) {
        DatagramPacket packet = new DatagramPacket(c.bytes, Config.DATA_SIZE, aHost, Config.PORT);
        try {
            socket = new DatagramSocket();
            socket.send(packet);
            System.out.println("Sent composition to " + c.getAddressByte());
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    void sendPicture(Picture picture) {

        try {
            socket = new DatagramSocket();
            for (int i = 0; i < picture.size(); i++) {
                System.out.println("Sending composition to " + picture.get(i).getAddressByte());
                DatagramPacket packet = new DatagramPacket(picture.get(i).bytes, Config.DATA_SIZE, aHost, Config.PORT);
                socket.send(packet);
            }
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }

    }

    void sendCompositionToAll(Composition comp){
        Composition temp = new Composition(comp);
        try {
            socket = new DatagramSocket();
            for(int i = 0; i < 15; i++){
                temp.setAddressByte(i);
                DatagramPacket packet = new DatagramPacket(temp.bytes, Config.DATA_SIZE, aHost, Config.PORT);
                socket.send(packet);
            }
            System.out.println("Sending composition to all");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
