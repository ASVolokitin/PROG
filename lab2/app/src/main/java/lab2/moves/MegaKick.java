package lab2.moves;

import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.PhysicalMove;


public class MegaKick extends PhysicalMove {
    public MegaKick() {
        super(Type.NORMAL, 120, 75);
    }

    protected double calcBaseDamage(Pokemon att, Pokemon def) {
        return super.calcBaseDamage(att, def);
    }

    protected String describe() {
        return "uses Mega Kick";
    }
}