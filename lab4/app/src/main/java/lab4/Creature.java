package lab4;

import javax.naming.InvalidNameException;

public abstract class Creature {
    private final String name;
    private final String type;
    private boolean isThirsty;
    private boolean isHungry;

    public Creature(String name, String type) {
        this.name = name;
        this.type = type;
        this.isThirsty = false;
        this.isHungry = false;
    }

    public Creature(String name, String type, boolean isThirsty, boolean isHungry) {
        this.name = name;
        this.type = type;
        this.isThirsty = isThirsty;
        this.isHungry = isHungry;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean getThirsty() { return isThirsty; }

    public boolean getHungry() { return isHungry; }

    public void setThirsty(boolean isThirsty) {
        this.isThirsty = isThirsty;
        if (isThirsty) System.out.println(this.name + " захотел пить.");
        else System.out.println(this.name + " уже не хочет пить.");
    }

    public void setHungry(boolean isHungry) {
        this.isHungry = isHungry;
        if (isHungry) System.out.println(this.name + " захотел есть.");
        else System.out.println(this.name + " уже не хочет есть.");
    }

    public String toString() {
        return "This is a " + this.name + ".";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Creature Creature2 = (Creature) obj;
        if (this.name == Creature2.name && this.type == Creature2.type) return true;
        else return false;
    }

    public int hashCode() {
        int [] factorials = {40320, 62880, 28800, 16800, 1600, 2080, 91200, 68000, 88000};
        int res = 0;
        int cnt = 0;
        for (int i = 0; i < name.length(); ++i) {
            res += (int) (Math.pow(factorials[i % factorials.length], factorials[(name.length() - i) % factorials.length]) % 1000007);
        }
        return res;
    }


}
