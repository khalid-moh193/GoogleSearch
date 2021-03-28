package Web.Util;

public enum URLs {


    Google("https://www.google.com/"),
    instaBug("https://instabug.com/");
    private String value;

    URLs(String value) {
        this.setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
