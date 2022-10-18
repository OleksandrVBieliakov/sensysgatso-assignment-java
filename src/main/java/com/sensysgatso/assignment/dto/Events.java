package com.sensysgatso.assignment.dto;

import com.sensysgatso.assignment.model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Events {
    private List<Event> events;
}
