package animals;

public class Dog extends Animal {

    public Dog(int id, String name, String skills) {
        super(id, name, skills);
    }

    @Override
    public void displayCommands() {
        System.out.println("Dog's commands list: ");
        System.out.println(getSkills());
    }

    @Override
    public void teachNewCommand(String command) {
        String updatedSkills = getSkills() + "," + command;
        setSkills(updatedSkills);
        System.out.println("Dog " + getName() + " learned new command: " + command);
    }
}
