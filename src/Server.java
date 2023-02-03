import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("Creando socket servidor");

            ServerSocket serverSocket = new ServerSocket();

            System.out.println("Realizando el bind");

            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");

            Socket newSocket = serverSocket.accept();

            System.out.println("Conexión recibida");

            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();

            byte[] arrayOpcion = new byte[1];
            is.read(arrayOpcion);

            int opcion = Integer.parseInt(new String(arrayOpcion));
            System.out.println(opcion);

            switch (opcion){
                case 1:
                    byte[] arrayDatos = new byte[2];
                    is.read(arrayDatos);
                    int datoRecibido = Integer.parseInt(new String(arrayDatos));
                    System.out.println(datoRecibido);
                    break;
                case 2:
                    System.out.println("OPTION 2");
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }



            System.out.println("Cerrando el nuevo socket");

            newSocket.close();

            System.out.println("Cerrando el socket servidor");

            serverSocket.close();

            System.out.println("Terminado");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}