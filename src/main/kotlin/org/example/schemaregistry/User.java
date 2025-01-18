package org.example.schemaregistry;

import com.fasterxml.jackson.annotation.JsonProperty;

//@io.confluent.kafka.schemaregistry.annotations.Schema(value = "{"
//        + "\"$id\": \"https://acme.com/referrer.json\","
//        + "\"$schema\": \"http://json-schema.org/draft-07/schema#\","
//        + "\"type\":\"object\","
//        + "\"properties\":{\"Ref\":"
//        + "{\"$ref\":\"ref.json#/definitions/ExternalType\"}},\"additionalProperties\":false}",
//        refs = {@io.confluent.kafka.schemaregistry.annotations.SchemaReference(
//                name = "ref.json", subject = "reference")})
public class User {


    @JsonProperty
    public String firstName;

    @JsonProperty
    public String lastName;

    @JsonProperty
    public short age;

    public User() {
    }

    public User(String firstName, String lastName, short age) {
        this(firstName, lastName, age, null);
    }

    public User(String firstName, String lastName, short age, Object o) {
    }
}