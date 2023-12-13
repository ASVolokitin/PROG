package lab4;

public class Shorty extends Creature implements Alive, Attack{

    public Shorty(ShortyNames Name) {
        super(Name.toString(), "Shorty");
    }
    public void move() {
        System.out.println("Коротышка " + this.getName() + " подошёл ближе.");
    }

    public void confrontation(String victim) {
        System.out.println("Коротышка " + this.getName() + " спрятался от сущности " + victim + " за дерево.");
    }
    public void help(Creature creature) { System.out.println(this.getName() + " стоит около сущности " + creature.getName() + " и не знает что делать.");}
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Shorty Shorty2 = (Shorty) obj;
        if (this.getName() == Shorty2.getName()) return true;
        else return false;
    }

    public String toString() {
        return "Этого коротышку зовут " + this.getName() + ".";
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
