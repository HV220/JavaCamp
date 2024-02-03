package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.awt.dnd.DropTarget;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductsRepositoryJdbcImplTest {

    private ProductsRepositoryJdbcImpl repository;
    private DataSource dataSource;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "gammy", 175),
            new Product(2L, "tea", 34),
            new Product(3L, "bubble", 45),
            new Product(4L, "book", 352),
            new Product(5L, "alcohol", 777)
    );

    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1L, "gammy", 175);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L, "updatedGammy", 100);


    @BeforeEach
    void initRepository() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase embeddedDatabase = builder
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        dataSource = embeddedDatabase;
        repository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @AfterEach
    void resetDB() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("drop table product;");
    }

    @Test
    void findAll() {
        List<Product> products = repository.findAll();
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, products);
    }

    @Test
    void findById() {
        Optional<Product> p = repository.findById(1L);
        assertTrue(p.isPresent());
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, p.get());
    }

    @Test
    void update() {
        repository.update(new Product(1L, "updatedGammy", 100));
        Optional<Product> updated = repository.findById(1L);
        assertTrue(updated.isPresent());
        assertEquals(EXPECTED_UPDATED_PRODUCT, updated.get());
    }

    @Test
    void save() {
        Product p = new Product(null, "newProduct", 50);
        repository.save(p);
        Optional<Product> saved = repository.findById(p.getId());
        assertTrue(saved.isPresent());
        assertEquals(p, saved.get());
    }

    @Test
    void delete() {
        repository.delete(1L);
        Optional<Product> deleted = repository.findById(1L);
        assertFalse(deleted.isPresent());
    }
}