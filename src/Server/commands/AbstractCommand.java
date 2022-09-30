package Server.commands;

import Common.utills.CommandRequirement;
import Common.utills.ServerResponse;

public abstract class AbstractCommand {
    private final String name;
    private final String description;
    private final CommandRequirement requirement;

    public AbstractCommand(String name, String description, CommandRequirement requirement) {
        this.name = name;
        this.description = description;
        this.requirement = requirement;
    }

    public abstract ServerResponse execute(String argument, Object object) throws Exception;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandRequirement getRequirement() {
        return requirement;
    }
}
