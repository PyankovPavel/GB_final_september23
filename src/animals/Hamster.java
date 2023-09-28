package animals;

public class Hamster extends Animal {

    public Hamster(int id, String name, String skills) {
        super(id, name, skills);
    }

    @Override
    public void displayCommands() {
        System.out.println("Hamster commands list: ");
        System.out.println(getSkills());
    }

    @Override
    public void teachNewCommand(String command) {
        String updatedSkills = getSkills() + "," + command;
        setSkills(updatedSkills);
        System.out.println("Hamster " + getName() + " learned new command: " + command);
    }
}
