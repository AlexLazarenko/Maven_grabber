package home.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Docs {
    private String g;
    private String a;

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Docs docs = (Docs) o;

        if (g != null ? !g.equals(docs.g) : docs.g != null) return false;
        return a != null ? a.equals(docs.a) : docs.a == null;
    }

    @Override
    public int hashCode() {
        int result = g != null ? g.hashCode() : 0;
        result = 31 * result + (a != null ? a.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "g='" + g + '\'' +
                ", a='" + a + '\'' +
                '}';
    }
}
