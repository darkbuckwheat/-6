package Server.commands;

import Common.exceptions.InvalidCommandArguments;
import Common.utills.CommandRequirement;
import Common.utills.ExecuteCode;
import Common.utills.ServerResponse;
import Server.utillity.CommandManager;

import java.util.HashMap;

public class HelpCommand extends AbstractCommand{
    private final CommandManager commandManager;
    private final HashMap<String, AbstractCommand> commands;

    public HelpCommand(HashMap<String, AbstractCommand> commands, CommandManager commandManager){
        super("help", "Выводит справку по доступным командам", CommandRequirement.NONE);
        this.commandManager = commandManager;
        this.commands = commands;
    }

    public ServerResponse execute(String argument, Object object) throws InvalidCommandArguments {
        if (!argument.isEmpty()) {
            throw new InvalidCommandArguments(argument);
        } else if(object != null){
            throw new InvalidCommandArguments(object.toString());
        }
        String res = "Список доступных команд:";
        for (AbstractCommand command : commands.values()) {
            res = res + "\n" + command.getName() + ": " + command.getDescription();
        }
        commandManager.addToHistory(this);
        return new ServerResponse(res, ExecuteCode.VALUE);
    }
}
