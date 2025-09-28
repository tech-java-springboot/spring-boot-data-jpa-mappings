package com.codeoncewidakash.payload;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ErrorResponse(String message, LocalDateTime timeStamp) {

}
