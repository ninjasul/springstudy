package me.ninjasul.restapi.events;

import lombok.extern.log4j.Log4j2;
import me.ninjasul.restapi.accounts.Account;
import me.ninjasul.restapi.accounts.CurrentUser;
import me.ninjasul.restapi.common.ErrorsResource;
import org.checkerframework.checker.units.qual.Current;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.http.HttpResponse;
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
    public ResponseEntity createEvent(  @RequestBody @Valid EventDto eventDto,
                                        Errors errors,
                                        @CurrentUser Account currentUser ) {

        if( errors.hasErrors() ) {
            return badRequest(errors);
        }

        eventValidator.validate(eventDto, errors);

        if( errors.hasErrors() ) {
            return badRequest(errors);
        }

/*
        if( currentUser == null ) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
*/

        Event event = modelMapper.map(eventDto, Event.class);
        event.update();
        event.setManager(currentUser);
        Event newEvent = eventRepository.save(event);

        ControllerLinkBuilder selfLinkBuilder = linkTo(EventController.class).slash(newEvent.getId());
        URI createdUri = selfLinkBuilder.toUri();

        EventResource eventResource = new EventResource(event);
        eventResource.add(linkTo(EventController.class).withRel("query-events"));
        eventResource.add(selfLinkBuilder.withRel("update-event"));
        eventResource.add(new Link("/docs/index.html#resources-events-create").withRel("profile"));
        return ResponseEntity.created(createdUri).body(eventResource);
    }

    @GetMapping
    public ResponseEntity queryEvents(  Pageable pageable,
                                        PagedResourcesAssembler assembler,
                                        @CurrentUser Account currentUser ) {

        Page<Event> eventPage = eventRepository.findAll(pageable);
        PagedResources pagedResources = assembler.toResource(eventPage, e -> new EventResource((Event) e));
        pagedResources.add(new Link("/docs/index.html#resources-events-list").withRel("profile"));

        if( currentUser != null ) {
            pagedResources.add(linkTo(EventController.class).withRel("create-event"));
        }

        return ResponseEntity.ok(pagedResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEvent( @PathVariable Integer id,
                                    @CurrentUser Account currentUser ) {
        Optional<Event> optionalEvent = eventRepository.findById(id);

        if( optionalEvent.isEmpty() ) {
            return ResponseEntity.notFound().build();
        }

        EventResource eventResource = new EventResource(optionalEvent.get());
        eventResource.add(new Link("/docs/index.html#resources-events-get").withRel("profile"));

        if( currentUser != null ) {
            eventResource.add(linkTo(EventController.class).slash(id).withRel("update-event"));
        }

        return ResponseEntity.ok(eventResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEvent(@PathVariable Integer id,
                                      @RequestBody @Valid EventDto eventDto,
                                      Errors errors,
                                      @CurrentUser Account currentUser ) {

        Optional<Event> optionalEvent = eventRepository.findById(id);

        if( optionalEvent.isEmpty() ) {
            return ResponseEntity.notFound().build();
        }

        if( errors.hasErrors() ) {
            return badRequest(errors);
        }

        eventValidator.validate(eventDto, errors);

        if( errors.hasErrors() ) {
            return badRequest(errors);
        }

        Event existingEvent = optionalEvent.get();

        if( !existingEvent.getManager().equals(currentUser) ) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        modelMapper.map( eventDto, existingEvent );
        Event updatedEvent = eventRepository.save(existingEvent);
        EventResource eventResource = new EventResource(updatedEvent);
        eventResource.add(new Link("/docs/index.html#resources-events-update").withRel("profile"));

        return ResponseEntity.ok(eventResource);
    }

    private ResponseEntity<ErrorsResource> badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(new ErrorsResource(errors));
    }

}