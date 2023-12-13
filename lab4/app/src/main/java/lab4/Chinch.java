package lab4;

public class Chinch extends Creature  implements Alive, Attack{
    public Chinch(String name) {
        super(name, "Chinch");
    }

    public Chinch(String name, boolean isThirsty, boolean isHungry) {
        super(name, "Chinch", isThirsty, isHungry);
    }

    public void move() {
        System.out.println("Клоп посеменил дальше.");
    }

    public void confrontation(String victim) {
        System.out.println("Клоп укусил сущность " + victim + " в спину.");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Chinch Chinch2 = (Chinch) obj;
        if (this.getName() == Chinch2.getName()) return true;
        else return false;
    }

    public String toString() {
        return "Этого клопа зовут " + this.getName() + ".";
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
