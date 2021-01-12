package se.jensen.exercise.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;

import java.util.ArrayList;
import java.util.List;

@Tag("unit")

public class TestDepartmentDatabaseEntryMapper {

    @DisplayName("Test to convert DepartmentDatabaseEntry to Department")
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

    @DisplayName("Test to convert Department to DepartmentDatabaseEntry")
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

    @DisplayName("Test to convert a list of DepartmentDatabaseEntry to list of Department")
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
