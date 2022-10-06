package Server;

import Client.exceptions.InvalidInputException;
import Common.exceptions.IllegalAddressException;
import Common.exceptions.InvalidCommandArguments;
import Common.exceptions.NumberOfArgumentException;
import Common.utills.Checker;
import Server.utillity.CollectionCreator;
import Server.utillity.CollectionManager;
import Server.utillity.CommandManager;
import Server.utillity.Receiver;
import ch.qos.logback.classic.Logger;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class Server {
    private static final int BUFFER_SIZE = 2048;
    private static final Logger LOGGER = (Logger) org.slf4j.LoggerFactory.getLogger(Server.class);
    private static final int NUMBER_OF_ARGUMENTS = 3;
    private static final Scanner in = new Scanner(System.in);

    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        Boolean run = true;
        LOGGER.trace("the server is running");
        if (args.length == NUMBER_OF_ARGUMENTS) {
            try {
                final InetSocketAddress address = Checker.checkAddress(args[0], args[1]);
                LOGGER.info("set " + address + " address");
                final String filename = args[2].trim();
                if (filename.isEmpty()) {
                    LOGGER.error("no data file!");
                } else {
                    try {
                        DatagramSocket server = new DatagramSocket(address);
                        CollectionManager collectionManager = CollectionCreator.load(args[2], LOGGER);
                        CommandManager commandManager = new CommandManager(collectionManager);
                        Receiver receiver = new Receiver(commandManager, server, BUFFER_SIZE, LOGGER);
                        while (run) {
                            run = !existsConsoleInput(commandManager);
                            if (!run){break;}
                            receiver.receive();
                        }
                    }catch (ClassNotFoundException e) {
                        LOGGER.error("wrong data from client");
                    } catch (IOException e) {
                        LOGGER.error(String.valueOf(e));
                    } catch (InvalidInputException | InvalidCommandArguments | NumberOfArgumentException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IllegalAddressException e) {
                LOGGER.error("Illegal address" + e);
            }
        } else {
            LOGGER.error("command line arguments must indicate host name, port and filename");
        }
        LOGGER.trace("the server is shutting down");
    }

    private static Boolean existsConsoleInput(CommandManager commandManager) throws IOException, InvalidInputException, NumberOfArgumentException, InvalidCommandArguments {
        if (System.in.available() > 0){
            String command = in.nextLine();
            switch (command) {
                case "save":
                    try {
                        commandManager.getSaveCommand().execute("", null);
                        System.out.println("Collection saved");
                    }
                    catch (NullPointerException e){
                        System.out.println("Collection can't be saved because it isn't initialized eyt");
                    }
                    break;
                case "end":
                    System.out.println("Shutting server down");
                    return true;
                default:
                    System.out.println("There are only two commands: end, save");
            }
        }
        return false;
    }
}