package enums;

public enum Format {
    ORDINARY("Обычный курс"),
    BOOTCAMP("Лагерь");

    private String a;

    Format(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }
}