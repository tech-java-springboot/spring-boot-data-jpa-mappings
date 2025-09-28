package com.codeoncewidakash.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codeoncewidakash.constants.SubTVChannelConstant;
import com.codeoncewidakash.entity.Subscriber;
import com.codeoncewidakash.entity.TVChannel;
import com.codeoncewidakash.enums.StatusType;
import com.codeoncewidakash.exception.SubscriberNotFoundExceptoin;
import com.codeoncewidakash.payload.SubscriberRequestDto;
import com.codeoncewidakash.payload.SubscriberResponseDto;
import com.codeoncewidakash.payload.TVChannelShallowResponseDto;
import com.codeoncewidakash.repo.SubscriberRepo;
import com.codeoncewidakash.service.ISubscriberService;
import com.codeoncewidakash.service.ITVChannelService;
import com.codeoncewidakash.util.TVChannelSubscriberUtil;

@Service
public class SubscriberServiceImpl implements ISubscriberService {
	
	private SubscriberRepo subscriberRepo;
	private ITVChannelService channelService;

	public SubscriberServiceImpl(SubscriberRepo subscriberRepo, ITVChannelService channelService) {
		this.subscriberRepo = subscriberRepo;
		this.channelService = channelService;
	}

	@Override
	public String registerSubscriber(SubscriberRequestDto subscriberRequestDto) {
		//1. fetch the channel details based on the channel id's
		Set<Long> channelIds = subscriberRequestDto.channelIds();
		Set<TVChannel> selectedChannels = channelService.channelsById(channelIds);
		
		//2. covert DTO to entity
		Subscriber subscriber = Subscriber.builder()
		.firstName(subscriberRequestDto.firstName())
		.lastName(subscriberRequestDto.lastName())
		.email(subscriberRequestDto.email())
		.phoneNumber(subscriberRequestDto.phoneNumber())
		.city(subscriberRequestDto.city())
		.statusType(StatusType.ACTIVE)
		.build();
		
		if(!selectedChannels.isEmpty())
			subscriber.setChannels(selectedChannels);
		
		//3. save entity to database
		Subscriber createdSubscriber = subscriberRepo.save(subscriber);
		
		return String.format(SubTVChannelConstant.SUB_REGISTER_MSG, createdSubscriber.getId());
	}

	@Override
	public SubscriberResponseDto getSubscriberById(Long id) {
		Subscriber subscriber = getSubscriberByEntityId(id);
		Set<TVChannelShallowResponseDto> shallowTVChannelResponseDto = TVChannelSubscriberUtil.toShallowTVChannelResponseDto(subscriber);
		return new SubscriberResponseDto(subscriber.getId(), subscriber.getFirstName(), subscriber.getLastName(), subscriber.getEmail(), subscriber.getPhoneNumber(), subscriber.getCity(), subscriber.getCreatedOn(), subscriber.getUpdatedOn(), subscriber.getStatusType(), shallowTVChannelResponseDto);
	}

	@Override
	public SubscriberResponseDto getSubscriberByPhoneNumber(Long phoneNumber) {
		Subscriber subscriber = subscriberRepo.findByPhoneNumber(phoneNumber).orElseThrow(() -> new SubscriberNotFoundExceptoin(String.format(SubTVChannelConstant.SUB_PHONE_ERROR_MSG, phoneNumber)));
		Set<TVChannelShallowResponseDto> shallowTVChannelResponseDto = TVChannelSubscriberUtil.toShallowTVChannelResponseDto(subscriber);
		return new SubscriberResponseDto(subscriber.getId(), subscriber.getFirstName(), subscriber.getLastName(), subscriber.getEmail(), subscriber.getPhoneNumber(), subscriber.getCity(), subscriber.getCreatedOn(), subscriber.getUpdatedOn(), subscriber.getStatusType(), shallowTVChannelResponseDto);
	}

	@Override
	public Subscriber getSubscriberByEntityId(Long id) {
		return subscriberRepo.findById(id).orElseThrow(() -> new SubscriberNotFoundExceptoin(String.format(SubTVChannelConstant.SUB_ID_ERROR_MSG, id)));
	}

	@Override
	public Set<SubscriberResponseDto> getAllSubscribers() {
		return subscriberRepo.findAll().stream()
		.map(sub -> new SubscriberResponseDto(sub.getId(), sub.getFirstName(), sub.getLastName(), sub.getEmail(), sub.getPhoneNumber(), sub.getCity(), sub.getCreatedOn(), sub.getUpdatedOn(), sub.getStatusType(), TVChannelSubscriberUtil.toShallowTVChannelResponseDto(sub))).collect(Collectors.toSet());
	}

	@Override
	public String updateSubscriber(Long id, SubscriberRequestDto subscriberRequestDto) {
		Subscriber subscriber = TVChannelSubscriberUtil.convertRequestToEntityForUpdate(getSubscriberByEntityId(id), subscriberRequestDto);
		subscriberRepo.save(subscriber);
		return String.format(SubTVChannelConstant.SUB_UPDATE_MSG, id);
	}

	@Override
	public String updateSubscriberPhoneNumber(Long id, Long phoneNumber) {
		Subscriber subscriber = getSubscriberByEntityId(id);
		if(phoneNumber != null)
			subscriber.setPhoneNumber(phoneNumber);
		subscriberRepo.save(subscriber);
		return String.format(SubTVChannelConstant.SUB_PHONE_UPDATE_MSG, id);
	}

	@Override
	public String updateSubscriberEmail(Long id, String email) {
		Subscriber subscriber = getSubscriberByEntityId(id);
		if(email != null)
			subscriber.setEmail(email);
		subscriberRepo.save(subscriber);
		return String.format(SubTVChannelConstant.SUB_EMAIL_UPDATE_MSG, id);
	}

	@Override
	public String updateSubscriberStatus(Long id, boolean status) {
		Subscriber subscriber = getSubscriberByEntityId(id);
		if(status)
			subscriber.setStatusType(StatusType.ACTIVE);
		else
			subscriber.setStatusType(StatusType.INACTIVE);
		subscriberRepo.save(subscriber);
		return String.format(SubTVChannelConstant.SUB_STATUS_UPDATE_MSG, id);
	}

	@Override
	public void deleteSubscriberById(Long id) {
		Subscriber subscriber = getSubscriberByEntityId(id);
		subscriberRepo.delete(subscriber);
	}

}
