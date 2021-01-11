package se.jensen.exercise.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.test.category.UnitTest;

//import org.junit.experimental.categories.Category;


//@Category(UnitTest.class)

public class TestDepartmentDatabaseEntryCreated {
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