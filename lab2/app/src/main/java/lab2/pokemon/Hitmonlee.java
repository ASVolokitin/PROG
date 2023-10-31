package lab2.pokemon;

import ru.ifmo.se.pokemon.Type;
import lab2.moves.MegaKick;


public class Hitmonlee extends Tyrogue {
	public Hitmonlee (String name, int level) {
	    super(name, level); // создали объект типа Pokemon с переданными name и level
        this.setStats(50, 120, 53, 35, 110, 87); //задаем базовые характеристики (hp, att, def, spAtt, spDef, speed)
        this.addMove(new MegaKick());
	}
}
