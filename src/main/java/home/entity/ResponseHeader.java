package home.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseHeader {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseHeader that = (ResponseHeader) o;

        return response != null ? response.equals(that.response) : that.response == null;
    }

    @Override
    public int hashCode() {
        return response != null ? response.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ResponseHeader{" +
                "response=" + response +
                '}';
    }
}
