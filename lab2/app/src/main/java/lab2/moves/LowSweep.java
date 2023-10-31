package lab2.moves;

import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.PhysicalMove;

public class LowSweep extends PhysicalMove {
    public LowSweep() {
        super(Type.FIGHTING, 65, 100);
    }

    protected void applyOppEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.SPEED, -1);
    }

    protected String describe() {
        return "Low Sweeps";
    }
}