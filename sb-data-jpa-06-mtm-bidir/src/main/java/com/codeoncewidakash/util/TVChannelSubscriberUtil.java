package com.codeoncewidakash.util;

import java.util.Set;
import java.util.stream.Collectors;

import com.codeoncewidakash.entity.Subscriber;
import com.codeoncewidakash.entity.TVChannel;
import com.codeoncewidakash.payload.SubscriberRequestDto;
import com.codeoncewidakash.payload.SubscriberResponseDto;
import com.codeoncewidakash.payload.SubscriberShallowReponseDto;
import com.codeoncewidakash.payload.TVChannelShallowResponseDto;

public class TVChannelSubscriberUtil {
	
	private TVChannelSubscriberUtil() {

	}
	
	public static Set<SubscriberShallowReponseDto> toShallowSubscriberResponseDto(TVChannel tvChannel) {
		return tvChannel.getSubscribers()
				.stream()
				.map(ch -> new SubscriberShallowReponseDto(ch.getId(), ch.getFirstName(), ch.getLastName(), ch.getEmail(), ch.getPhoneNumber(), ch.getCity(), ch.getCreatedOn(), ch.getUpdatedOn(), ch.getStatusType()))
				.collect(Collectors.toSet());
	} 
	
	public static Set<TVChannelShallowResponseDto> toShallowTVChannelResponseDto(Subscriber subscriber) {
		return subscriber.getChannels()
				.stream()
				.map(ch -> new TVChannelShallowResponseDto(ch.getId(), ch.getName(), ch.getLanguage(), ch.getName(), ch.getMonthlyCost(), ch.getCreatedOn(), ch.getUpdatedOn(), ch.getStatusType()))
				.collect(Collectors.toSet());
	}
	
	public static Subscriber convertRequestToEntityForUpdate(Subscriber subscriber, SubscriberRequestDto subscriberRequestDto ) {
		if(subscriberRequestDto.firstName() != null)
			subscriber.setFirstName(subscriberRequestDto.firstName());
		if(subscriberRequestDto.lastName() != null)
			subscriber.setLastName(subscriberRequestDto.lastName());
		if(subscriber.getEmail() != null)
			subscriber.setEmail(subscriberRequestDto.email());
		if(subscriber.getPhoneNumber() != null)
			subscriber.setPhoneNumber(subscriberRequestDto.phoneNumber());
		if(subscriber.getCity() != null)
			subscriber.setCity(subscriberRequestDto.city());
		return subscriber;
	}
}
