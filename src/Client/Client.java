package Client;

import Client.exceptions.InvalidInputException;
import Client.exceptions.NoConnectionException;
import Client.utillity.*;
import Common.exceptions.IllegalAddressException;
import Common.utills.Checker;
import Common.utills.CommandRequirement;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;


public final class Client {
    private static final int TIMEOUT = 100;
    private static final int BUFFER_SIZE = 3048;
    private static final int RECONNECTION_ATTEMPTS = 5;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        OutputManager outputManager = new OutputManager(System.out);
        InputManager inputManager = new InputManager(System.in, outputManager);
        DragonReader dragonReader = new DragonReader(inputManager);
        DragonFiller dragonFiller = new DragonFiller(dragonReader, inputManager, outputManager);
        if (args.length == NUMBER_OF_ARGUMENTS) {
            try (DatagramChannel client = DatagramChannel.open()) {
                InetSocketAddress serverAddress = Checker.checkAddress(args[0], args[1]);
                client.bind(null).configureBlocking(false);
                Requester requester = new Requester(client, serverAddress, TIMEOUT, BUFFER_SIZE, RECONNECTION_ATTEMPTS, outputManager);
                HashMap<String, CommandRequirement> requirements = requester.sendPullingRequest();
                ConsoleManager consoleManager = new ConsoleManager(requirements, inputManager, outputManager, dragonFiller, requester);
                consoleManager.start();
            } catch (IllegalAddressException | NoConnectionException | InterruptedException
                    | ClassNotFoundException | InvalidInputException e) {
                outputManager.printlnImportantColorMessage(e.getMessage(), Color.RED);
            } catch (IOException e) {
                outputManager.printlnImportantColorMessage("error during connection:", Color.RED);
                outputManager.printlnImportantColorMessage(e.getMessage(), Color.RED);
            }
        } else {
            outputManager.printlnImportantColorMessage("please enter a server hostname and port as a command "
                    + "line arguments", Color.RED);
        }
    }
}