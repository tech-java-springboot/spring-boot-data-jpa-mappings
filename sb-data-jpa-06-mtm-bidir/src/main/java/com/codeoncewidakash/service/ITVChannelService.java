package com.codeoncewidakash.service;

import java.util.List;
import java.util.Set;

import com.codeoncewidakash.entity.TVChannel;
import com.codeoncewidakash.payload.TVChannelRequestDto;
import com.codeoncewidakash.payload.TVChannelResponseDto;

public interface ITVChannelService {
	public String registerChannel(TVChannelRequestDto channelRequestDto);
	public TVChannelResponseDto getTVChannelById(Long id);
	public TVChannelResponseDto getTVChannelByName(String channelName);
	public List<TVChannelResponseDto> getAllTVChannels();
	public List<TVChannelResponseDto> filterChannelByCategory(String categoryName);
	public Set<TVChannel> channelsById(Set<Long> ids);
	public void deleteChannelById(Long id);
}
