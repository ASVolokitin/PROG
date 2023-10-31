package lab2.moves;

import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;

public class Rest extends SpecialMove {
    public Rest() {
        super(Type.PSYCHIC, 0, 0);
    }
    protected void applySelfEffects(Pokemon pokemon) {
        pokemon.restore();
    }

    protected String describe() {
        return "Rests";
    }
}