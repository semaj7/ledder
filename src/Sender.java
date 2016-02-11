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
        System.out.println("Sending composition to " + c.getAddressByte());

        c.setAddressByte(dest);
        sendComposition(c);
    }

    void sendComposition(Composition c) {
        System.out.println("Sending composition to " + c.getAddressByte());
        DatagramPacket packet = new DatagramPacket(c.bytes, Config.DATA_SIZE, aHost, Config.PORT);
        try {

            socket = new DatagramSocket();
            socket.send(packet);
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

    void sendPicture2(Picture picture) {
        for (int i = 0; i < picture.size(); i++) {
            sendComposition(picture.get(i));
        }
    }
    void sendToAll(){

    }

}
