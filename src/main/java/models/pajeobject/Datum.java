package models.pajeobject;

import java.util.Objects;

public class Datum {
    public int id;
    public String email;
    public String first_name;
    public String last_name;
    public String avatar;

    public Datum() {
        super();
    }

    public Datum(int id, String email, String first_name, String last_name, String avatar) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Datum datum = (Datum) o;
        return id == datum.id && Objects.equals(email, datum.email) && Objects.equals(first_name, datum.first_name) && Objects.equals(last_name, datum.last_name) && Objects.equals(avatar, datum.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, first_name, last_name, avatar);
    }

    @Override
    public String toString() {
        return "Datum{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
