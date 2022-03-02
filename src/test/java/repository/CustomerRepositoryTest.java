package repository;

import entity.Customer;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {
    private CustomerRepository customerRepository;

    @BeforeAll
    public static void setup() {
        Connection connection = MyConnection.connection;
    }

    @BeforeEach
    public void beforeEach() throws SQLException {
        customerRepository = new CustomerRepository();
    }

    //    @AfterAll
//    public void afterEach() throws SQLException {
//        // deletes all records in table.
//        String delete = "DELETE FROM customer ";
//        PreparedStatement preparedStatement = MyConnection.connection.prepareStatement(delete);
//        preparedStatement.execute();
//        preparedStatement.close();
//    }

    @Test
    public void insertTest() throws SQLException {
        // Arrange -->
        Customer customer = new Customer("Test1", "123", "tehran");

        // Act
        Integer id = customerRepository.save(customer);

        // Assert
        Customer loadedCustomer = customerRepository.findById(id);
        assertAll(
                () -> assertNotNull(id),
                () -> assertNotNull(loadedCustomer),
                () -> assertEquals(id, loadedCustomer.getId()),
                () -> assertEquals("Test1", loadedCustomer.getUsername())
        );
        customerRepository.delete(id);
    }

    @Test
    public void updateTest() throws SQLException {
        // Arrange -->
        Customer customer = new Customer("beforeUpdate", "123", "tehran");
        Customer beforeUpdate = customer;
        // Act
        Integer id = customerRepository.save(customer);
        customer.setUsername("afterUpdate");
        customer.setId(id);

        // Assert
        customerRepository.update(customer);
        assertNotEquals("beforeUpdate", customer.getUsername());
        System.out.println(customer);
        customerRepository.delete(id);
    }

    @Test
    public void deleteTest() throws SQLException {
        // Arrange
        Customer customer = new Customer("delete", "123", "mashhad");
        customer.setId(customerRepository.save(customer));

        // Act
        customerRepository.delete(customer.getId());

        // Assert
        assertNull(customerRepository.findById(customer.getId()));
    }

    @Test
    public void findByIdTest() throws SQLException {
        // Arrange
        Customer customer = new Customer("Yousef", "123", "tehran");

        // Act
        customer.setId(customerRepository.save(customer));

        // Assert
        assertNotNull(customerRepository.findById(customer.getId()));

    }

    @Test
    public void findAllTest() throws SQLException {
        // Arrange
        customerRepository.save(new Customer("Yousef", "123", "tehran"));
        customerRepository.save(new Customer("ali", "456", "esfahan"));
        customerRepository.save(new Customer("benyamin", "789", "mashhad"));

        // Act
        List<Customer> customers = customerRepository.findAll();
        System.out.println(customers.size());

        // Assert
        assertTrue(6 <= customers.size());

    }


}