package se.jensen.exercise.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.entity.Department;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.test.category.UnitTest;

//import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.List;

//@Category(UnitTest.class)

public class TestDepartmentDatabaseEntryMapper {
    @Test
    public void testDepartmentDatabaseEntryMapperMethod1()
    {
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(2)
                .departmentName("Department2")
                .build();
        Department result = DepartmentDatabaseEntryMapper.map(departmentDatabaseEntry);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(Integer.valueOf(2), result.getDepartmentId());
        Assertions.assertEquals("Department2", result.getDepartmentName());
    }
    @Test
    public void testDepartmentDatabaseEntryMapperMethod2()
    {
        Department department = Department.builder()
                .departmentId(10)
                .departmentName("Department10")
                .build();
        DepartmentDatabaseEntry result = DepartmentDatabaseEntryMapper.map(department);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(Integer.valueOf(10), result.getDepartmentId());
        Assertions.assertEquals("Department10", result.getDepartmentName());
    }
    @Test
    public void testDepartmentDatabaseEntryMapperList()
    {
        List<DepartmentDatabaseEntry> list = new ArrayList<>();
        list.add (DepartmentDatabaseEntry.builder()
                .departmentId(11)
                .departmentName("Department11")
                .build());
        List <Department> result = DepartmentDatabaseEntryMapper.map(list);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(list.get(0).getDepartmentId(), result.get(0).getDepartmentId());
        Assertions.assertEquals(list.get(0).getDepartmentName(), result.get(0).getDepartmentName());
    }
}
