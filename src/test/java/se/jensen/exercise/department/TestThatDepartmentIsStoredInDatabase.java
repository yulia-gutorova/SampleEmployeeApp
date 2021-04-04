package se.jensen.exercise.department;

import org.junit.jupiter.api.*;
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

    @DisplayName("Test that departmentDatabaseEntry is stored in database")
    @Test
    @Disabled
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
