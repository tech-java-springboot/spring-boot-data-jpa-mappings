package com.codeoncewidakash.service;

import java.util.Set;

import com.codeoncewidakash.entity.Subscriber;
import com.codeoncewidakash.payload.SubscriberRequestDto;
import com.codeoncewidakash.payload.SubscriberResponseDto;

public interface ISubscriberService {
	public String registerSubscriber(SubscriberRequestDto subscriberRequestDto);
	public SubscriberResponseDto getSubscriberById(Long id);
	public SubscriberResponseDto getSubscriberByPhoneNumber(Long phoneNumber);
	public Subscriber getSubscriberByEntityId(Long id);
	public Set<SubscriberResponseDto> getAllSubscribers();
	public String updateSubscriber(Long id, SubscriberRequestDto subscriberRequestDto);
	public String updateSubscriberPhoneNumber(Long id, Long phoneNumber);
	public String updateSubscriberEmail(Long id, String email);
	public String updateSubscriberStatus(Long id, boolean status);
	public void deleteSubscriberById(Long id);
}
