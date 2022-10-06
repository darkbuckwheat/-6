package Server.utillity;

import Common.utills.CommandRequirement;
import Server.commands.*;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandManager {
    private final HashMap<String, AbstractCommand> commands = new HashMap<>();
    private final ArrayList<String> history = new ArrayList<>();
    private final HashMap<String, CommandRequirement> requirements = new HashMap<>();
    private final SaveCommand saveCommand;

    public CommandManager(CollectionManager collectionManager) {
        ClearCommand clearCommand = new ClearCommand(collectionManager);
        commands.put(clearCommand.getName(), clearCommand);
        AddCommand addCommand = new AddCommand(collectionManager, this);
        commands.put(addCommand.getName(), addCommand);
        ShowCommand showCommand = new ShowCommand(collectionManager, this);
        commands.put(showCommand.getName(), showCommand);
        HelpCommand helpCommand = new HelpCommand(commands, this);
        commands.put(helpCommand.getName(), helpCommand);
        HistoryCommand historyCommand = new HistoryCommand(this);
        commands.put(historyCommand.getName(), historyCommand);
        InfoCommand infoCommand = new InfoCommand(collectionManager, this);
        commands.put(infoCommand.getName(), infoCommand);
        ExitCommand exitCommand = new ExitCommand(collectionManager);
        commands.put(exitCommand.getName(), exitCommand);
        ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand();
        commands.put(executeScriptCommand.getName(), executeScriptCommand);
        FilterLessThanSpeakingCommand filterLessThanSpeakingCommand =
                new FilterLessThanSpeakingCommand(collectionManager, this);
        commands.put(filterLessThanSpeakingCommand.getName(), filterLessThanSpeakingCommand);
        FilterGreaterThanSpeakingCommand filterGreaterThanSpeakingCommand =
                new FilterGreaterThanSpeakingCommand(collectionManager, this);
        commands.put(filterGreaterThanSpeakingCommand.getName(), filterGreaterThanSpeakingCommand);
        PrintByWingspanCommand printByWingspanCommand = new PrintByWingspanCommand(collectionManager, this);
        commands.put(printByWingspanCommand.getName(), printByWingspanCommand);
        UpdateByIdCommand updateByIdCommand = new UpdateByIdCommand(collectionManager, this);
        commands.put(updateByIdCommand.getName(), updateByIdCommand);
        RemoveByIdCommand removeByIdCommand = new RemoveByIdCommand(collectionManager, this);
        commands.put(removeByIdCommand.getName(), removeByIdCommand);
        RemoveGreaterCommand removeGreaterCommand = new RemoveGreaterCommand(collectionManager, this);
        commands.put(removeGreaterCommand.getName(), removeGreaterCommand);
        RemoveLowerCommand removeLowerCommand = new RemoveLowerCommand(collectionManager, this);
        commands.put(removeLowerCommand.getName(), removeLowerCommand);
        ChangeSaveFile changeSaveFile = new ChangeSaveFile(collectionManager, this);
        commands.put(changeSaveFile.getName(), changeSaveFile);

        commands.forEach((k, v) -> requirements.put(k, v.getRequirement()));

        saveCommand = new SaveCommand(collectionManager, this);
    }

    public HashMap<String, AbstractCommand> getCommands() {
        return commands;
    }

    public ArrayList<String> getHistory(){
        return history;
    }

    public HashMap<String, CommandRequirement> getRequirements() {
        return requirements;
    }

    public SaveCommand getSaveCommand() {
        return saveCommand;
    }

    public void addToHistory(AbstractCommand command){
        history.add(command.getName());
        if (history.size() > 15){
            history.remove(0);
        }
    }
}
