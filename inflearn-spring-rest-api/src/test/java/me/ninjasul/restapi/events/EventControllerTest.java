package me.ninjasul.restapi.events;

import me.ninjasul.restapi.common.BaseControllerTest;
import me.ninjasul.restapi.common.TestDescription;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class EventControllerTest extends BaseControllerTest {

    @Autowired
    EventRepository eventRepository;

    @Test
    @TestDescription("정상적으로 이벤트를 생성하는 테스트")
    public void createEvent() throws Exception {

        // Given
        EventDto eventDto = EventDto.builder()
                            .name("Spring")
                            .description("REST API Development with Spring")
                            .beginEnrollmentDateTime(LocalDateTime.of(2018, 12, 10, 18, 40))
                            .closeEnrollmentDateTime(LocalDateTime.of( 2018, 12, 20, 18, 0))
                            .beginEventDateTime(LocalDateTime.of(2018, 12, 25, 9, 0))
                            .endEventDateTime(LocalDateTime.of( 2018, 12, 26, 18, 0))
                            .basePrice(100)
                            .maxPrice(200)
                            .limitOfEnrollment(100)
                            .location("강남역 D2 스타텁 팩토리")
                            .build();

        // When & Then
        mockMvc.perform(post("/api/events/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(eventDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("free").value(false))
                .andExpect(jsonPath("offline").value(true))
                .andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.name()))
                .andDo(document(
                    "create-event",
                    links(
                        linkWithRel("self").description("link to self"),
                        linkWithRel("query-events").description("link to query events"),
                        linkWithRel("update-event").description("link to update an existing event"),
                        linkWithRel("profile").description("link to profile")
                    ),
                    requestHeaders(
                        headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                        headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                    ),
                    requestFields(
                        fieldWithPath("name").description("Name of a new event"),
                        fieldWithPath("description").description("description of a new event"),
                        fieldWithPath("beginEnrollmentDateTime").description("Enrollment Beginning Date Time of a new event"),
                        fieldWithPath("closeEnrollmentDateTime").description("Enrollment Closing Date Time of a new event"),
                        fieldWithPath("beginEventDateTime").description("Event Beginning Date Time of a new event"),
                        fieldWithPath("endEventDateTime").description("Event End Date Time of a new event"),
                        fieldWithPath("location").description("Location of a new event"),
                        fieldWithPath("basePrice").description("Base Price of a new event"),
                        fieldWithPath("maxPrice").description("Maximum Price of a new event"),
                        fieldWithPath("limitOfEnrollment").description("limitOfEnrollment of a new event")
                    ),
                    responseHeaders(
                        headerWithName(HttpHeaders.LOCATION).description("location header"),
                        headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                    ),
                    responseFields(
                        fieldWithPath("id").description("Id of a new event"),
                        fieldWithPath("name").description("Name of a new event"),
                        fieldWithPath("description").description("description of a new event"),
                        fieldWithPath("beginEnrollmentDateTime").description("Enrollment Beginning Date Time of a new event"),
                        fieldWithPath("closeEnrollmentDateTime").description("Enrollment Closing Date Time of a new event"),
                        fieldWithPath("beginEventDateTime").description("Event Beginning Date Time of a new event"),
                        fieldWithPath("endEventDateTime").description("Event End Date Time of a new event"),
                        fieldWithPath("location").description("Location of a new event"),
                        fieldWithPath("basePrice").description("Base Price of a new event"),
                        fieldWithPath("maxPrice").description("Maximum Price of a new event"),
                        fieldWithPath("limitOfEnrollment").description("limitOfEnrollment of a new event"),
                        fieldWithPath("free").description("It tells whether the event is free of charge or not."),
                        fieldWithPath("offline").description("It tells whether the event is offline or not"),
                        fieldWithPath("eventStatus").description("Event Status"),
                        fieldWithPath("_links.self.href").description("link to self"),
                        fieldWithPath("_links.query-events.href").description("link to query event list"),
                        fieldWithPath("_links.update-event.href").description("link to update an existing event"),
                        fieldWithPath("_links.profile.href").description("link to profile")
                    )
                ))
        ;

    }

    @Test
    @TestDescription("입력 받을 수 없는 값을 사용한 경우 에러가 발생하는 테스트")
    public void createEvent_Bad_Request() throws Exception {
        Event event = Event.builder()
                .name("Spring")
                .description("REST API Development with Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2018, 12, 10, 18, 40))
                .closeEnrollmentDateTime(LocalDateTime.of( 2018, 12, 20, 18, 0))
                .beginEventDateTime(LocalDateTime.of(2018, 12, 25, 9, 0))
                .endEventDateTime(LocalDateTime.of( 2018, 12, 26, 18, 0))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2 스타텁 팩토리")
                .free(true)
                .offline(false)
                .eventStatus(EventStatus.PUBLISHED)
                .build();

        mockMvc.perform(post("/api/events/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;

    }

    @Test
    @TestDescription("입력 값이 비어 있는 경우에 에러가 발생하는 테스트")
    public void createEvent_BadRequest_Empty_Input() throws Exception {
        EventDto eventDto = EventDto.builder().build();

        mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(objectMapper.writeValueAsString(eventDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @TestDescription("입력 값이 잘못된 경우에 에러가 발생하는 테스트")
    public void createEvent_BadRequest_Wrong_Input() throws Exception {
        EventDto eventDto = EventDto.builder()
                .name("Spring")
                .description("REST API Development with Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2018, 12, 10, 18, 40))
                .closeEnrollmentDateTime(LocalDateTime.of( 2018, 12, 5, 18, 0))
                .beginEventDateTime(LocalDateTime.of(2018, 12, 25, 9, 0))
                .endEventDateTime(LocalDateTime.of( 2018, 12, 12, 18, 0))
                .basePrice(10000)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2 스타텁 팩토리")
                .build();

        mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(objectMapper.writeValueAsString(eventDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("content[0].objectName").exists())
                .andExpect(jsonPath("content[0].defaultMessage").exists())
                .andExpect(jsonPath("content[0].code").exists())
                .andExpect(jsonPath("_links.index").exists())
        ;
    }

    @Test
    @TestDescription("이벤트를 30개 생성한 후 2번째 페이지 조회 테스트(한 페이지당 이벤트는 10개)")
    public void queryEvents() throws Exception {
        // Given
        IntStream.range(0, 30).forEach(this::generateEvent);

        // When & Then
        mockMvc.perform(get("/api/events")
                        .param("size", "10")
                        .param("page", "1")
                        .param("sort", "name,DESC"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.eventList[0].id").exists())
                .andExpect(jsonPath("_embedded.eventList[0]._links.self").exists())
                .andExpect(jsonPath("page").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.profile").exists())
        ;
    }

    @Test
    @TestDescription("이벤트 하나를 조회하기")
    public void getEvent() throws Exception {

        // Given
        Event event = generateEvent(0);

        // When & Then
        mockMvc.perform(get("/api/events/{id}", event.getId()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("_links.self").exists())
                    .andExpect(jsonPath("_links.profile").exists())
                    .andDo(document("get-an-event"))
        ;
    }

    @Test
    @TestDescription("없는 이벤트 조회하기")
    public void getEvent404() throws Exception {
        // Given & When & Then
        mockMvc.perform(get("/api/events/99975"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    @TestDescription("이벤트를 정상적으로 수정하기")
    public void updateEvent() throws Exception {

        // Given
        Event event = generateEvent(200);

        EventDto eventDto = modelMapper.map( event, EventDto.class );
        String eventName = "Updated Event";
        eventDto.setName(eventName);

        // When & Then
        mockMvc.perform(put("/api/events/{id}", event.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(eventName))
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.profile").exists())
        ;
    }

    @Test
    @TestDescription("입력데이터가 비어있는 경우 수정 실패")
    public void updateEvent400_Empty() throws Exception {

        // Given
        Event event = generateEvent(200);
        EventDto eventDto = new EventDto();

        // When & Then
        mockMvc.perform(put("/api/events/{id}", event.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @TestDescription("BasePrice가 MaxPrice 보다 큰 경우 수정 실패")
    public void updateEvent400_Wrong() throws Exception {

        // Given
        Event event = generateEvent(200);

        EventDto eventDto = modelMapper.map( event, EventDto.class );
        eventDto.setBasePrice(100000);
        eventDto.setMaxPrice(500);

        // When & Then
        mockMvc.perform(put("/api/events/{id}", event.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @TestDescription("존재하지 않는 이벤트 수정 실패")
    public void updateEvent404() throws Exception {

        // Given
        Event event = generateEvent(200);
        EventDto eventDto = modelMapper.map( event, EventDto.class );

        // When & Then
        mockMvc.perform(put("/api/events/1231233")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventDto)))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }

    private Event generateEvent(int i) {
        String formattedIndex = String.format("%02d", i);
        Event event = Event.builder()
                            .name("Event " + formattedIndex)
                            .description("Test Event " + formattedIndex)
                            .beginEnrollmentDateTime(LocalDateTime.of(2018, 12, 10, 18, 40))
                            .closeEnrollmentDateTime(LocalDateTime.of( 2018, 12, 20, 18, 0))
                            .beginEventDateTime(LocalDateTime.of(2018, 12, 25, 9, 0))
                            .endEventDateTime(LocalDateTime.of( 2018, 12, 26, 18, 0))
                            .basePrice(100)
                            .maxPrice(200)
                            .limitOfEnrollment(100)
                            .location("강남역 D2 스타텁 팩토리")
                            .free(false)
                            .offline(true)
                            .eventStatus(EventStatus.DRAFT)
                            .build();

        return eventRepository.save(event);
    }
}
