package se.jensen.exercise.department;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jensen.entity.Department;

public class TestThatNullPointerExceptionIsThrown {
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
