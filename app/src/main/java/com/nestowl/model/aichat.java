package com.nestowl.model;

public class aichat {
    String text, value, values;

    public aichat() {
    }

    public aichat(String text, String value, String values) {
        this.text = text;
        this.value = value;
        this.values=values;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
