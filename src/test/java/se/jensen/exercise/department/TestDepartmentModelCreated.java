package se.jensen.exercise.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jensen.api.DepartmentModel;
import se.jensen.entity.Department;
import se.jensen.test.category.UnitTest;

//import org.junit.experimental.categories.Category;


//@Category(UnitTest.class)

public class TestDepartmentModelCreated {
    @Test
    public void testDepartmentModelCreated(){
        final Integer departmentId = 1;
        final String departmentName= "newDepartment";

        DepartmentModel departmentModel = DepartmentModel.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build();

        Assertions.assertNotNull(departmentModel);
        Assertions.assertEquals(departmentId, departmentModel.getDepartmentId());
        Assertions.assertEquals(departmentName, departmentModel.getDepartmentName());
    }

   // @Test (expected = NullPointerException.class)
    public void testNonNullExceptionDepartmentId() throws Exception
    {
        DepartmentModel.builder()
            .departmentId(null)
            .departmentName("Department")
            .build();
    }

    //@Test (expected = NullPointerException.class)
    public void testNonNullExceptionDepartmentName () throws Exception
    {
        DepartmentModel.builder()
                .departmentId(1)
                .departmentName(null)
                .build();
    }

}
