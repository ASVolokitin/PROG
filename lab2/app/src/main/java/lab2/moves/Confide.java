package lab2.moves;

import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;

public class Confide extends SpecialMove {
    public Confide() {
        super(Type.NORMAL, 0, 0);
    }

    protected void applyOppEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.ACCURACY, -1);
    }

    protected String describe() {
        return "Confides";
    }
}