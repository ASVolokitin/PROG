import java.lang.Math;

public class Scooperfield implements Character, Emotions {
    public void FeelTheBite() {
            System.out.println("Who's biting me?!");
    }
     public void FightingEnemy() {
            if (Math.random() >= 0.5) System.out.println("Scooperfield defeated the opponent.");
            else System.out.println("Scooperfield's unable to fight.");
    }
    public String GetMad() {return "Scooperfield's mad.";}
    public String GetScared() {return "Scooperfield's scared.";}

}