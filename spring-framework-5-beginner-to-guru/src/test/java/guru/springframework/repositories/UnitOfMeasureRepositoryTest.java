package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitOfMeasureRepositoryTest {

    @Autowired
    UnitOfMeasureRepository repository;

    @Test
    @DirtiesContext
    public void findByDescriptionCup() {
        // Given & When
        Optional<UnitOfMeasure> unitOfMeasureOptional = repository.findByDescription("Cup");

        // Then
        assertEquals("Cup", unitOfMeasureOptional.get().getDescription());
    }

    @Test
    public void findByDescriptionTeaspoon() {
        // Given & When
        Optional<UnitOfMeasure> unitOfMeasureOptional = repository.findByDescription("Teaspoon");

        // Then
        assertEquals("Teaspoon", unitOfMeasureOptional.get().getDescription());
    }

    private Object[] parametersForFindByDescription() {
        return new Object[] {
             "\"Teaspoon\"",
             "\"Tablespoon\"",
             "\"Cup\""  ,
             "\"Pinch\"",
             "\"Ounce\"",
             "\"Each\"" ,
             "\"Dash\"" ,
             "\"Pint\"" ,
        };
    }

    @Test
    public void findByDescription() {

        // Given
        List<String> descriptionList = Arrays.asList(
            "Teaspoon",
            "Tablespoon",
            "Cup"  ,
            "Pinch",
            "Ounce",
            "Each" ,
            "Dash" ,
            "Pint"
        );

        for( String description : descriptionList ) {
            // When
            Optional<UnitOfMeasure> unitOfMeasureOptional = repository.findByDescription(description);

            // Then
            assertEquals(description, unitOfMeasureOptional.get().getDescription());
        }
    }
}