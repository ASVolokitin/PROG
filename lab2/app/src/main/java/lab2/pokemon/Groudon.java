package lab2.pokemon;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import lab2.moves.Confide;
import lab2.moves.SwordsDance;
import lab2.moves.FireBlast;
import lab2.moves.Rest;


public class Groudon extends Pokemon {
	public Groudon (String name, int level) {
	    super(name, level); // создали объект типа Pokemon с переданными name и level
        this.setStats(100, 150, 140, 100, 90, 90); //задаем базовые характеристики (hp, att, def, spAtt, spDef, speed)
        this.setType(Type.GROUND); // задаем тип покемона
        this.setMove(new Confide(), new SwordsDance(), new FireBlast(), new Rest());
	}
}
