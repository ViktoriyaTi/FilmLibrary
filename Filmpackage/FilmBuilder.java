package Filmpackage;

public class FilmBuilder {
    private static FilmBuilder instance;
    Film film;

    private FilmBuilder() {
    }

    public static FilmBuilder getInstance() {
        if (instance == null)
            instance = new FilmBuilder();
        instance.film = new Film();

        return instance;
    }

    public void setname(String a) {
        film.name = a;
    }

    public void setdescription(String a) {
        film.description = a;
    }

    public void setgenre(Genre a) {
        film.genre = a;
    }

    public Film build() {
        return film;

    }

}
