package se.jensen.exercise.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import se.jensen.entity.Department;


@Tag("unit")

public class TestThatDepartmentIsCreated {

    @Test
    public void testThatDepartmentIsCreated()
    {
        Integer DEPARTMENTID = 1;
        String DEPARTMENTNAME= "newDepartment";

        Department department = Department.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();

        Assertions.assertNotNull(department);
        Assertions.assertEquals(DEPARTMENTID, department.getDepartmentId());
        Assertions.assertEquals(DEPARTMENTNAME, department.getDepartmentName());
    }

}

