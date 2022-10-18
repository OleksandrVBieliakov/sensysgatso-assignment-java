package com.sensysgatso.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensysgatso.assignment.dto.Events;
import com.sensysgatso.assignment.dto.Violations;
import com.sensysgatso.assignment.dto.ViolationsSummary;
import com.sensysgatso.assignment.model.Event;
import com.sensysgatso.assignment.model.Violation;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.sensysgatso.assignment.service.impl.EventProcessorServiceImpl.REDLIGHT;
import static com.sensysgatso.assignment.service.impl.EventProcessorServiceImpl.SPEED;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventScenarioTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private int saveEvents() throws Exception {
        Resource resource = new ClassPathResource("events.json");

        List<Event> events = mapper.readValue(resource.getFile(), Events.class).getEvents();

        for (Event event : events) {
            mockMvc.perform(post("/event")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(event)))
                    .andExpect(status().isCreated());
        }
        return events.size();
    }

    private List<Event> getEvents() throws Exception {
        String content = mockMvc.perform(get("/event"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        return mapper.readValue(content, Events.class).getEvents();
    }

    private void processEvents() throws Exception {
        mockMvc.perform(post("/event/process"))
                .andExpect(status().isOk());
    }

    private List<Violation> getViolations(Boolean paid) throws Exception {
        String url = "/violation";
        if (paid != null)
            if (paid)
                url += "/paid";
            else
                url += "/unpaid";

        String content = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        return mapper.readValue(content, Violations.class).getViolations();
    }

    private void getViolation(UUID id, boolean found) throws Exception {
        mockMvc.perform(get("/violation/" + id))
                .andExpect(found ? status().isOk() : status().isNotFound());
    }

    private ViolationsSummary getViolationsSummary() throws Exception {
        String content = mockMvc.perform(get("/violation/summary"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        return mapper.readValue(content, ViolationsSummary.class);
    }

    private void payViolation(UUID id) throws Exception {
        mockMvc.perform(post("/violation/" + id + "/pay"))
                .andExpect(status().isOk());
    }

    private void checkPayments(int paidCount, int unpaidCount) throws Exception {
        List<Violation> paidViolations = getViolations(true);
        assertThat(paidViolations.size(), Matchers.equalTo(paidCount));

        List<Violation> unpaidViolations = getViolations(false);
        assertThat(unpaidViolations.size(), Matchers.equalTo(unpaidCount));

        BigDecimal payedFine = paidViolations.stream().map(Violation::getFine).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal unpayedFine = unpaidViolations.stream().map(Violation::getFine).reduce(BigDecimal.ZERO, BigDecimal::add);

        ViolationsSummary summary = getViolationsSummary();
        assertThat(summary.getPaid().getCount(), Matchers.equalTo(paidCount));
        assertThat(summary.getUnpaid().getCount(), Matchers.equalTo(unpaidCount));
        assertThat(summary.getPaid().getFine(), Matchers.equalTo(payedFine));
        assertThat(summary.getUnpaid().getFine(), Matchers.equalTo(unpayedFine));
    }

    private void payViolations(int payCount) throws Exception {
        List<Violation> unpaidViolations = getViolations(false);

        for (int i = 0; i < payCount; i++) {
            payViolation(unpaidViolations.get(i).getId());
        }
    }

    @Test
    public void playScenario() throws Exception {
        int totalEventsCount = saveEvents();

        List<Event> events = getEvents();
        assertThat(events.size(), Matchers.equalTo(totalEventsCount));

        Map<String, Long> eventTypes = events.stream()
                .collect(groupingBy(Event::getEventType, counting()));

        int speedViolationCount = eventTypes.getOrDefault(SPEED, 0L).intValue();
        int redlightViolationCount = eventTypes.getOrDefault(REDLIGHT, 0L).intValue();
        int totalViolationCount = speedViolationCount + redlightViolationCount;

        //events where saved but not precessed yet, no violations were created
        List<Violation> violations = getViolations(null);
        assertThat(violations.size(), Matchers.equalTo(0));

        //force events processing
        processEvents();

        violations = getViolations(null);
        assertThat(violations.size(), Matchers.equalTo(totalViolationCount));

        //just get one violation
        getViolation(violations.get(0).getId(), true);
        //try not found
        getViolation(UUID.randomUUID(), false);

        //initially all violations are unpaid

        checkPayments(0, totalViolationCount);

        //pay

        int payCount = 3;

        payViolations(payCount);

        //now we have paid violations, let's check

        checkPayments(payCount, totalViolationCount - payCount);
    }
}
