import java.nio.channels.*;
import java.nio.*;
import java.net.*;
import java.util.Iterator;
import java.util.Random;

public class Servidor {

    public static void main(String[] args) {
        int pto = 9999;
        Random aleatorio = new Random(System.currentTimeMillis());
        try {
            ServerSocketChannel s = ServerSocketChannel.open();
            s.configureBlocking(false);
            //??
            s.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            s.socket().bind(new InetSocketAddress(pto));
            Selector sel = Selector.open();
            s.register(sel, SelectionKey.OP_ACCEPT);
            System.out.println("Servicio iniciado..esperando clientes..");

            while (true) {
                sel.select();
                Iterator<SelectionKey> it = sel.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey k = (SelectionKey) it.next();
                    it.remove();
                    if (k.isAcceptable()) {
                        SocketChannel cl = s.accept();
                        System.out.println("Cliente conectado desde->" + cl.socket().getInetAddress().getHostAddress() + ":" + cl.socket().getPort());
                        cl.configureBlocking(false);
                        cl.register(sel, SelectionKey.OP_READ);
                        continue;
                    }//if
                    if (k.isReadable()) {
                        SocketChannel ch = (SocketChannel) k.channel();
                        ByteBuffer b = ByteBuffer.allocate(2000);
                        b.clear();
                        int n = ch.read(b);
                        b.flip();
                        String msj = new String(b.array(), 0, n);
                        if (msj.equalsIgnoreCase("SALIR")) {
                            System.out.println("Mensaje recibido: " + msj + "\nCliente se va..");
                            ch.close();
                            continue;
                        } else if (msj.equals("1")) {
                            String option[] = {"perro", "gato", "luna", "sol", "nopal", "pie", "vaso", "pluma"};
                            System.out.println("Facil XD");
                            String eco = option[aleatorio.nextInt(7)];
                            ByteBuffer b2 = ByteBuffer.wrap(eco.getBytes());
                            ch.write(b2);
                            continue;
                        } else if (msj.equals("2")) {
                            String option[] = {"amaranto", "leticia", "pantalon", "semilla", "calculadora", "calabaza", "tenedor", "computadora"};
                            System.out.println("Medio");
                            String eco = option[aleatorio.nextInt(7)];
                            ByteBuffer b2 = ByteBuffer.wrap(eco.getBytes());
                            ch.write(b2);
                            continue;
                        } else if (msj.equals("3")) {
                            String option[] = {"Electroencefalografista", "Esternocleidomastoideo", "Electroencefalografía", "Electroencefalograma", "Otorrinolaringólogo", "Electrocardiograma", "Electrodoméstico", "Arteriosclerosis"};
                            System.out.println("Dificil D:");
                            String eco = option[aleatorio.nextInt(7)];
                            ByteBuffer b2 = ByteBuffer.wrap(eco.getBytes());
                            ch.write(b2);
                            continue;
                        } else {
                            System.out.println("Mensaje recibido: " + msj + "\nDevolviendo eco..");
                            String eco = "ECO_" + msj;
                            ByteBuffer b2 = ByteBuffer.wrap(eco.getBytes());
                            ch.write(b2);
                            continue;
                        }//else
                    }//if_readable        
                }//while
            }//while
        } catch (Exception e) {
            e.printStackTrace();
        }//catch
    }//main
}