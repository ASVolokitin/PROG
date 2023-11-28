package lab3;

public class Scooperfield extends Creature  implements Alive, Attack{
    public Scooperfield() {
        super("Скуперфильд", "Human");
    }
    public void Move() {
        System.out.println("Скуперфильд поднял голову.");
    }

    public void Confrontation(String victim) {
        System.out.println("Скуперфильд попытался избавиться от назойливой сущности " + victim + ".");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Scooperfield Scooperfield2 = (Scooperfield) obj;
        if (this.getName() == Scooperfield2.getName()) return true;
        else return false;
    }

    public String toString() {
        return "Это Скуперфильд.";
    }
    public int hashCode() {
        int [] factorials = {40320, 62880, 28800, 16800, 1600, 2080, 91200, 68000, 88000};
        int cnt = 0;
        int res = 0;
        for (int i = 0; i < this.getType().length(); ++i) {
            cnt += Integer.parseInt(String.valueOf(this.getType().charAt(i)));
        }
        for (int i = 0; i < this.getName().length(); ++i) {
            cnt += Integer.parseInt(String.valueOf(this.getName().charAt(i)));
        }
        for (int i = 0; i < cnt; ++i) {
            res += (int) (Math.pow(factorials[i], factorials[(res - i) % factorials.length]) % 1009);
        }
        return res;
    }
}
