package Client.utillity;

import Client.exceptions.InvalidInputException;
import Client.exceptions.NoConnectionException;
import Client.exceptions.ScriptException;
import Common.exceptions.IncorrectFieldValueException;
import Common.utills.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

public class ConsoleManager {
    private HashMap<String, CommandRequirement> commands;
    private final InputManager inputManager;
    private final OutputManager outputManager;
    private final DragonFiller dragonFiller;
    private final Requester requester;

    public ConsoleManager(HashMap<String, CommandRequirement> commands, InputManager inputManager,
                          OutputManager outputManager, DragonFiller dragonFiller, Requester requester) {
        this.commands = commands;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
        this.dragonFiller = dragonFiller;
        this.requester = requester;
    }

    public void start() throws IOException, ClassNotFoundException, InvalidInputException,
            NoConnectionException, InterruptedException {
        boolean executeFlag = true;
        commands = requester.sendPullingRequest();
        while (executeFlag) {
            String input = inputManager.read();
            if (!input.trim().isEmpty()) {
                String inputCommand = input.split(" ")[0].toLowerCase(Locale.ROOT);
                String argument = "";
                if (input.split(" ").length > 1) {
                    argument = input.replaceFirst(inputCommand + " ", "");
                }
                try {
                    ClientRequest request = new ClientRequest(inputCommand, argument, getObjectArgument(inputCommand));
                    ServerResponse response = (ServerResponse) requester.send(request);
                    executeFlag = processServerResponse(response);
                } catch (ScriptException | IncorrectFieldValueException e) {
                    inputManager.finishReadScript();
                    outputManager.printlnImportantColorMessage(e.getMessage(), Color.RED);
                }
            } else {
                outputManager.printlnColorMessage("Please type any command. To see list of command type \"help\"",
                        Color.RED);
            }
        }
    }

    public Object getObjectArgument(String commandName)
            throws ScriptException, InvalidInputException, IncorrectFieldValueException {
        Object object = null;
        if (commands.containsKey(commandName)) {
            CommandRequirement requirement = commands.get(commandName);
            switch (requirement) {
                case DRAGON:
                    object = dragonFiller.fillDragon();
                    break;
                case CAVE:
                    object = dragonFiller.fillCave();
                    break;
                case COORDINATES:
                    object = dragonFiller.fillCoordinates();
                    break;
                default:
                    break;
            }
        }
        return object;
    }

    public boolean processServerResponse(ServerResponse serverResponse) {
        ExecuteCode executeCode = serverResponse.getExecuteCode();
        switch (executeCode) {
            case ERROR:
                inputManager.finishReadScript();
                outputManager.printlnColorMessage(executeCode.getMessage(), Color.RED);
                outputManager.printlnColorMessage(serverResponse.getMessage(), Color.RED);
                break;
            case SUCCESS:
                outputManager.printlnColorMessage(executeCode.getMessage(), Color.GREEN);
                break;
            case VALUE:
                outputManager.printlnImportantMessage(executeCode.getMessage());
                outputManager.printlnImportantMessage(serverResponse.getMessage());
                break;
            case READ_SCRIPT:
                inputManager.startReadScript(serverResponse.getMessage());
                break;
            case EXIT:
                outputManager.printlnImportantColorMessage(executeCode.getMessage(), Color.RED);
                return false;
            default:
                outputManager.printlnImportantColorMessage("incorrect server's response...", Color.RED);
        }
        return true;
    }
}
