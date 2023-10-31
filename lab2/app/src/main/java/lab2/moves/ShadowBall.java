package lab2.moves;

import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;

public class ShadowBall extends SpecialMove {
    public ShadowBall() {
        super(Type.GHOST, 80, 100);
    }

    protected void applyOppEffects(Pokemon pokemon) {
        if (Math.random() <= 0.2) {
            pokemon.setMod(Stat.SPECIAL_DEFENSE, -1);
        }
    }

    protected String describe() {
        return "throws a Shadow Ball";
    }
   }