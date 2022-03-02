package repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyConnectionTest {
    @Test
    public void testConnection() {
        assertDoesNotThrow(() -> MyConnection.connection);
    }

}