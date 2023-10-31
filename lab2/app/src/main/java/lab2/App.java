package lab2;

import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;
import lab2.pokemon.Groudon;
import lab2.pokemon.Hitmonlee;
import lab2.pokemon.Porygon;
import lab2.pokemon.Porygon2;
import lab2.pokemon.PorygonZ;
import lab2.pokemon.Tyrogue;

public class App {
    public static void main(String[] args) {
        Battle b = new Battle();
        b.addAlly(new Groudon("Kirill Zavyalov", 1));
        b.addAlly(new Hitmonlee("Liza Kolomiets", 1));
        b.addAlly(new Porygon("Kate Senina", 1));
        b.addFoe(new Porygon2("Nastya Panova", 1));
        b.addFoe(new PorygonZ("Veronika Roschina", 1));
        b.addFoe(new Tyrogue("Anna Erinova", 1));
        b.go();
    }
}
