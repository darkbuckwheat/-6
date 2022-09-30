package Client.utillity;

import Client.exceptions.IllegalValueException;
import Client.exceptions.InvalidInputException;
import Client.exceptions.ScriptException;

import Common.exceptions.IncorrectFieldValueException;

public class BaseDragonFiller {
    private final OutputManager outputManager;
    private final InputManager inputManager;

    public BaseDragonFiller(InputManager inputManager, OutputManager outputManager) {
        this.inputManager = inputManager;
        this.outputManager = outputManager;
    }


    public <T> T fill(String message, Reader<T> reader) throws ScriptException, InvalidInputException {
        T returns;
        while (true) {
            try {
                outputManager.print(message + ": ");
                returns = reader.read();
                break;
            } catch (NumberFormatException e) {
                outputManager.printlnColorMessage("Value must be a number", Color.RED);
                if (inputManager.getScriptMode()) {
                    throw new ScriptException("Number expected");
                }
            } catch (IllegalArgumentException e) {
                outputManager.printlnColorMessage("Chose anything from list", Color.RED);
                if (inputManager.getScriptMode()) {
                    throw new ScriptException("An enum value was expected");
                }
            } catch (IllegalValueException e) {
                outputManager.printlnColorMessage(e.getMessage(), Color.RED);
                if (inputManager.getScriptMode()) {
                    throw new ScriptException(e.getMessage());
                }
            } catch (IncorrectFieldValueException e) {
                e.printStackTrace();
            }
        }
        return returns;
    }

}
