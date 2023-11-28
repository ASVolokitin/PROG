package lab3;

public class App {
    public static void main(String[] args) {
        Scooperfield Scoop = new Scooperfield();
        System.out.println(Scoop.toString());
        Chinch Ch = new Chinch("Клоп");
        System.out.println(Ch.toString());
        Ch.Confrontation(Scoop.getName());
        Scoop.Confrontation(Ch.getName());
        Scoop.Move();
        Shorty Krabs = new Shorty(ShortyNames.KRABS);
        System.out.println(Krabs.toString());
        Krabs.Confrontation(Scoop.getName());
        Shorty Miga = new Shorty(ShortyNames.MIGA);
        System.out.println(Miga.toString());
        Shorty Julio = new Shorty(ShortyNames.JULIO);
        System.out.println(Julio.toString());
        Miga.Move();
        Julio.Move();
    }
}
