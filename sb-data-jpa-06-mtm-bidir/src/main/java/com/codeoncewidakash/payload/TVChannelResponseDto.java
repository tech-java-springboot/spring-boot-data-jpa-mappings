package com.codeoncewidakash.payload;

import java.time.LocalDate;
import java.util.Set;

import com.codeoncewidakash.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public record TVChannelResponseDto(
		Long id, 
		String name, 
		String language, 
		String category, 
		Double monthlyCost,
		LocalDate createdOn,
		LocalDate updatedOn,
		StatusType statusType,
		Set<SubscriberShallowReponseDto> subscribers
		) 
{
	
}
