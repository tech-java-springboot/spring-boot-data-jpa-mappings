package com.codeoncewidakash.dto;

import java.io.Serializable;
import java.util.List;

public record UserInfoDTO(String firstName, String lastName, String username, String password, Long mobile, List<Long> roleIds) implements Serializable {

}
