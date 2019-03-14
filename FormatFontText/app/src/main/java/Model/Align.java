package Model;

import android.view.Gravity;

public class Align {
    private int keyAlign;
    private String ValueAlign;

    public Align(int keyAlign, String valueAlign) {
        this.keyAlign = keyAlign;
        ValueAlign = valueAlign;
    }

    public int getKeyAlign() {
        return keyAlign;
    }

    public void setKeyAlign(int keyAlign) {
        this.keyAlign = keyAlign;
    }

    public String getValueAlign() {
        return ValueAlign;
    }

    public void setValueAlign(String valueAlign) {
        ValueAlign = valueAlign;
    }
}
