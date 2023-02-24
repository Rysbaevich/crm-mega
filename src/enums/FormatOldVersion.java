package enums;

public class FormatOldVersion {
    private String format;

    private FormatOldVersion(String format) {
        this.format = format;
    }

    public static final FormatOldVersion BOOTCAMP = new FormatOldVersion("Bootcamp");
    public static final FormatOldVersion ORDINARY = new FormatOldVersion("Ordinary");
}
