package lab2.moves;

import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.StatusMove;


public class SwordsDance extends StatusMove {
    public SwordsDance() {
        super(Type.NORMAL, 0, 0); // создали объект типа Move с переданными type, power и accuracy
    }

    protected void applySelfEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.ATTACK, 2); // повысили уровень атаки на 2 для атакующего покемона
    }
    protected String describe() {
        return "uses Sword Dance"; // вывели комментарий об использованной атаке
    }

}