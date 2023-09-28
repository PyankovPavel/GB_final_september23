package animals;

public class Horse extends Animal {
    public Horse(int id, String name, String skills) {
        super(id, name, skills);
    }

    @Override
    public void displayCommands() {
        System.out.println("Horse's list of commands: ");
        System.out.println(getSkills());
    }

    @Override
    public void teachNewCommand(String command) {
        String updatedSkills = getSkills() + "," + command;
        setSkills(updatedSkills);
        System.out.println("Horse " + getName() + " learned new command: " + command);
    }
}