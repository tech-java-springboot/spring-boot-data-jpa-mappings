package com.codeoncewidakash.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codeoncewidakash.constants.SubTVChannelConstant;
import com.codeoncewidakash.entity.Subscriber;
import com.codeoncewidakash.entity.TVChannel;
import com.codeoncewidakash.enums.StatusType;
import com.codeoncewidakash.exception.TVChannelNotFoundException;
import com.codeoncewidakash.payload.SubscriberShallowReponseDto;
import com.codeoncewidakash.payload.TVChannelRequestDto;
import com.codeoncewidakash.payload.TVChannelResponseDto;
import com.codeoncewidakash.repo.TVChannelRepo;
import com.codeoncewidakash.service.ITVChannelService;
import com.codeoncewidakash.util.TVChannelSubscriberUtil;

@Service
public class TVChannelServiceImpl implements ITVChannelService {

	private TVChannelRepo channelRepo;
	
	public TVChannelServiceImpl(TVChannelRepo channelRepo) {
		this.channelRepo = channelRepo;
	}
	
	@Override
	public String registerChannel(TVChannelRequestDto channelRequestDto) {
		TVChannel channel = TVChannel.builder()
		.name(channelRequestDto.name())
		.language(channelRequestDto.language())
		.category(channelRequestDto.category())
		.monthlyCost(channelRequestDto.monthlyCost())
		.statusType(StatusType.ACTIVE)
		.build();
		
		Long id = channelRepo.save(channel).getId();
		
		return String.format(SubTVChannelConstant.TV_CHANNEL_REGISTER_MSG, id);
	}

	@Override
	public TVChannelResponseDto getTVChannelById(Long id) {
		TVChannel tvChannel = getTVChannelEntityById(id);
		Set<SubscriberShallowReponseDto> subscribers = TVChannelSubscriberUtil.toShallowSubscriberResponseDto(tvChannel);
		return new TVChannelResponseDto(tvChannel.getId(), tvChannel.getName(), tvChannel.getLanguage(), tvChannel.getCategory(), tvChannel.getMonthlyCost(), tvChannel.getCreatedOn(), tvChannel.getUpdatedOn(), tvChannel.getStatusType(), subscribers);
	}

	private TVChannel getTVChannelEntityById(Long id) {
		return channelRepo.findById(id).orElseThrow(() -> new TVChannelNotFoundException(String.format(SubTVChannelConstant.TV_CHANNEL_ID_ERROR_MSG, id)));
	}

	@Override
	public TVChannelResponseDto getTVChannelByName(String channelName) {
		TVChannel tvChannel = channelRepo.findByName(channelName).orElseThrow(() -> new TVChannelNotFoundException(String.format(SubTVChannelConstant.TV_CHANNEL_NAME_ERROR_MSG, channelName)));
		Set<SubscriberShallowReponseDto> subscribers = TVChannelSubscriberUtil.toShallowSubscriberResponseDto(tvChannel);
		return new TVChannelResponseDto(tvChannel.getId(), tvChannel.getName(), tvChannel.getLanguage(), tvChannel.getCategory(), tvChannel.getMonthlyCost(), tvChannel.getCreatedOn(), tvChannel.getUpdatedOn(), tvChannel.getStatusType(), subscribers);
	}

	@Override
	public List<TVChannelResponseDto> getAllTVChannels() {
		return channelRepo.findAll().stream()
				.map(ch -> new TVChannelResponseDto(ch.getId(), ch.getName(), ch.getLanguage(), ch.getCategory(), ch.getMonthlyCost(), ch.getCreatedOn(), ch.getUpdatedOn(), ch.getStatusType(), TVChannelSubscriberUtil.toShallowSubscriberResponseDto(ch)))
				.toList();
	}
	
	@Override
	public List<TVChannelResponseDto> filterChannelByCategory(String categoryName) {
		return getAllTVChannels().stream()
				.filter(ch -> categoryName==null || categoryName.isBlank() || ch.category().equalsIgnoreCase(categoryName))
				.toList();
	}
	
	@Override
	public Set<TVChannel> channelsById(Set<Long> ids) {
		return channelRepo.findAllById(ids).stream().collect(Collectors.toSet());
	}
	
	@Override
	public void deleteChannelById(Long id) {
		// Get TV Channel
		TVChannel tvChannel = getTVChannelEntityById(id);
		
		// Remove this channel from all subscribers (owning side)
		for(Subscriber subscriber : tvChannel.getSubscribers()) {
			subscriber.getChannels().remove(tvChannel);
		}
		
		// Clear the channel's subscriber list (inverse side)
		tvChannel.getSubscribers().clear();
		
		// Now delete the channel
		channelRepo.delete(tvChannel);
	}

}
