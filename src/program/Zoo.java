package program;

import animals.*;

import java.io.*;
import java.util.*;

public class Zoo {
    private final List<Animal> animals;
    private final Counter counter;
    private static final String FILE_PATH = "zooTable.txt";

    public Zoo() {
        animals = new ArrayList<>();
        counter = new Counter();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        saveData();
    }

    public void displayAnimalCommands(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                animal.displayCommands();
                return;
            }
        }
        System.out.println("Animal " + name + " doesn't exists");
    }


    public void teachNewCommand(String name, String command) {
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                String[] commands = command.split(",");
                for (int i = 0; i < commands.length; i++) {
                    String trimmedCommand = commands[i].trim();
                    commands[i] = trimmedCommand;
                }
                animal.teachNewCommand(command);
                saveData();
                System.out.println("Animal's command added successfully!");
                return;
            }
        }
        System.out.println("Animal " + name + " doesn't exists");
    }


    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String className = data[0];
                    String name = data[1];
                    String skills = String.join(",", Arrays.copyOfRange(data, 2, data.length));
                    Animal animal;
                    switch (className) {
                        case "Animals.Dog" -> animal = new Dog(counter.add(), name, skills);
                        case "Animals.Cat" -> animal = new Cat(counter.add(), name, skills);
                        case "Animals.Hamster" -> animal = new Hamster(counter.add(), name, skills);
                        case "Animals.Donkey" -> animal = new Donkey(counter.add(), name, skills);
                        case "Animals.Horse" -> animal = new Horse(counter.add(), name, skills);
                        default -> {
                            System.out.println("Wrong animal's type: " + className);
                            continue;
                        }
                    }
                    animals.add(animal);
                } else {
                    System.out.println("Wrong data in the file: " + line);
                }
            }
            System.out.println("Load successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void displayAllAnimals() {
        try {
            File file = new File(FILE_PATH);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String animalData = fileScanner.nextLine();
                System.out.println(animalData);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File with animals doesn't exists");
        }
    }

    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Animal animal : animals) {
                String id = Integer.toString(counter.add());
                String className = animal.getClass().getSimpleName();
                String name = animal.getName();
                String skills = animal.getSkills().replaceAll(",\\s+", ",");

                String line = id + ", " + className + ", " + name + ", " + skills;
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Saved successfully!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
