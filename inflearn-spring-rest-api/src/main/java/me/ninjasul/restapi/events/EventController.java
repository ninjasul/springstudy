package me.ninjasul.restapi.events;

import lombok.extern.log4j.Log4j2;
import me.ninjasul.restapi.common.ErrorsResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Controller
@Log4j2
@RequestMapping(value="/api/events", produces= MediaTypes.HAL_JSON_UTF8_VALUE)
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EventValidator eventValidator;

    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors) {

        if( errors.hasErrors() ) {
            return badRequest(errors);
        }

        eventValidator.validate(eventDto, errors);

        if( errors.hasErrors() ) {
            return badRequest(errors);
        }

        Event event = modelMapper.map(eventDto, Event.class);
        event.update();
        Event newEvent = eventRepository.save(event);

        ControllerLinkBuilder selfLinkBuilder = linkTo(EventController.class).slash(newEvent.getId());
        URI createdUri = selfLinkBuilder.toUri();

        log.info("createdUri: {}", createdUri);
        log.info( "event: {}", newEvent);

        EventResource eventResource = new EventResource(event);
        eventResource.add(linkTo(EventController.class).withRel("query-events"));
        eventResource.add(selfLinkBuilder.withRel("update-event"));
        eventResource.add(new Link("/docs/index.html#resources-events-create").withRel("profile"));
        return ResponseEntity.created(createdUri).body(eventResource);
    }

    @GetMapping
    public ResponseEntity queryEvents(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<Event> eventPage = eventRepository.findAll(pageable);
        PagedResources pagedResources = assembler.toResource(eventPage, e -> new EventResource((Event) e));
        pagedResources.add(new Link("/docs/index.html#resources-events-list").withRel("profile"));
        return ResponseEntity.ok(pagedResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEvent(@PathVariable Integer id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);

        if( optionalEvent.isEmpty() ) {
            return ResponseEntity.notFound().build();
        }

        EventResource eventResource = new EventResource(optionalEvent.get());
        eventResource.add(new Link("/docs/index.html#resources-events-get").withRel("profile"));
        return ResponseEntity.ok(eventResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEvent(@PathVariable Integer id,
                                      @RequestBody @Valid EventDto eventDto,
                                      Errors errors ) {

        Optional<Event> optionalEvent = eventRepository.findById(id);

        if( optionalEvent.isEmpty() ) {
            return ResponseEntity.notFound().build();
        }

        if( errors.hasErrors() ) {
            return ResponseEntity.badRequest().build();
        }

        eventValidator.validate(eventDto, errors);

        if( errors.hasErrors() ) {
            return ResponseEntity.badRequest().build();
        }

        Event event = optionalEvent.get();
        modelMapper.map( eventDto, event );
        Event updatedEvent = eventRepository.save(event);
        EventResource eventResource = new EventResource(updatedEvent);
        eventResource.add(new Link("/docs/index.html#resources-events-update").withRel("profile"));

        return ResponseEntity.ok(eventResource);
    }

    private ResponseEntity<ErrorsResource> badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(new ErrorsResource(errors));
    }

}