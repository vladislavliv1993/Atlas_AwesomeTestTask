package models.pajeobject;

import java.util.Objects;

public class Support {
    public String url;
    public String text;

    public Support() {
        super();
    }

    public Support(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Support support = (Support) o;
        return Objects.equals(url, support.url) && Objects.equals(text, support.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, text);
    }

    @Override
    public String toString() {
        return "Support{" +
                "url='" + url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
