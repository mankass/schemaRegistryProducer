package org.example.schemaregistry

import io.confluent.kafka.serializers.KafkaJsonSerializer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.util.*


@RestController("/api/")
class Controller(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    @GetMapping("/")
    fun send() {
        val props: Properties = Properties()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = KafkaJsonSerializer::class.java
        props["schema.registry.url"] = "http://127.0.0.1:8982"


        val producer = KafkaProducer<String, Message>(props)
        val message = Message(
            firstName = "John",
            lastName = "Doe",
            dateBorn = Instant.now(),
        )
        val producerRecord = ProducerRecord<String, Message>("someTopic2", "someKey", message)
        producer.send(producerRecord).get()
        producer.close()
    }
}