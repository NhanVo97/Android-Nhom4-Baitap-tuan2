package Model;

public class Color {
    private String keyColor;
    private String valueColor;

    public Color(String keyColor, String valueColor) {
        this.keyColor = keyColor;
        this.valueColor = valueColor;
    }

    public String getKeyColor() {
        return keyColor;
    }

    public void setKeyColor(String keyColor) {
        this.keyColor = keyColor;
    }

    public String getValueColor() {
        return valueColor;
    }

    public void setValueColor(String valueColor) {
        this.valueColor = valueColor;
    }
}
