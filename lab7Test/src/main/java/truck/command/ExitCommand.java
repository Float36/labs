package truck.command;

public class ExitCommand implements Command{
    @Override
    public void execute(){
        System.out.println("Вихід з програми...");
        System.exit(0);
    }
}
