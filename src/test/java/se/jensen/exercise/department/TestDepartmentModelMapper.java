package se.jensen.exercise.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import se.jensen.api.DepartmentModel;
import se.jensen.api.mapper.DepartmentModelMapper;
import se.jensen.entity.Department;

@Tag("unit")

public class TestDepartmentModelMapper {

    @DisplayName("Test to convert DepartmentModel to Department")
    @Test
    public void testDepartmentModelMapperMethod1(){
        DepartmentModel departmentModel= DepartmentModel.builder()
                .departmentId(1)
                .departmentName("Development").build();
        Department result= DepartmentModelMapper.map(departmentModel);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(Integer.valueOf(1),result.getDepartmentId());
        Assertions.assertEquals("Development",result.getDepartmentName());
    }

    @DisplayName("Test to convert Department to DepartmentModel")
    @Test
    public void testDepartmentModelMapperMethod2(){
        Department department= Department.builder().departmentId(2).departmentName("Sales").build();
        DepartmentModel result= DepartmentModelMapper.map(department);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(Integer.valueOf(2),result.getDepartmentId());
        Assertions.assertEquals("Sales",result.getDepartmentName());
    }
}


