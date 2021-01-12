package se.jensen.exercise.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import se.jensen.dao.DepartmentDatabaseEntry;

@Tag("unit")

public class TestDepartmentDatabaseEntryCreated {

    @DisplayName("Test that DepartmentDatabaseEntry is created")
    @Test
    public void testDepartmentDatabaseEntryCreated()
    {
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(1)
                .departmentName("NewDevelopment")
                .build();
        Assertions.assertNotNull(departmentDatabaseEntry);
        Assertions.assertEquals(Integer.valueOf(1), departmentDatabaseEntry.getDepartmentId());
        Assertions.assertEquals("NewDevelopment", departmentDatabaseEntry.getDepartmentName());
    }
}