package lab4;

public class Scooperfield extends Creature implements Alive, Attack{
    private boolean muted;
    private long award;
    EmotionalState emotionalState;

    public Scooperfield(boolean muted, boolean isThirsty, boolean isHungry, long award) {
        super("Скуперфильд", "Human", isThirsty, isHungry);
        this.muted = muted;
        this.award = award;
        this.emotionalState = new EmotionalState();

    }

    private class EmotionalState {
        private int greed;
        private int rage;

        public EmotionalState() {
            this.rage = 0;
            this.greed = 0;
        }

        public EmotionalState(int rage, int greed) {
            this.rage = rage;
            this.greed = greed;
        }

        public int getRage() { return rage; }

        public int getGreed() { return greed; }

        private void setState(boolean newThirsty, boolean newHungry, long newaward) throws IllegalAwardDeny {
            class State {
                private int isThirsty;
                private int isHungry;
                private long award;

                public State(boolean isThirsty, boolean isHungry, long award) {
                    this.isThirsty = isThirsty ? 1 : 0;
                    this.isHungry = isHungry ? 1 : 0;
                    this.award = award;
                }
            }
            State prevstate = new State(getThirsty(), getHungry(), award);
            State newstate = new State(newThirsty, newHungry, newaward);

            if (newstate.award == 0 && (newstate.isThirsty + newstate.isHungry) > 0) {
                throw new IllegalAwardDeny("Вознаграждение нельзя обнулить, Скуперфильд готов отдать всё за своё спасение!");
            }
            else {
                int dgreed = (int) Math.log(newstate.award - prevstate.award) + (newstate.isThirsty - prevstate.isThirsty) * 3 + (newstate.isHungry - prevstate.isHungry);
                this.greed += Math.max(dgreed, 0);
                this.rage += (dgreed - greed) * 0.3 + (newstate.isThirsty - prevstate.isThirsty) * 7 + (newstate.isHungry - prevstate.isHungry) * 5;
                this.rage = Math.max(this.rage, 0);
                if (newstate.award != prevstate.award) { System.out.println("Скуперфильд изменил размер вознаграждения на " + (newstate.award - prevstate.award) + ", теперь оно равно " + newstate.award + " фертингам."); }
                award = newaward;
            }
        }
        public String toString() {
            return ("Уровень жадности Скуперфильда: " + this.greed + ";\nУровень бешенства Скуперфильда: " + this.rage + ".");
        }
    }
    public static class Imagination {

        private String thought;

        public Imagination() {
            this.thought = "ничем";
        }

        public Imagination(String thought) {
            this.thought = thought;
        }

        public void setThought(String thought) {
            this.thought = thought;
        }

        void drawImage(String thought) {
            System.out.println("Скуперфильд думает о " + thought + ".");
        }

        void drawImage() {
            System.out.println("Скуперфильд думает о " + this.thought);
        }

        @Override
        public String toString() {
            return "Текщая мечта Скуперфильда: " + this.thought;
        }
    }
    

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public void setAward(long award) throws IllegalAwardDeny {
        emotionalState.setState(this.getThirsty(), this.getHungry(), award);
    }

    public void move() {
        System.out.println("Скуперфильд поднял голову.");
    }

    public void confrontation(String victim) {System.out.println("Скуперфильд попытался избавиться от назойливой сущности " + victim + ".");}

    public void speak(String speech) throws MuteException {
        if (this.muted) throw new MuteException("У Скуперфильда связан рот!");
        System.out.println(this.getName() + ": " + '"' + speech + '"');
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
