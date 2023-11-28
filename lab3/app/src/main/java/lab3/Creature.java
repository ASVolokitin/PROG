package lab3;

public abstract class Creature {
    private String name;
    private String type;

    public Creature(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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
