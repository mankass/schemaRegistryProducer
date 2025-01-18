package org.example.schemaregistry

import com.fasterxml.jackson.annotation.JsonProperty
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaString
import java.awt.Insets
import java.time.Instant

//@JsonSchemaInject(
//    strings = [
//        JsonSchemaString(path = "javaType", value = "org.example.schemaregistry.Message")
//    ]
//)
//@io.confluent.kafka.schemaregistry.annotations.Schema(value="{"
//        + "\"$id\": \"https://acme.com/referrer.json\","
//        + "\"$schema\": \"http://json-schema.org/draft-07/schema#\","
//        + "\"type\":\"object\","
//        + "\"properties\":{\"Ref\":"
//        + "{\"$ref\":\"ref.json#/definitions/ExternalType\"}},\"additionalProperties\":false}",
//    refs={@io.confluent.kafka.schemaregistry.annotations.SchemaReference(
//        name="ref.json", subject="reference")})
class Message(
    @JsonProperty
    val firstName: String,
    @JsonProperty
    val lastName: String,
    @JsonProperty
    val dateBorn: Instant,
)