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
            do {
                System.out.println("Aceptando conexiones");

                Socket newSocket = serverSocket.accept();

                System.out.println("Conexión recibida");

                InputStream is = newSocket.getInputStream();

                byte[] arrayOpcion = new byte[1];
                is.read(arrayOpcion);

                int opcion = Integer.parseInt(new String(arrayOpcion));
                System.out.println("OPCION: " + opcion);

                byte[] arrayDigitos = new byte[1];
                is.read(arrayDigitos);

                int digitos = Integer.parseInt(new String(arrayDigitos));
                System.out.println("DIGITOS: " + digitos);

                switch (opcion) {
                    case 1:
                        byte[] arrayDatos = new byte[digitos];
                        is.read(arrayDatos);

                        int datoRecibido = Integer.parseInt(new String(arrayDatos));
                        float eqCamposFlo = datoRecibido / 4050f; //1 campo de fútbol = 4050m2
                        String eqCampos = String.valueOf(eqCamposFlo);

                        devolverDatos(eqCampos);
                        break;
                    case 2:
                        byte[] arrayDatos2 = new byte[digitos];
                        is.read(arrayDatos2);

                        int datoRecibido2 = Integer.parseInt(new String(arrayDatos2));
                        float eqMesesFlo = (datoRecibido2 / 575f); //Me quedan 575 meses para la jubilación ((67-19)*12)
                        String eqMeses = String.valueOf(eqMesesFlo);

                        devolverDatos(eqMeses);
                        break;
                    case 3:
                        byte[] arrayDatos3 = new byte[digitos];
                        is.read(arrayDatos3);

                        int datoRecibido3 = Integer.parseInt(new String(arrayDatos3));
                        float eqLibrosFlo = datoRecibido3 / 47f; //Pérez Reverte tiene 47 obras literarias
                        String eqLibros = String.valueOf(eqLibrosFlo);

                        devolverDatos(eqLibros);
                        break;
                    case 4:
                        byte[] arrayDatos4 = new byte[digitos];
                        is.read(arrayDatos4);

                        int datoRecibido4 = Integer.parseInt(new String(arrayDatos4));
                        float difGasolinaFlo = datoRecibido4 - 1.517f; //gasolinera más barata -> 1,517€/L
                        String difGasolina = String.valueOf(difGasolinaFlo);

                        devolverDatos(difGasolina);
                        break;
                    case 5:
                        System.out.println("Cerrando el nuevo socket");

                        newSocket.close();

                        System.out.println("Cerrando el socket servidor");

                        serverSocket.close();

                        System.out.println("Terminado");
                }
            } while (true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void devolverDatos(String dato) throws IOException {

        Socket serverSocket = new Socket();
        InetSocketAddress addr = new InetSocketAddress("localhost", 5566);
        serverSocket.connect(addr);
        OutputStream os = serverSocket.getOutputStream();
        os.write(dato.getBytes());
        System.out.println("Dato devuelto: " + dato);
        serverSocket.close();

    }
}