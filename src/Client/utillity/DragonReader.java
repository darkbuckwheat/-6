package Client.utillity;

import Client.exceptions.InvalidInputException;
import Common.classes.DragonColor;
import Common.exceptions.IncorrectFieldValueException;

import java.util.Locale;

public class DragonReader{
    private final InputManager inputManager;

    public DragonReader(InputManager inputManager){
        this.inputManager = inputManager;
    }

    public Long readId() throws InvalidInputException {
        Long id;
        id = Long.parseLong(inputManager.read());
        return id;
    }

    public String readName() throws InvalidInputException {
        String name;
        name = inputManager.read();
        return name;
    }

    public int readAge() throws InvalidInputException, IncorrectFieldValueException {
        int age;
        age = Integer.parseInt(inputManager.read());
        if (age < 0){throw new IncorrectFieldValueException("age", Integer.toString(age), "more than 0");}
        return age;
    }

    public float readWingspan() throws InvalidInputException, IncorrectFieldValueException {
        float wingspan;
        wingspan = Float.parseFloat(inputManager.read());
        if (wingspan < 0){throw new IncorrectFieldValueException("wingspan", Float.toString(wingspan), "more than 0");}
        return wingspan;
    }

    public Boolean readSpeaking() throws IncorrectFieldValueException, InvalidInputException {
        Boolean speaking;
        String inp = inputManager.read().toLowerCase(Locale.ROOT);
        if (inp != "true" && inp != "false"){
            throw new IncorrectFieldValueException("speaking", inp, "one of 'true' or 'false'");
        }
        speaking = Boolean.parseBoolean(inp);
        return speaking;
    }

    public long readX() throws InvalidInputException, IncorrectFieldValueException {
        long x;
        x = Long.parseLong(inputManager.read());
        if (x <= -846){throw new IncorrectFieldValueException("x", Long.toString(x), "more than -846");}
        return x;
    }

    public double readY() throws InvalidInputException {
        double y;
        y = Double.parseDouble(inputManager.read());
        return y;
    }

    public int readDepth() throws InvalidInputException {
        int depth;
        depth = Integer.parseInt(inputManager.read());
        return depth;
    }

    public DragonColor readColor() throws InvalidInputException, IncorrectFieldValueException {
        DragonColor color;
        String inp = inputManager.read();
        color = DragonColor.getColorByName(inp);
        if (color == null){throw new IncorrectFieldValueException("color", inp, "one of " + DragonColor.getAll());}
        return color;
    }
}
