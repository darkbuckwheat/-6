package Server;

import Client.utillity.DragonFiller;
import Client.utillity.DragonReader;
import Client.utillity.InputManager;
import Common.exceptions.IllegalAddressException;
import Common.utills.Checker;
import Server.utillity.CollectionManager;
import Server.utillity.CommandManager;
import Server.utillity.Receiver;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.HashSet;

public class Server {
    private static final int BUFFER_SIZE = 2048;
    //private static final Logger LOGGER = LogManager.getLogger(Server.class);
    private static final int NUMBER_OF_ARGUMENTS = 3;

    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        //LOGGER.trace("the server is running");
        if (args.length == NUMBER_OF_ARGUMENTS) {
            try {
                //the host name is indicated by the first string in the command line arguments, the port - by second
                final InetSocketAddress address = Checker.checkAddress(args[0], args[1]);
                //LOGGER.info(() -> "set " + address + " address");
                // the filename is indicated by the third string in the command line arguments
                final String filename = args[2].trim();
                if (filename.isEmpty()) {
                    //LOGGER.error("no data file!");
                } else {
                    try {
                        DatagramSocket server = new DatagramSocket(address);
                        CollectionManager collectionManager = new CollectionManager(new HashSet<>(), "");
                        CommandManager commandManager = new CommandManager(collectionManager);
                        Receiver receiver = new Receiver(commandManager, server, BUFFER_SIZE);
                        while (true) {
                            receiver.receive();
                        }
                    } catch (ClassNotFoundException | IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (IllegalAddressException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("command line arguments must indicate host name, port and filename");
        }
        System.out.println("the server is shutting down");
    }
}