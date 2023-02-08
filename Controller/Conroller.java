package Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Filmpackage.CatalogOfFilms;
import Filmpackage.Film;
import Personpackage.CatalogOfPerson;
import Personpackage.Person;
import Personpackage.Role;
import Rentpackage.RentFilm;

public class Conroller {
    public Conroller() {
    }

    public void start() throws IOException {
        Person person;
        ArrayList<Film> catalogFilms;
        ArrayList<Person> catalogPerson;
        ArrayList<RentFilm> infoRent;
        int choice;
        Scanner input = new Scanner(System.in);
        CatalogOfFilms filmbase = new CatalogOfFilms();
        CatalogOfPerson personbase = new CatalogOfPerson();
        RentFilm rentFilm = new RentFilm();
        catalogFilms = filmbase.inputFile("FilmCatalog");
        catalogPerson = personbase.inputFile("PersonCatalog");
        infoRent = rentFilm.inputFile("InfoAboutRent", catalogPerson);
        person = personbase.getNamePerson(catalogPerson);
        if (person != null & person.getRole().equals(Role.admin)) {
            System.out.println(
                    "Что необходимо сделать?:\n1 - Дополнить каталог фильмов\n2 - Дополнить базу пользователей\n3 - Дополнить каталог записей об аренде");
            choice = input.nextInt();
            if (choice == 1) {
                filmbase.fillRecord(catalogFilms);
                filmbase.saveFile("FilmCatalog", filmbase.getFilmsCatalog());
            }
            if (choice == 2) {
                personbase.fillRecord(catalogPerson);
                personbase.saveFile("PersonCatalog", personbase.getPersonCatalog());
            }
            if (choice == 3) {
                rentFilm.fillRecord(infoRent, catalogPerson, catalogFilms, person);
                rentFilm.outputToTxtFile("InfoAboutRent", rentFilm.getRentFilmArrayList());
            }

        } else
            rentFilm.fillRecord(infoRent, catalogPerson, catalogFilms, person);
        rentFilm.outputToTxtFile("InfoAboutRent", rentFilm.getRentFilmArrayList());

    }
}
