package net.santhosh;

import net.santhosh.entity.WikipediaData;
import net.santhosh.repository.WikipediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private WikipediaDataRepository wikipediaDataRepository;

    public KafkaDatabaseConsumer(WikipediaDataRepository wikipediaDataRepository) {
        this.wikipediaDataRepository = wikipediaDataRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.group-id}")
    public void consume(String eventMessage){
        LOGGER.info(String.format("Event Message Received -> %s ", eventMessage));
        WikipediaData wikipediaData = new WikipediaData();
        wikipediaData.setWikiEventData(eventMessage);
        wikipediaDataRepository.save(wikipediaData);
    }
}
