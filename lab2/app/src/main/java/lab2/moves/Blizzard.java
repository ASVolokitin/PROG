package lab2.moves;

import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;

public class Blizzard extends SpecialMove {
    public Blizzard() {
        super(Type.ICE, 120, 70);
    }

    protected void applyOppEffects(Pokemon pokemon) {
        if (Math.random() <= 0.1) {
            Effect.freeze(pokemon);
        }
    }

    protected String describe() {
        return "uses Blizzard";
    }
}