package me.ninjasul.demospring51;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository /* @Primary */
public class NinjasulBookRepository implements BookRepositoryInterface {
}
