package lab2.moves;

import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;

public class DefenseCurl extends SpecialMove {
    public DefenseCurl() {
        super(Type.NORMAL, 0, 0);
    }

    protected void applySelfEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.DEFENSE, 1);
    }

    protected String describe() {
        return "uses Defense Curl";
    }
}