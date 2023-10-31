package lab2.pokemon;

import ru.ifmo.se.pokemon.Type;
import lab2.moves.Rest;

public class PorygonZ extends Porygon {
	public PorygonZ (String name, int level) {
	    super(name, level); // создали объект типа Pokemon с переданными name и level
        this.setStats(85, 80, 70, 135, 75, 90); //задаем базовые характеристики (hp, att, def, spAtt, spDef, speed)
        this.addMove(new Rest());
	}
}
