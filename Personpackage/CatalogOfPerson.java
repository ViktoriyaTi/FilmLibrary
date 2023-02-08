package Personpackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CatalogOfPerson {
    ArrayList<Person> personCatalog = new ArrayList<>();

    public ArrayList<Person> getPersonCatalog() {
        return personCatalog;
    }

    public void setPersonCatalog(ArrayList<Person> personCatalog) {
        this.personCatalog = personCatalog;
    }

    public Person createPerson() {
        Scanner input = new Scanner(System.in);
        PersonBuilder person = PersonBuilder.getInstance();
        System.out.println("Введите фамилию: ");
        person.setsurname(input.nextLine());
        System.out.println("Введите имя: ");
        person.setname(input.nextLine());
        System.out.println("Введите номер контактного телефона: ");
        person.setphone(input.nextLine());
        System.out.println("Выберите вашу роль: 1 - Администратор , User - любой другой символ");
        if ((input.nextInt()) == 1) {
            person.setrole(Role.admin);
        } else {
            person.setrole(Role.user);
        }
        return person.build();
    }

    public void fillRecord(ArrayList<Person> information) {
        boolean flag = true;
        while (flag) {
            System.out.println("Вы хотите добавить нового пользователя? 1 - Да, 2 - Нет");
            Scanner input = new Scanner(System.in);
            int a = input.nextInt();
            if (a == 1) {
                information.add(createPerson());
            } else if (a == 2) {
                System.out.println("Каталог пользователей сохранен");
                flag = false;
            } else {
                System.out.println("Введите 1 - Да  или 2 - Нет");
            }
        }
        setPersonCatalog(information);
    }

    public void saveFile(String filename, ArrayList<Person> record) throws IOException {
        filename = filename + ".txt";
        FileWriter fileWriter = new FileWriter(filename);

        for (Person x : record) {
            fileWriter.write(x.toString());
            fileWriter.write("\n");
        }
        fileWriter.flush();
    }

    public ArrayList<Person> inputFile(String filename) throws IOException {
        String[] parsing;
        ArrayList<Person> output = new ArrayList<>();
        java.io.File file = new File(filename + ".txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            parsing = scanner.nextLine().split(",");

            Person person = new Person();

            person.setName(parsing[0]);
            person.setSurname(parsing[1]);
            person.setPhoneNumber(parsing[2]);
            person.setRole(Role.valueOf(parsing[3]));

            output.add(person);
        }
        return output;
    }

    public Person getNamePerson(ArrayList<Person> p) {
        String name;
        System.out.println("Введите Ваше имя");
        Scanner input = new Scanner(System.in);
        name = input.nextLine();
        for (Person person : p) {
            if (person.getName().contains(name)) {
                return person;
            }
        }
        System.out.println("Данный пользователь отсутвует для входа в системy");
        return null;
    }
}