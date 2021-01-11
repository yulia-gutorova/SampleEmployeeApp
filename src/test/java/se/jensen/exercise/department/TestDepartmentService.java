package se.jensen.exercise.department;
import liquibase.pro.packaged.D;
import net.minidev.json.JSONUtil;
import java.nio.channels.ScatteringByteChannel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.jensen.entity.Department;

import se.jensen.test.category.UnitTest;
import se.jensen.dao.*;
import se.jensen.service.*;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;

//import org.junit.experimental.categories.Category;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.*;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

//@Category(UnitTest.class)

public class TestDepartmentService {

    DepartmentDao departmentDao = mock(DepartmentDao.class);
    @InjectMocks
    DepartmentService departmentServiceImpl = new DepartmentServiceImpl();

    private final Integer DEPARTMENTID = Integer.valueOf(1);
    private final String DEPARTMENTNAME = "Development";

//-------------------------------------------------------------------------------------------------------------
    @BeforeEach
    public void setUpp()
    {
        MockitoAnnotations.initMocks(this);

    // mock  departmentDao.findById()
        when(departmentDao.findById(DEPARTMENTID))
                .thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build()));

    // mock departmentDao.findAll()
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();
        List<DepartmentDatabaseEntry> list = new ArrayList<>();
        list.add(departmentDatabaseEntry);
        when(departmentDao.findAll()).thenReturn(list);

        // mock departmentDao.save()
        when(departmentDao.save(any()))
                .thenReturn(DepartmentDatabaseEntry.builder()
                        .departmentId(DEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build());

    // mock departmentDao.delete()
        doNothing().when(departmentDao).delete(any());
    }
