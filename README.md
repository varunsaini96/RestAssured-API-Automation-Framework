# REST Assured API Automation Framework

API test automation framework for the [Swagger Petstore](https://petstore.swagger.io) **User** API, built with Java, REST Assured and TestNG.

## Tech stack

| Purpose | Tool |
|---|---|
| Language / build | Java, Maven |
| API testing | REST Assured |
| Test runner | TestNG |
| Data-driven testing | Apache POI (reads `.xlsx`) |
| Reporting | Extent Reports |
| Logging | Log4j2 |

## Project structure

```
src/test/java/api
├── endpoints   # API calls (create, read, update, delete user)
├── payload     # Request body models (POJOs)
├── test        # Test classes
└── utilities   # Excel reader, data providers, Extent report listener
src/test/resources
├── log4j2.xml  # Logging configuration
testing.xml      # Suite file that runs the tests
```

## What is covered

- Create, read, update and delete operations on the Petstore `/user` endpoints
- Data-driven tests: user data is read from an Excel sheet through a TestNG `@DataProvider`
- Status code and response assertions
- HTML report generated for every run

## How to run

Clone the repository and run:

```bash
mvn clean test
```

This runs the suite defined in `testng.xml`.

## Output

- **Test report:** `reports/Test-Report-<timestamp>.html`
- **Logs:** `logs/automation.log`

## API under test

This is a public sandbox API shared by many users, so responses can occasionally be inconsistent.