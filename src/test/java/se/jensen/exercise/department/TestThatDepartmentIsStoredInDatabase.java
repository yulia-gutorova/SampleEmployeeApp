package se.jensen.exercise.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("unit")

public class TestThatDepartmentIsStoredInDatabase {

    DepartmentDao departmentDao = mock(DepartmentDao.class);

    // Mock the DB
    private final Integer DEPARTMENTID = 1;
    private final String  DEPARTMENTNAME = "newDepartment";

    @BeforeEach
    public void setUpMock()
    {
        when(departmentDao.save(any(DepartmentDatabaseEntry.class)))
                .thenReturn(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build());
    }

    @Test
    public void testIsStored()
    {
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();
        DepartmentDatabaseEntry departmentDatabaseEntrySaved = departmentDao.save(departmentDatabaseEntry);

        Assertions.assertEquals(departmentDatabaseEntry.getDepartmentId(),departmentDatabaseEntrySaved.getDepartmentId());
        Assertions.assertEquals(departmentDatabaseEntry.getDepartmentName(),departmentDatabaseEntrySaved.getDepartmentName());

    }
}
