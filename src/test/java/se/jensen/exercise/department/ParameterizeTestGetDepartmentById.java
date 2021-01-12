package se.jensen.exercise.department;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import se.jensen.RestServiceApplication;
import se.jensen.api.DepartmentModel;
import se.jensen.exercise.department.client.DepartmentRestServiceClient;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

public class ParameterizeTestGetDepartmentById {

    public static Stream<Arguments> dataForTest()
    {
        return Stream.of(
                Arguments.of(1, "Development"),
                Arguments.of(2, "Sales"),
                Arguments.of(3, "Management"));
    }

//---------------------------------------------------------------------------------
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

    @ParameterizedTest
    @MethodSource("dataForTest")
    public void testGetDepartmentById(int id, String name) {
        Optional<DepartmentModel> department = DepartmentRestServiceClient.getDepartmentById(id);
        Assertions.assertTrue(department.isPresent());

        DepartmentModel departmentModel = department.get();

        Assertions.assertEquals(Integer.valueOf(id), departmentModel.getDepartmentId());
        Assertions.assertEquals(name, departmentModel.getDepartmentName());
    }
}
