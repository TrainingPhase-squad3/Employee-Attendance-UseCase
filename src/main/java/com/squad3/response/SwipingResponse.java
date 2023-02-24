package com.squad3.response;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SwipingResponse {

	private LocalDate date;
	private LocalTime swipIn;
	private LocalTime swipOut;

}
