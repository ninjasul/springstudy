package inflearn.jpa.basic.domain.inherited.tableperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Entity(name = "ALBUM_TABLE_PER_CLASS")
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
}