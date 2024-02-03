package edu.school21.repositories;

import edu.school21.models.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository{

    private final JdbcTemplate jdbcTemplate;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
    }

    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        List<Product> products = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class), id);
        return products.isEmpty() ? Optional.empty() : Optional.of(products.get(0));
    }

    public void update(Product product) {
        String sql = "UPDATE product SET name = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getId());
    }

    public void save(Product product) {
        String sql = "INSERT INTO product (id, name, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, null, product.getName(), product.getPrice());
        String sql1 = "select id from product order by id desc limit 1;";
        Long id = jdbcTemplate.queryForObject(sql1,Long.class);
        product.setId(id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
