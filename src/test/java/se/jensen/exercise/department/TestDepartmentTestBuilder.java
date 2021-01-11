package se.jensen.exercise.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;
import se.jensen.test.category.UnitTest;

//import org.junit.experimental.categories.Category;


//@Category(UnitTest.class)

    public class TestDepartmentTestBuilder {
        @Test
        public void TestDepartmentTestBuilder()
        {
            DepartmentTestBuilder testBuilder = new DepartmentTestBuilder();
            Department result = testBuilder.build();
            Assertions.assertNotNull(result);
            Assertions.assertEquals(Integer.valueOf(1), result.getDepartmentId());
            Assertions.assertEquals("Development", result.getDepartmentName());
        }
    }




