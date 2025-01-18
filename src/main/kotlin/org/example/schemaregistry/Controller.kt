package org.example.schemaregistry

import io.confluent.kafka.serializers.KafkaJsonSerializer
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializerConfig
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.util.*


@RestController("/api/")
class Controller {

    @GetMapping("/")
    fun send() {
        val props: Properties = Properties()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"

        props["schema.registry.url"] = "http://127.0.0.1:8982"
//        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = "io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer"
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = KafkaJsonSchemaSerializer::class.java

//        props[KafkaJsonSchemaSerializerConfig.FAIL_INVALID_SCHEMA] = true
//        props[KafkaJsonSchemaSerializerConfig.AUTO_REGISTER_SCHEMAS] = true
        println("Send message")
        val producer = KafkaProducer<String, Message>(props)
        val message = Message(
            firstName = "John",
            lastName = "Doe",
            dateBorn = Instant.now(),
        )
        //message
        val producerRecord = ProducerRecord<String, Message>("message", "someKey", message)
        producer.send(producerRecord).get()
        producer.close()
    }
}