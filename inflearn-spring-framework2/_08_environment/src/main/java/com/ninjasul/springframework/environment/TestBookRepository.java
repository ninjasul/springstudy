package com.ninjasul.springframework.environment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import static com.ninjasul.springframework.environment.EnvironmentApplication.TEST;

@Repository
@Profile(TEST)
public class TestBookRepository implements BookRepository {
}