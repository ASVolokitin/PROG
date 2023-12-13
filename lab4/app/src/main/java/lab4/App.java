package lab4;

public class App {
    public static void main(String[] args) {
        Scooperfield Scoop = new Scooperfield(true, false, true, 100);
        System.out.println(Scoop.toString());
        Scooperfield.Imagination imagination = new Scooperfield.Imagination();
        try {
            System.out.println("Скуперфильд задумался над тем, чтобы отменить вознаграждение за своё спасение.");
            Scoop.setAward(0);
        } catch (IllegalAwardDeny e) {
            imagination.setThought("еде");
            imagination.drawImage();
            System.out.println(e.getMessage());
        }

        Scoop.setThirsty(true);
        imagination.setThought("жажде");


        try {
            imagination.drawImage();
            System.out.println("Скуперфильд задумался над тем, чтобы увеличить вознаграждение за своё спасение.");
            Scoop.setAward(10000);
        } catch (IllegalAwardDeny e) {
            System.out.println(e.getMessage());
        }

       // System.out.println(Scoop.emotionalState.toString());
        Chinch Ch = new Chinch("Клоп", false, true);
        System.out.println(Ch.toString());
        Ch.confrontation(Scoop.getName());
        Scoop.confrontation(Ch.getName());
        Scoop.move();
        Shorty Krabs = new Shorty(ShortyNames.KRABS);
        System.out.println(Krabs.toString());
        Krabs.confrontation(Scoop.getName());
        Shorty Miga = new Shorty(ShortyNames.MIGA) {
            public void help(Creature creature) {
                System.out.println(ShortyNames.MIGA.toString() + " помог сущности " + creature.getName() + ".");
            }
        };
        System.out.println(Miga.toString());
        Shorty Julio = new Shorty(ShortyNames.JULIO);
        System.out.println(Julio.toString());
        Miga.move();
        Julio.move();
        Julio.help(Scoop);
        Miga.help(Scoop);
        Scoop.setMuted(false);
        Scoop.speak("Фа-фи-фо! Эфа фяфка, фяфка фрофляфая! Кха!.. Тьфу! Фяфка фрофляфая! Бяфка брофляфая!");
    }
}