//-------------------------------------------------------------------------------------------------------------

    @Test
    public void  testGetAllDepartments()
    {
        List <Department> allDepartments = departmentServiceImpl.getDepartments(); //findAll()

        Assertions.assertNotNull(allDepartments);
        Assertions.assertEquals(1, allDepartments.size());
        Assertions.assertEquals(DEPARTMENTID , allDepartments.get(0).getDepartmentId());
        Assertions.assertEquals(DEPARTMENTNAME, allDepartments.get(0).getDepartmentName());

        verify(departmentDao, atLeastOnce()).findAll();
        verify(departmentDao, times(1)).findAll();
    }
    //-------------------------------------------------------------------------------------------------------------

    @Test
    public void testGetDepartmentById ()
    {
        Department department = departmentServiceImpl.getDepartmentById(DEPARTMENTID);  //findById()

        Assertions.assertNotNull(department);
        Assertions.assertEquals(DEPARTMENTID , department.getDepartmentId());
        Assertions.assertEquals(DEPARTMENTNAME, department.getDepartmentName());

        verify(departmentDao, atLeastOnce()).findById(DEPARTMENTID);
        verify(departmentDao, times(1)).findById(any(Integer.class));
    }
    //-------------------------------------------------------------------------------------------------------------

    @Test
    public void testCreateDepartment()  //findByAll, save
    {
    // mock  departmentDao.findById()
        when(departmentDao.findById(DEPARTMENTID)).thenReturn(Optional.empty());

        Department departmentToCreate = Department.builder().departmentId(DEPARTMENTID).departmentName(DEPARTMENTNAME).build();

        Department createdDepartment = departmentServiceImpl.create( departmentToCreate);

        Assertions.assertNotNull(createdDepartment);
        Assertions.assertEquals(DEPARTMENTID, createdDepartment.getDepartmentId());
        Assertions.assertEquals(DEPARTMENTNAME, createdDepartment.getDepartmentName());

        verify(departmentDao, times(1)).findById(any(Integer.class));
        verify(departmentDao, times(1)).save(any());
    }
    //-------------------------------------------------------------------------------------------------------------

    @Test
    public void testUpdateDepartment()  //findById, save
    {
        Integer NEWDEPARTMENTID = Integer.valueOf(2);
    // mock departmentDao.save()
        when(departmentDao.save(any()))
                .thenReturn(DepartmentDatabaseEntry.builder()
                        .departmentId(NEWDEPARTMENTID)
                        .departmentName(DEPARTMENTNAME)
                        .build());

        Department departmentToUpdate = Department.builder().departmentId(DEPARTMENTID).departmentName(DEPARTMENTNAME).build();
        Department updatedDepartment = departmentServiceImpl.update(departmentToUpdate);

        Assertions.assertNotNull(updatedDepartment);
        Assertions.assertEquals(NEWDEPARTMENTID, updatedDepartment.getDepartmentId());
        Assertions.assertEquals(DEPARTMENTNAME, updatedDepartment.getDepartmentName());

        verify(departmentDao, times(1)).findById(any(Integer.class));
        verify(departmentDao, times(1)).save(any());
    }
    //-------------------------------------------------------------------------------------------------------------

    @Test
    public void testRemoveDepartment()  //findByAll, delete
    {
        Department departmentToRemove = Department.builder().departmentId(DEPARTMENTID).departmentName(DEPARTMENTNAME).build();
        Department removedDepartment = departmentServiceImpl.remove(departmentToRemove);

        verify(departmentDao, times(1)).findById(any(Integer.class));
        verify(departmentDao, times(1)).delete(any());
    }
    //-------------------------------------------------------------------------------------------------------------

    @Test
    public void testCreateDepartmentIfDepartmentIsAlreadyInStorage() {

        try {
            Department depToCreate = Department.builder().departmentId(DEPARTMENTID).departmentName(DEPARTMENTNAME).build();

            Department createDepartment = departmentServiceImpl.create(depToCreate);
            Assertions.assertNotNull(createDepartment);
            verify(departmentDao, times(1)).save(any());
            fail();
        }
        catch (EntityAlreadyInStorageException entityAlreadyInStorageException) {
            System.out.println("testCreateDepartmentIfDepartmentIsAlreadyInStorage: Entity with id 1 is already in storage");
            Assertions.assertEquals("Entity with id 1 already in storage", entityAlreadyInStorageException.getMessage());
        }
        catch (Exception exception)
        {
            fail();
        }
    }
    //-------------------------------------------------------------------------------------------------------------

    @Test
    public void testDepartmentByIdNotFound()
    {
    // mock  departmentDao.findById()
        when(departmentDao.findById(10)).thenReturn(Optional.empty());

        try
        {
            Department department = departmentServiceImpl.getDepartmentById(10);
        }

        catch (EntityNotFoundException entityNotFoundException)
        {
            System.out.println("testDepartmentByIdNotFound: Entity with id 10 is not found by Id");
            Assertions.assertEquals("Entity with id 10 not found", entityNotFoundException.getMessage());
        }
        catch (Exception exception)
        {
            fail();
        }
    }
    //-------------------------------------------------------------------------------------------------------------

    @Test
    public void testDepartmentToUpdateNotFound ()
    {
    // mock  departmentDao.findById()
        when(departmentDao.findById(10)).thenReturn(Optional.empty());

        Department departmentToUpdate = Department.builder()
                .departmentId(10)
                .departmentName("Department10")
                .build();
        try
        {
            Department department = departmentServiceImpl.update(departmentToUpdate);
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            System.out.println("testDepartmentToUpdateNotFound: Entity with id 10 is not found to update");
            Assertions.assertEquals("Entity with id 10 not found", entityNotFoundException.getMessage());
        }
        catch (Exception exception)
        {
            fail();
        }
    }
    //-------------------------------------------------------------------------------------------------------------

    @Test
    public void testDepartmentToDeleteNotFound ()
    {
    // mock  departmentDao.findById()
        when(departmentDao.findById(10)).thenReturn(Optional.empty());

        Department departmentToDelete = Department.builder()
                .departmentId(10)
                .departmentName("Department10")
                .build();
        try
        {
            Department department = departmentServiceImpl.remove(departmentToDelete);
            //Department department = departmentServiceImpl.getDepartmentById(departmentToDelete.getDepartmentId());
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            System.out.println("testDepartmentToDeleteNotFound: Entity with id 10 is not found to delete");
            Assertions.assertEquals("Entity with id 10 not found", entityNotFoundException.getMessage());
        }
        catch (Exception exception)
        {
            fail();
        }
    }

}

