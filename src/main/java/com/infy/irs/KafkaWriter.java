package com.infy.irs;

import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infy.Exceptions.KafkaExceptions;
import com.infy.irs.Constants.Constants;

/**
 * @author rajesh
 * KafkaWriter Class writes to a Kafka Topic
 * 
 */
public class KafkaWriter extends Exception implements Constants {
	private static final Logger logger = LoggerFactory.getLogger(CryptoGraphic.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 * @param topic
	 * @return
	 * @throws KafkaExceptions
	 */
	public Boolean WriteToKafka(String msg, String topic) throws KafkaExceptions {
		Producer<String, String> producer = null;
		try {
			logger.info("Setting the properties for Kafka");
			Properties properties = new Properties();
			properties.put("bootstrap.servers", "localhost:9092");

			properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			properties.put("ack", "all");

			properties.put("request.timeout.ms", "500");
			logger.info("Writing into Kafka topic");
			producer = new KafkaProducer<>(properties);
			ProducerRecord<String, String> record = new ProducerRecord<>(topic, "1", msg);
			Future<RecordMetadata> result = producer.send(record);

			if (result.isDone()) {
				throw new KafkaExceptions(Constants.KAFKA_EXCEPTION_MESSAGE);
			} else {
				return true;
			}

		}

		catch (Exception e) {
			throw e;
		} finally {
			logger.info("Closing the Kafka Producer");
			producer.close();
		}

	}
}
