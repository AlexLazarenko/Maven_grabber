package home.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private String numFound;
    private ArrayList<Docs> docs;

    public String getNumFound() {
        return numFound;
    }

    public void setNumFound(String numFound) {
        this.numFound = numFound;
    }

    public ArrayList<Docs> getDocs() {
        return docs;
    }

    public void setDocs(ArrayList<Docs> docs) {
        this.docs = docs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (numFound != null ? !numFound.equals(response.numFound) : response.numFound != null) return false;
        return docs != null ? docs.equals(response.docs) : response.docs == null;
    }

    @Override
    public int hashCode() {
        int result = numFound != null ? numFound.hashCode() : 0;
        result = 31 * result + (docs != null ? docs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "numFound='" + numFound + '\'' +
                ", docs=" + docs +
                '}';
    }
}
