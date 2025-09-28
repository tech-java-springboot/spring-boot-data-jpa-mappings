package com.codeoncewidakash.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeoncewidakash.constants.SubTVChannelConstant;
import com.codeoncewidakash.payload.TVChannelRequestDto;
import com.codeoncewidakash.payload.TVChannelResponseDto;
import com.codeoncewidakash.service.ITVChannelService;

@RestController
@RequestMapping("/api/v1/tv/channel")
public class TVChannelController {
	
	private ITVChannelService channelService;
	
	public TVChannelController(ITVChannelService channelService) {
		this.channelService = channelService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerChannel(@RequestBody TVChannelRequestDto channelRequestDto){
		String registeredChannelResponse = channelService.registerChannel(channelRequestDto);
		return new ResponseEntity<>(registeredChannelResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/fetch/{id}")
	public ResponseEntity<TVChannelResponseDto> getTVChannelById(@PathVariable(name="id") Long channelId){
		TVChannelResponseDto responseDto = channelService.getTVChannelById(channelId);
		return ResponseEntity.ok(responseDto);
	}
	
	@GetMapping("/fetch/name/{name}")
	public ResponseEntity<TVChannelResponseDto> getTVChannelByName(@PathVariable(name = "name") String channelName){
		TVChannelResponseDto responseDto = channelService.getTVChannelByName(channelName);
		return ResponseEntity.ok(responseDto);
	}
	
	@GetMapping("/fetch/all")
	public ResponseEntity<List<TVChannelResponseDto>> getAllTVChannels(){
		return ResponseEntity.ok(channelService.getAllTVChannels());
	}
	
	@GetMapping("/filter/name")
	public ResponseEntity<List<TVChannelResponseDto>> filterTVChannelsByCategory(@RequestParam(name = "category", required = false)String category){
		return ResponseEntity.ok(channelService.filterChannelByCategory(category));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTVChannelById(@PathVariable(name = "id") Long id){
		channelService.deleteChannelById(id);
		return ResponseEntity.ok(String.format(SubTVChannelConstant.TV_CHANNEL_DELETED_MSG, id));
	}
}
