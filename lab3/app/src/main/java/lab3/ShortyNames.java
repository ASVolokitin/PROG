package lab3;

public enum ShortyNames {
    KRABS("Крабс"),MIGA("Мига"),JULIO("Жулио");

    private String name;

    ShortyNames(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
