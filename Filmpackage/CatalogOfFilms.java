package Filmpackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CatalogOfFilms {
    ArrayList<Film> filmsCatalog = new ArrayList<>();

    String name;

    public ArrayList<Film> getFilmsCatalog() {
        return filmsCatalog;
    }

    public void setrecordfilms(ArrayList<Film> record) {
        this.filmsCatalog = record;
    }

    public Film createFilm() {
        Scanner input = new Scanner(System.in);
        FilmBuilder film = FilmBuilder.getInstance();
        System.out.println("Введите название: ");
        film.setname(input.nextLine());
        System.out.println("Введите комментарий(описание к фильму): ");
        film.setdescription(input.nextLine());
        System.out.println(
                "Выберите жанр:\n1 - комедия\n2 - ужасы \n3 - боевик\n4 - драма, \n5 - мультфильм\nиной жанр - введите другой символ");
        int choice;
        try {
            choice = (input.nextInt());
            if (choice == 1) {
                film.setgenre(Genre.comedy);
            } else if (choice == 2) {
                film.setgenre(Genre.horror);
            } else if (choice == 3) {
                film.setgenre(Genre.action);
            } else if (choice == 4) {
                film.setgenre(Genre.drama);
            } else if (choice == 5) {
                film.setgenre(Genre.cartoon);
            } else {
                film.setgenre(Genre.other);
            }
        } catch (InputMismatchException inputMismatchException) {
            film.setgenre(Genre.other);
        }
        return film.build();
    }

    public void fillRecord(ArrayList<Film> record) {
        boolean flag = true;
        while (flag) {
            System.out.println("Вы хотите добавить новый фильм? 1 - Да, 2 - Нет");
            Scanner input = new Scanner(System.in);
            int a = input.nextInt();
            if (a == 1) {
                record.add(createFilm());
            } else if (a == 2) {
                System.out.println("Каталог фильмов сохранен");
                flag = false;
            } else {
                System.out.println("Введите 1 - Да  или 2 - Нет");
            }
        }
        setrecordfilms(record);
    }

    public void saveFile(String filename, ArrayList<Film> record) throws IOException {
        filename = filename + ".txt";
        FileWriter fileWriter = new FileWriter(filename);

        for (Film x : record) {
            fileWriter.write(x.toString());
            fileWriter.write("\n");
        }
        fileWriter.flush();
    }

    public ArrayList<Film> inputFile(String filename) throws IOException {
        String[] parsing;
        ArrayList<Film> output = new ArrayList<>();
        java.io.File file = new File(filename + ".txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            parsing = scanner.nextLine().split(",");

            Film film = new Film();
            film.setName(parsing[0]);
            film.setGenre(Genre.valueOf(parsing[1]));
            film.setDescription(parsing[2]);
            output.add(film);
        }
        return output;
    }
}