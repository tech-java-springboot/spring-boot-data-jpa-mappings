package com.codeoncewidakash.payload;

public record SubscriberRequestDto(String firstName, String lastName, String email, Long phoneNumber, String city, java.util.Set<Long> channelIds) {

}
