package lab2.moves;

import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.PhysicalMove;


public class RockSlide extends PhysicalMove {
    public RockSlide() {
        super(Type.ROCK, 75, 90);
    }

    protected void applyOppEffects(Pokemon pokemon) {
        if (Math.random() <= 0.3) {
            Effect.flinch(pokemon);
        }
    }

    protected String describe() {
        return "starts a Rock Slide";
    }
}
