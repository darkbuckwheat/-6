package Common.exceptions;

public class FileException extends Exception{
    public FileException(){
        super("Problem with provided file. File dose not exists or program has no rights to change it.");
    }
}
