package se.jensen.exercise.department;

import lombok.SneakyThrows;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.runners.MethodSorters;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.web.client.HttpClientErrorException;
import se.jensen.RestServiceApplication;
import se.jensen.api.DepartmentModel;
import se.jensen.exercise.department.client.DepartmentRestServiceClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {RestServiceApplication.class})

@Tag("integration")

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class DepartmentRestApiTest {

    private static ConfigurableApplicationContext applicationContext;

    @SneakyThrows
    @BeforeAll
    public static void startUp() {
        String[] args = {};
        SpringApplication app = new SpringApplication(RestServiceApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8080"));
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.setLogStartupInfo(false);
        applicationContext = app.run(args);
    }

    @AfterAll
    public static void shutDown() {
        SpringApplication.exit(applicationContext);
    }

//-----------------------------------------------------------------------------------------
    @DisplayName("Test to get all Departments")
    @Test
    public void a_testGetAllDepartments()
    {
        Optional <List <DepartmentModel>> department = DepartmentRestServiceClient.getAllDepartments();
        Assertions.assertTrue(department.isPresent());
        Assertions.assertEquals(3, department.get().stream().count());

    }
//-----------------------------------------------------------------------------------------
    @DisplayName("Test to get a Department by id")
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

    @DisplayName("Test to create a new Department")
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

    @DisplayName("Test to update a Department")
    @Test
    public void d_testUpdateDepartment()
    {
        DepartmentModel departmentToUpdate = DepartmentModel.builder()
                .departmentId(1000)
                .departmentName("Sales")
                .build();

        Optional <DepartmentModel> updateDepartment = DepartmentRestServiceClient.updateDepartment(departmentToUpdate);

        DepartmentModel model = updateDepartment.get();

        Assertions.assertEquals(Integer.valueOf(1000), model.getDepartmentId());
        Assertions.assertEquals("Sales", model.getDepartmentName());

        Assertions.assertEquals(4, DepartmentRestServiceClient.getAllDepartments().get().stream().count());
    }
//-----------------------------------------------------------------------------------------

    @DisplayName("Test to delete a Department")
    @Test
    public void e_testDeleteDepartment ()
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
    @DisplayName("Test to that Department is not found")
    @Test
    public void f_TestErrorHandling()
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



