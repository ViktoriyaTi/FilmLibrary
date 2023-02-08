package Rentpackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Filmpackage.Film;
import Personpackage.Person;

public class RentFilm {
    String phonenumber;
    Person person;
    String filmName;

    ArrayList<RentFilm> rentFilmArrayList = new ArrayList<>();

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setPerson(Person serviceAdmin) {
        this.person = serviceAdmin;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String rentedFilm) {
        this.filmName = rentedFilm;
    }

    public ArrayList<RentFilm> getRentFilmArrayList() {
        return rentFilmArrayList;
    }

    public void setRentFilmArrayList(ArrayList<RentFilm> rentFilmArrayList) {
        this.rentFilmArrayList = rentFilmArrayList;
    }

    public RentFilm createRentFilm(ArrayList<Person> pb, ArrayList<Film> fb, Person admin) {
        RentFilm rentFilm = new RentFilm();
        String phone;
        String name;
        System.out.println("Введите номер телефона: ");
        Scanner input = new Scanner(System.in);
        phone = input.nextLine();
        for (Person person : pb) {
            if (person.getPhoneNumber().contains(phone)) {
                rentFilm.setPhonenumber(person.getPhoneNumber());
            }
        }
        System.out.println("Введите название фильма: ");
        name = input.nextLine();
        for (Film film : fb) {
            if (film.getName().contains(name)) {
                rentFilm.setFilmName(film.getName());
            }
        }
        if ((rentFilm.getPhonenumber() == null) || (rentFilm.getFilmName() == null)) {
            System.out.println("Запись пустая");
        } else {
            rentFilm.setPerson(admin);
        }
        return rentFilm;
    }

    public void fillRecord(ArrayList<RentFilm> record, ArrayList<Person> pb, ArrayList<Film> fb, Person admin) {
        boolean flag = true;
        while (flag) {
            System.out.println("Вы хотите добавить новую запись об аренде фильма? 1 - Да, 2 - Нет");
            Scanner input = new Scanner(System.in);
            int a = input.nextInt();
            if (a == 1) {
                record.add(createRentFilm(pb, fb, admin));
            } else if (a == 2) {
                System.out.println("База записей сохранена");
                flag = false;
            } else {
                System.out.println("Введите 1 - Да  или 2 - Нет");
            }
        }
        setRentFilmArrayList(record);
    }

    public void outputToTxtFile(String filename, ArrayList<RentFilm> record) throws IOException {
        filename = filename + ".txt";
        FileWriter fileWriter = new FileWriter(filename);

        for (RentFilm x : record) {
            fileWriter.write(x.toString());
            fileWriter.write("\n");
        }
        fileWriter.flush();
    }

    public ArrayList<RentFilm> inputFile(String filename, ArrayList<Person> pb) throws IOException {
        String[] parsing;
        ArrayList<RentFilm> output = new ArrayList<>();
        java.io.File file = new File(filename + ".txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            parsing = scanner.nextLine().split(",");

            RentFilm rentFilm = new RentFilm();
            rentFilm.setPhonenumber(parsing[0]);

            rentFilm.setFilmName(parsing[1]);

            for (Person x : pb) {
                if (x.getName().contains(parsing[2])) {
                    rentFilm.setPerson(x);
                }
            }

            output.add(rentFilm);

        }
        return output;
    }

    @Override
    public String toString() {
        return phonenumber + ',' + filmName + ',' + person.getName();
    }
}
