package se.jensen.exercise.department;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.jensen.entity.Department;

public class TestThatNullPointerExceptionIsThrown {

    @DisplayName("Test that departmentId can not be null")
    @Test
    public void testDepartmentIdNotNull()
    {

        Assertions.assertThrows(NullPointerException.class, ()->

        {Department.builder()
                .departmentId(null)
                .departmentName("Department1")
                .build();
    });
    }

    @DisplayName("Test that departmentName can not be null")
    @Disabled
    @Test
    public void testDepartmentNameNotNull() {

        Assertions.assertThrows(NullPointerException.class, () ->
        {
            Department.builder()
                    .departmentId(1)
                    .departmentName(null)
                    .build();

        });
    }

}
