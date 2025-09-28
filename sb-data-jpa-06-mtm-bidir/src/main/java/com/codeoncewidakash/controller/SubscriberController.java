package com.codeoncewidakash.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeoncewidakash.constants.SubTVChannelConstant;
import com.codeoncewidakash.payload.SubscriberRequestDto;
import com.codeoncewidakash.payload.SubscriberResponseDto;
import com.codeoncewidakash.service.ISubscriberService;

@RestController
@RequestMapping("/api/v1/subscriber")
public class SubscriberController {

	private ISubscriberService subscriberService;

	public SubscriberController(ISubscriberService subscriberService) {
		this.subscriberService = subscriberService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerSubscriber(@RequestBody SubscriberRequestDto subscriberRequestDto) {
		String registerSubscriberResponse = subscriberService.registerSubscriber(subscriberRequestDto);
		return new ResponseEntity<>(registerSubscriberResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteSubscriber(@PathVariable(name = "id") Long id) {
		subscriberService.deleteSubscriberById(id);
		return ResponseEntity.ok(String.format(SubTVChannelConstant.SUB_DELETED_MSG, id));
	}

	@GetMapping("/get/id/{id}")
	public ResponseEntity<SubscriberResponseDto> getSubscriberById(@PathVariable(name = "id") Long id) {
		SubscriberResponseDto subscriberResponseDto = subscriberService.getSubscriberById(id);
		return ResponseEntity.ok(subscriberResponseDto);
	}
	
	@GetMapping("/get/phone/{number}")
	public ResponseEntity<SubscriberResponseDto> getSubscriberByPhoneNumber(@PathVariable(name = "number") Long id) {
		SubscriberResponseDto subscriberResponseDto = subscriberService.getSubscriberByPhoneNumber(id);
		return ResponseEntity.ok(subscriberResponseDto);
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<Set<SubscriberResponseDto>> getAllSubscribers() {
		return ResponseEntity.ok(subscriberService.getAllSubscribers());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateSubscriber(@PathVariable(name = "id") Long id, @RequestBody SubscriberRequestDto subscriberRequestDto) {
		String updateSubscriberResponse = subscriberService.updateSubscriber(id, subscriberRequestDto);
		return ResponseEntity.ok(updateSubscriberResponse);
	}
	
	@PatchMapping("/update/{id}/phone/{number}")
	public ResponseEntity<String> updateSubscriberPhoneNumber(@PathVariable(name = "id") Long id, @PathVariable(name = "number") Long phoneNumber) {
		String updateSubscriberPhoneNumberReponse = subscriberService.updateSubscriberPhoneNumber(id, phoneNumber);
		return ResponseEntity.ok(updateSubscriberPhoneNumberReponse);
	}
	
	@PatchMapping("/update/{id}/email/{email}")
	public ResponseEntity<String> updateSubscriberEmail(@PathVariable(name = "id") Long id, @PathVariable(name = "email") String email) {
		String updateSubscriberEmailReponse = subscriberService.updateSubscriberEmail(id, email);
		return ResponseEntity.ok(updateSubscriberEmailReponse);
	}
	
	@PatchMapping("/update/{id}/status/{status}")
	public ResponseEntity<String> updateSubscriberStatus(@PathVariable(name = "id") Long id, @PathVariable(name = "status") boolean status) {
		String updateSubscriberStatusReponse = subscriberService.updateSubscriberStatus(id, status);
		return ResponseEntity.ok(updateSubscriberStatusReponse);
	}
	
}
