package se.jensen.exercise.department;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.jensen.RestServiceApplication;
import se.jensen.api.DepartmentModel;
import se.jensen.dao.EntityAlreadyInStorageException;
import se.jensen.exercise.department.client.DepartmentRestServiceClient;
import se.jensen.RestServiceApplication;
import se.jensen.test.category.IntegrationTest;
import se.jensen.test.category.UnitTest;

import lombok.SneakyThrows;
import org.junit.*;
//import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.junit.runners.MethodSorters;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestServiceApplication.class})

//@Category(IntegrationTest.class)

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentRestApiTest {

    private static ConfigurableApplicationContext applicationContext;

    @SneakyThrows
    @BeforeEach
    public static void startUp() {
        String[] args = {};
        SpringApplication app = new SpringApplication(RestServiceApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8080"));
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.setLogStartupInfo(false);
        applicationContext = app.run(args);
    }

    @AfterEach
    public static void shutDown() {
        SpringApplication.exit(applicationContext);
    }

//-----------------------------------------------------------------------------------------
    @Test
    public void a_testGetAllDepartments()
    {
        Optional <List <DepartmentModel>> department = DepartmentRestServiceClient.getAllDepartments();
        Assertions.assertTrue(department.isPresent());
        Assertions.assertEquals(3, department.get().stream().count());

    }
//-----------------------------------------------------------------------------------------
    @Test
    public void b_testGetDepartmentById()
    {
        Optional <DepartmentModel> department = DepartmentRestServiceClient.getDepartmentById(3);
        Assertions.assertTrue(department.isPresent());
        DepartmentModel departmentModel = department.get();
        Assertions.assertEquals(Integer.valueOf(3), departmentModel.getDepartmentId());
        Assertions.assertEquals("Management", departmentModel.getDepartmentName());
    }
//-----------------------------------------------------------------------------------------

    @Test
    public void c_testCreateNewDepartment()
    {
        DepartmentModel departmentToCreate = DepartmentModel.builder()
                .departmentId(1000)
                .departmentName("Testers")
                .build();

        Optional<DepartmentModel> createdDepartment = DepartmentRestServiceClient.createDepartment(departmentToCreate);
        DepartmentModel model = createdDepartment.get();

        Assertions.assertEquals(Integer.valueOf(1000), model.getDepartmentId());
        Assertions.assertEquals("Testers", model.getDepartmentName());

        Assertions.assertEquals(4, DepartmentRestServiceClient.getAllDepartments().get().stream().count());
    }

//-----------------------------------------------------------------------------------------

    @Test
    public void d_testDeleteDepartment ()
    {
        DepartmentModel departmentToDelete = DepartmentModel.builder()
                .departmentId(1)
                .departmentName("Development")
                .build();

        Optional <DepartmentModel> deletedDepartment = DepartmentRestServiceClient.deleteDepartment(departmentToDelete);

        DepartmentModel model = deletedDepartment.get();

        Assertions.assertEquals(Integer.valueOf(1), model.getDepartmentId());
        Assertions.assertEquals("Development", model.getDepartmentName());

        Assertions.assertEquals(3, DepartmentRestServiceClient.getAllDepartments().get().stream().count());
    }
//-----------------------------------------------------------------------------------------

    @Test
    public void e_testUpdateDepartment()
    {
        DepartmentModel departmentToUpdate = DepartmentModel.builder()
                .departmentId(1000)
                .departmentName("Sales")
                .build();

        Optional <DepartmentModel> updateDepartment = DepartmentRestServiceClient.updateDepartment(departmentToUpdate);

        DepartmentModel model = updateDepartment.get();

        Assertions.assertEquals(Integer.valueOf(1000), model.getDepartmentId());
        Assertions.assertEquals("Sales", model.getDepartmentName());

        Assertions.assertEquals(3, DepartmentRestServiceClient.getAllDepartments().get().stream().count());
    }
//-----------------------------------------------------------------------------------------
    @Test
    public void TestErrorHandling()
    {
        try
        {
           Optional <DepartmentModel> departmentModel = DepartmentRestServiceClient.getDepartmentById(10);
        }
        catch (HttpClientErrorException e)
        {
            Assertions.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            Assertions.assertEquals("404 : [Entity with id 10 not found]", e.getMessage());
        }
    }
}



