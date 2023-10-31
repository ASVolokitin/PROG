package lab2.pokemon;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import lab2.moves.RockSlide;
import lab2.moves.Confide;
import lab2.moves.LowSweep;


public class Tyrogue extends Pokemon {
	public Tyrogue (String name, int level) {
	    super(name, level); // создали объект типа Pokemon с переданными name и level
        this.setStats(35, 35, 35, 35, 35, 35); //задаем базовые характеристики (hp, att, def, spAtt, spDef, speed)
        this.setType(Type.FIGHTING); // задаем тип покемона
        this.setMove(new RockSlide(), new Confide(), new LowSweep());
	}
}
