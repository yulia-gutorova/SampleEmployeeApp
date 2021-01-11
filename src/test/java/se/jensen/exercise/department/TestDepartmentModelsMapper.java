package se.jensen.exercise.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jensen.api.DepartmentModel;
import se.jensen.api.mapper.DepartmentModelsMapper;
import se.jensen.entity.Department;
import se.jensen.test.category.UnitTest;

//import org.junit.experimental.categories.Category;


import java.util.ArrayList;
import java.util.List;

//@Category(UnitTest.class)

public class TestDepartmentModelsMapper {
    @Test
    public void testDepartmentModelsMapper(){
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(Department.builder()
                .departmentId(1)
                .departmentName("Department1")
                .build());
        List<DepartmentModel> modelList = DepartmentModelsMapper.map(departmentList);
        Assertions.assertEquals(1,modelList.size());
        Assertions.assertEquals(Integer.valueOf(1),modelList.get(0).getDepartmentId());
        Assertions.assertEquals("Department1",modelList.get(0).getDepartmentName());
    }
}
