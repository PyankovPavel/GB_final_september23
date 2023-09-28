package program;/*
 *
 * */

import animals.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    private final Zoo zoo;
    private final BufferedReader br;
    private final Counter counter;

    public Menu(Zoo zoo) {
        this.zoo = zoo;
        br = new BufferedReader(new InputStreamReader(System.in));
        counter = new Counter();
    }

    public void mainMenu() {
        while (true) {
            try {
                System.out.println("1. Add new animal");
                System.out.println("2. Show all animals");
                System.out.println("3. Show animal's commands");
                System.out.println("4. Teach an animal new command");
                System.out.println("0. Exit");
                System.out.print("Input the number of menu: ");
                int input = Integer.parseInt(br.readLine());

                switch (input) {
                    case 1 -> add();
                    case 2 -> zoo.displayAllAnimals();
                    case 3 -> displayAnimalCommands();
                    case 4 -> teachNewCommand();
                    case 0 -> {
                        System.out.println("The program is finished");
                        return;
                    }
                    default -> System.out.println("Wrong input. Try one more time");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Wrong input. Try one more time");
            }
        }
    }


    private void add() throws IOException {
        System.out.println("Input animal's name: ");
        String name = br.readLine();
        System.out.println("Input animal's commands: ");
        String commands = br.readLine();

        System.out.println("The animal's type is: ");
        System.out.println("1. Dog");
        System.out.println("2. Cat");
        System.out.println("3. Hamster");
        System.out.println("4. Donkey");
        System.out.println("5. Horse");
        int animalType = Integer.parseInt(br.readLine());

        Animal animal;
        switch (animalType) {
            case 1 -> animal = new Dog(counter.add(), name, commands);
            case 2 -> animal = new Cat(counter.add(), name, commands);
            case 3 -> animal = new Hamster(counter.add(), name, commands);
            case 4 -> animal = new Donkey(counter.add(), name, commands);
            case 5 -> animal = new Horse(counter.add(), name, commands);
            default -> {
                System.out.println("Wrong animal type.");
                return;
            }
        }

        zoo.addAnimal(animal);
        System.out.println("The animal added successfully!");
    }

    private void displayAnimalCommands() throws IOException {
        System.out.println("Input animal's name: ");
        String name = br.readLine();

        zoo.displayAnimalCommands(name);
    }

    private void teachNewCommand() throws IOException {
        System.out.println("Input animal's name: ");
        String name = br.readLine();
        System.out.println("Input new commands for the animal: ");
        String command = br.readLine();

        zoo.teachNewCommand(name, command);
        System.out.println("The command added successfully!");
    }
}