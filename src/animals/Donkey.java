package animals;

public class Donkey extends Animal {
	public Donkey(int id, String name, String skills) {
		super(id, name, skills);
	}

	@Override
	public void displayCommands() {
		System.out.println("Donkey's commands list: ");
		System.out.println(getSkills());
	}

	@Override
	public void teachNewCommand(String command) {
		String updatedSkills = getSkills() + "," + command;
		setSkills(updatedSkills);
		System.out.println("Donkey " + getName() + " learned new command: " + command);
	}
}
