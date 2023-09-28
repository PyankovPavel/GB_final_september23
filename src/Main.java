import program.Menu;
import program.Zoo;

public class Main {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        Menu menu = new Menu(zoo);
        menu.mainMenu();
    }
}
