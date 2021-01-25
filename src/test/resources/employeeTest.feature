Feature: test employee api

#BeforeAll Scenario
  Scenario: I start application
    Given I start application

#Scenario 1
  Scenario: client gets all employees
    When the client calls employees
    Then the client receives 4 employees

#Scenario 2
  Scenario: client updates Department for employee 1
    When the client updates DepartmentId to 2 for Employee 1
    Then the DepartmentId for Employee 1 is updated to 2

#Scenario 3
  Scenario: client gets Employee 1
    When  the client gets Employee 1
    Then  DepartmentId is 2

#Scenario 4
  Scenario: client updates firstName for employee 1
    When client updates firstName for Employee 1 to "FIRSTNAME"
    Then firstName for Employee 1 updated to "FIRSTNAME"

#Scenario 5
  Scenario: client deletes Employee
    When client delete employee 1
    Then Employee 1 not found


#Scenario 6
  Scenario Outline: client create a new Employee
    When client create a new Employee with "<EMPLOYEEID>", "<FIRSTNAME>", "<LASTNAME>", "<FULLTIME>", "<SALARY>", "<DEPARTMENTID>"
    Then employee with employeeId "<EMPLOYEEID>" is found

    Examples:
    |EMPLOYEEID|FIRSTNAME|LASTNAME|FULLTIME|SALARY  |DEPARTMENTID|
    |1000      |Yulia    |Gutorova|true    |1000000 |100         |
    |1001      |Vladimir |Gutorov |false   |10000000|100         |

#Scenario 7
  Scenario: client tries to delete Employee which is not found
    Given the employeeId of employee to delete
      |5|
    When client try to delete employee with employeeId 5
    Then message that employee med Id 5 is not found

#AfterAll Scenario
  Scenario: I close application
    When I message that I close application
    Then application is closed