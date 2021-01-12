package se.jensen.exercise.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import se.jensen.api.DepartmentModel;

@Tag("unit")

public class TestDepartmentModelCreated {

    @DisplayName("Test that DepartmentModel is created")
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

    @DisplayName("Test that DepartmentId can not be null")
     @Test
    public void testNonNullExceptionDepartmentId() {
         Assertions.assertThrows(NullPointerException.class, () ->
         {
             DepartmentModel.builder()
                     .departmentId(null)
                     .departmentName("Department")
                     .build();
         });
     }

    @DisplayName("Test that DepartmentName can not be null")
     @Test
    public void testNonNullExceptionDepartmentName ()
    {
        Assertions.assertThrows(NullPointerException.class, () ->
        {
            DepartmentModel.builder()
                    .departmentId(1)
                    .departmentName(null)
                    .build();
        });
    }

}
