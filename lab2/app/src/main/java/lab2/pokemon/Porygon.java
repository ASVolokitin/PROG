package lab2.pokemon;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import lab2.moves.ShadowBall;
import lab2.moves.Blizzard;

public class Porygon extends Pokemon {
	public Porygon (String name, int level) {
	    super(name, level); // создали объект типа Pokemon с переданными name и level
        this.setStats(65, 60, 79, 85, 75, 40); //задаем базовые характеристики (hp, att, def, spAtt, spDef, speed)
        this.setType(Type.NORMAL); // задаем тип покемона
        this.setMove(new ShadowBall(), new Blizzard());
	}
}
