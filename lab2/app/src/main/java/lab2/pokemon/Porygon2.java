package lab2.pokemon;

import ru.ifmo.se.pokemon.Type;
import lab2.moves.DefenseCurl;

public class Porygon2 extends Porygon {
	public Porygon2 (String name, int level) {
	    super(name, level); // создали объект типа Pokemon с переданными name и level
        this.setStats(85, 80, 90, 105, 95, 60); //задаем базовые характеристики (hp, att, def, spAtt, spDef, speed)
        this.addMove(new DefenseCurl());
	}
}
