package cucumber_steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import se.jensen.RestServiceApplication;
import se.jensen.api.EmployeeModel;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TestEmployeeRestAPI extends  TestClient{

    Optional<List<EmployeeModel>> employees = null;
    Optional<EmployeeModel> employee = null;

    private static ConfigurableApplicationContext applicationContext;

//---------------------- Start and finish application ---------------------------------

    @Given("I start application")
    public void iStartApplication() {
        String[] args = {};
        SpringApplication app = new SpringApplication(RestServiceApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8080"));
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.setLogStartupInfo(false);
        applicationContext = app.run(args);
    }

//---------------------- Scenario 1 ---------------------------------

    @When("the client calls employees")
    public void theClientCallsEmployees() throws Throwable {
        employees = getAllEmployees();
    }

    @Then("the client receives {int} employees")
    public void theClientReceivesEmployees(int numberOfEmployees) throws Throwable{
        Assert.assertEquals(numberOfEmployees, employees.get().size());
    }

//---------------------- Scenario 2 ---------------------------------


    @When("the client updates DepartmentId to {int} for Employee {int}")
    public void theClientUpdatesDepartmentIdToForEmployee(int newDepartmentId, int numberOfEmployee) {
        updateEmployee(EmployeeModel.builder()
                .employeeId(numberOfEmployee)
                .firstName("firstName1")
                .lastName("lastName1")
                .fullTime(true)
                .salary(BigDecimal.valueOf(25000))
                .departmentId(newDepartmentId)
                .build());
    }

    @Then("the DepartmentId for Employee {int} is updated to {int}")
    public void theDepartmentIdForEmployeeIsUpdatedTo(int newDepartmentId, int numberOfEmployee) {
        Optional<EmployeeModel> employee = getEmployeeById(numberOfEmployee);
        Assert.assertEquals(Integer.valueOf(newDepartmentId) ,employee.get().getDepartmentId());
    }

//---------------------- Scenario 3 ---------------------------------

    @When("the client gets Employee {int}")
    public void theClientGetsEmployee(int numberOfEmployee) throws Throwable {
        employee = getEmployeeById(numberOfEmployee);
    }

    @Then("DepartmentId is {int}")
    public void departmentIs(int numberOfDepartment) {
        Assert.assertEquals(Integer.valueOf(2),employee.get().getDepartmentId());
    }

//---------------------- Scenario 4 ---------------------------------

    @When("client updates firstName for Employee {int} to {string}")
    public void clientUpdatesFirstNameForEmployeeTo(int numberOfEmployee, String firstName) throws Throwable {
        updateEmployee(EmployeeModel.builder()
                .employeeId(numberOfEmployee)
                .firstName(firstName)
                .lastName("lastName1")
                .fullTime(true)
                .salary(BigDecimal.valueOf(25000))
                .departmentId(2)
                .build());
    }

    @Then("firstName for Employee {int} updated to {string}")
    public void firstnameForEmployeeUpdatedTo(int numberOfEmployee, String firstName) throws Throwable {
        Optional <EmployeeModel> employee = getEmployeeById(numberOfEmployee);
        Assert.assertEquals(firstName, employee.get().getFirstName());
    }

//---------------------- Scenario 5 ---------------------------------

    @When("client delete employee {int}")
    public void clientDeleteEmployee(int numberEmployeeToDelete) throws Throwable {
        EmployeeModel employee = getEmployeeById(numberEmployeeToDelete).get();
        deleteEmployee(employee);
    }

    @Then("Employee {int} not found")
    public void employeeNotFound(int numberOfEmployee) throws Throwable {
        try {
            getEmployeeById(numberOfEmployee);
            Assert.fail("department id: " + numberOfEmployee + "not deleted");
        }
        catch (Exception e){
            Assert.assertEquals("404 : [Entity with id 1 not found]",e.getMessage());
        }
    }

//---------------------- Scenario 6 ---------------------------------

    @When("client create a new Employee with {string}, {string}, {string}, {string}, {string}, {string}")
    public void clientCreateANewEmployeeWith(String employeeId, String firstName, String lastName, String fullTime, String salary, String departmentId) {
        createEmployee(EmployeeModel.builder()
                .employeeId(Integer.parseInt(employeeId))
                .firstName(firstName)
                .lastName(lastName)
                .fullTime(Boolean.parseBoolean(fullTime))
                .salary(BigDecimal.valueOf(Integer.parseInt(salary)))
                .departmentId(Integer.parseInt(departmentId))
                .build());
    }

    @Then("employee with employeeId {string} is found")
    public void employeeWithEmployeeIdIsFound(String employeeId) {
        Assert.assertTrue(getEmployeeById(Integer.parseInt(employeeId)).isPresent());
    }

//---------------------- Scenario 7 ---------------------------------

    @Given("the employeeId of employee to delete")
    public void theEmployeeIdOfEmployeeToDelete(DataTable table) {
        List<String> data = table.asList();
        Integer employeeId = Integer.parseInt(data.get(0));
}

    @When("client try to delete employee with employeeId {int}")
    public void clientTryToDeleteEmployeeWithEmployeeId(int employeeId) {
        deleteEmployee(EmployeeModel.builder()
                .employeeId(employeeId)
                .firstName("")
                .lastName("")
                .fullTime(true)
                .salary(BigDecimal.valueOf(0))
                .departmentId(0)
                .build());
    }

    @Then("message that employee med Id {int} is not found")
    public void messageThatEmployeeMedIdIsNotFound(int employeeId) {
       try
       {
           getEmployeeById(employeeId);
       }
       catch (Exception e)
        {
            Assert.assertEquals("404 : [Entity with id 5 not found]",e.getMessage());
        }
    }

//---------------------- AfterAll Scenario ---------------------------------

    @When("I message that I close application")
    public void iMessageThatICloseApplication() {
        System.out.println("The application is closed");
    }

    @Then("application is closed")
    public void applicationIsClosed() {
        SpringApplication.exit(applicationContext);
    }

}
