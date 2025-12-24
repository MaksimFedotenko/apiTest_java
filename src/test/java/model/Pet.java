package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Pet {
    @JsonProperty("id")
    public long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("status")
    public String status;

    @JsonProperty("photoUrls")
    public List<String> photoUrls;
}
