package ru.se_nata.ati.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducer {

	public KafkaProducer() {
		super();
	}

	private KafkaTemplate<String, String> kafkaTemplate;

	public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	private static final Logger LOGGER= LoggerFactory.getLogger(KafkaProducer.class);
	
	
//	private KafkaTemplate<String, RegulatoryAct> kafkaTemplate;
	
	 public void sendMessage(String message){
	        LOGGER.info(String.format("Message sent -> %s", message));
	        kafkaTemplate.send(Constants.TOPIC_NAME_1, message);
	    }
	
/*	
	public void sendMassage(RegulatoryAct regulatoryact) {
		LOGGER.info(String.format("Meesage sent -> %s",regulatoryact.toString()));

		
		 * Message<RegulatoryAct> message=MessageBuilder .withPayload(regulatoryact)
		 * .setHeader(KafkaHeaders.TOPIC, Constants.TOPIC_NAME_1) .build();
		 * kafkaTemplate.send(message);
		 
	}*/
}
