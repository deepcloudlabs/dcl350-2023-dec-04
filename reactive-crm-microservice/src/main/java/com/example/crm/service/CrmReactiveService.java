package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.repository.CustomerDocumentRepository;
import com.example.crm.service.event.CustomerAcquiredEvent;
import com.example.crm.service.event.CustomerReleasedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CrmReactiveService {
	private final CustomerDocumentRepository customerDocumentRepository;
	private final ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate;
	private final ObjectMapper objectMapper;
	
	public CrmReactiveService(CustomerDocumentRepository customerDocumentRepository,
			ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate, ObjectMapper objectMapper) {
		this.customerDocumentRepository = customerDocumentRepository;
		this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
		this.objectMapper = objectMapper;
	}

	public Mono<CustomerDocument> findCustByEmail(String email) {
		return customerDocumentRepository.findOneByEmail(email);
	}

	public Flux<CustomerDocument> findCustsByPage(int pageNo, int pageSize) {
		return customerDocumentRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	public Mono<CustomerDocument> acquireCustomer(CustomerDocument customer) {
		return customerDocumentRepository.insert(customer)
				    .doOnSuccess(
				    		insertedCustomer->{
				    			try {
				    				var event = new CustomerAcquiredEvent(customer.getIdentity());
				    				String eventAsJson = objectMapper.writeValueAsString(event);
									reactiveKafkaProducerTemplate.send("crm-events", null,eventAsJson).subscribe();
								} catch (JsonProcessingException e) {
									System.err.println(e.getMessage());
								}
				            }
				    );
	}

	public Mono<CustomerDocument> releaseCustomer(String email) {
		return customerDocumentRepository.findOneByEmail(email)
			    .doOnSuccess(
			    		foundCustomer->{
			    			customerDocumentRepository.delete(foundCustomer)
			    			                          .doOnSuccess(removedCustomer ->{
			    			                        	  try {
			    			                        		  var event = new CustomerReleasedEvent(foundCustomer.getIdentity());
			    			                        		  String eventAsJson = objectMapper.writeValueAsString(event);
			    			                        		  reactiveKafkaProducerTemplate.send("crm-events", null,eventAsJson).subscribe();
			    			                        	  } catch (JsonProcessingException e) {
			    			                        		  System.err.println(e.getMessage());
			    			                        	  }			    			                        	  
			    			                          }).subscribe();
			            }
			    );
	}

}
