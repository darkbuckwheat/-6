package Server.utillity;

import Common.exceptions.IllegalKeyException;
import Common.utills.*;
import Server.commands.AbstractCommand;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Receiver {
    private final int bufferSize;
    private final CommandManager commandManager;
    private final DatagramSocket server;

    public Receiver(CommandManager commandManager, DatagramSocket server, int bufferSize) {
        this.commandManager = commandManager;
        this.server = server;
        this.bufferSize = bufferSize;
    }

    public void receive() throws IOException, ClassNotFoundException {
        byte[] bytesReceiving = new byte[bufferSize];
        DatagramPacket request = new DatagramPacket(bytesReceiving, bytesReceiving.length);
        server.receive(request);
        Object received = Serializer.deserialize(bytesReceiving);
        InetAddress client = request.getAddress();
        int port = request.getPort();
        Object response;
        if (received instanceof PullingRequest) {
            response = new PullingResponse(commandManager.getRequirements());
        } else {
            ClientRequest clientRequest = (ClientRequest) received;
            String inputCommand = clientRequest.getCommandName();
            String argument = clientRequest.getCommandArguments();
            Object objectArgument = clientRequest.getObjectArgument();
            if (commandManager.getCommands().containsKey(inputCommand)) {
                AbstractCommand command = commandManager.getCommands().get(inputCommand);
                try {
                    response = command.execute(argument, objectArgument);
                    /*commandManager.getSaveCommand().execute("", null);
                } catch (IllegalKeyException e) {
                    response = new ServerResponse(e.getMessage(), ExecuteCode.ERROR);
                */
                } catch (Exception e) {
                    response = new ServerResponse(e.getMessage(), ExecuteCode.ERROR);
                }
            } else {
                response = new ServerResponse("Unknown command detected: " + inputCommand, ExecuteCode.ERROR);
            }
        }
        byte[] bytesSending = Serializer.serialize(response);
        DatagramPacket packet = new DatagramPacket(bytesSending, bytesSending.length, client, port);
        server.send(packet);
    }
}
