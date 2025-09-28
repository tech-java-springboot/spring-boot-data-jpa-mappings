package com.codeoncewidakash.payload;

import java.time.LocalDate;

import com.codeoncewidakash.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public record SubscriberShallowReponseDto(
		Long id, 
		String firstName, 
		String lastName, 
		String email, 
		Long phoneNumber, 
		String city, 
		LocalDate createdOn, 
		LocalDate updatedOn, 
		StatusType statusType) 
{
	
}
