package com.codeoncewidakash.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codeoncewidakash.constants.SubTVChannelConstant;
import com.codeoncewidakash.entity.TVChannel;
import com.codeoncewidakash.enums.StatusType;
import com.codeoncewidakash.payload.TVChannelRequestDto;
import com.codeoncewidakash.repo.TVChannelRepo;
import com.codeoncewidakash.service.impl.TVChannelServiceImpl;

@ExtendWith(MockitoExtension.class)
class TVChannelServiceImplTest {
	
	@InjectMocks
	private TVChannelServiceImpl channelServiceImpl;
	
	@Mock
	private TVChannelRepo channelRepo;
	
	@Test
	void registerChannelTest() {
		//1. prepare Request DTO object
		TVChannelRequestDto channelRequestDto = new TVChannelRequestDto("Start Gold", "Hindi", "Entertainment", 50.0, null);
		
		//2. convert Request DTO to Entity
		TVChannel tvChannel = TVChannel.builder()
		.id(101L)
		.name(channelRequestDto.name())
		.language(channelRequestDto.language())
		.category(channelRequestDto.category())
		.monthlyCost(channelRequestDto.monthlyCost())
		.statusType(StatusType.ACTIVE)
		.build();
		
		//3. mock Repository call
		when(channelRepo.save(tvChannel)).thenReturn(tvChannel);
		
		//4. invoke Service method
		String response = channelServiceImpl.registerChannel(channelRequestDto);
		
		//5. assert the results
		assertEquals(channelRequestDto.name(), tvChannel.getName());
		verify(channelRepo).save(any(TVChannel.class));
		assertEquals(101L, tvChannel.getId());
		assertEquals(String.format(SubTVChannelConstant.TV_CHANNEL_REGISTER_MSG, tvChannel.getId()), response);
	}
}
