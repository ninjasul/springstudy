package me.ninjasul.restapi.common;

import me.ninjasul.restapi.events.EventController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.validation.Errors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ErrorsResource extends Resource<Errors> {

    public ErrorsResource(Errors content, Link... links) {
        super(content, links);
        add(linkTo(EventController.class).withRel("index"));
    }
}