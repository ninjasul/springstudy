package inflearn.jpa.basic.domain.mappedsuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    protected String createdBy;
    protected LocalDateTime createdDate;
    protected String modifiedBy;
    protected LocalDateTime lastModifiedDate;
}