package animals;

public class Cat extends Animal {

    public Cat(int id, String name, String skills) {
        super(id, name, skills);
    }

    @Override
    public void displayCommands() {
        System.out.println("Cat's commands " + getName() + ": " + getSkills());
    }

    @Override
    public void teachNewCommand(String command) {
        String updatedSkills = getSkills() + "," + command;
        setSkills(updatedSkills);
        System.out.println("Cat " + getName() + " learned new command: " + command);
    }
}

