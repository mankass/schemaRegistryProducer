package org.example.schemaregistry

import com.fasterxml.jackson.annotation.JsonProperty
import java.awt.Insets
import java.time.Instant

class Message(
    @JsonProperty
    val firstName: String,
    @JsonProperty
    val lastName: String,
    @JsonProperty
    val dateBorn: Instant,
)