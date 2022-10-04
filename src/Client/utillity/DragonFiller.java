package Client.utillity;

import Client.exceptions.InvalidInputException;
import Client.exceptions.ScriptException;
import Common.classes.Coordinates;
import Common.classes.Dragon;
import Common.classes.DragonCave;
import Common.classes.DragonColor;
import Common.exceptions.IncorrectFieldValueException;

public class DragonFiller {
    private final DragonReader reader;
    private final BaseDragonFiller baseDragonFiller;

    public DragonFiller(DragonReader reader, InputManager inputManager, OutputManager outputManager){
        this.reader = reader;
        baseDragonFiller = new BaseDragonFiller(inputManager, outputManager);
    }

    public Long fillId() throws ScriptException, InvalidInputException {
        return baseDragonFiller.fill("Enter id", reader::readId);
    }

    public Coordinates fillCoordinates() throws ScriptException, InvalidInputException, IncorrectFieldValueException {
        return new Coordinates(baseDragonFiller.fill("Enter x", reader::readX), baseDragonFiller.fill("Enter y", reader::readY));
    }

    public String fillName() throws ScriptException, InvalidInputException {
        return baseDragonFiller.fill("Enter name", reader::readName);
    }

    public int fillAge() throws ScriptException, InvalidInputException {
        return baseDragonFiller.fill("Enter age", reader::readAge);
    }

    public float fillWingspan() throws ScriptException, InvalidInputException {
        return baseDragonFiller.fill("Enter wingspan", reader::readWingspan);
    }

    public Boolean fillSpeaking() throws ScriptException, InvalidInputException {
        return baseDragonFiller.fill("Enter speaking", reader::readSpeaking);
    }

    public DragonColor fillColor() throws ScriptException, InvalidInputException {
        return baseDragonFiller.fill("Enter color", reader::readColor);
    }

    public DragonCave fillCave() throws ScriptException, InvalidInputException {
        return new DragonCave(baseDragonFiller.fill("Enter depth", reader::readDepth));
    }

    public Dragon fillDragon() throws ScriptException, InvalidInputException, IncorrectFieldValueException {
        return new Dragon(this.fillName(), this.fillCoordinates(), this.fillAge(), this.fillWingspan(), this.fillSpeaking(),
                this.fillColor(), this.fillCave());
    }
}
