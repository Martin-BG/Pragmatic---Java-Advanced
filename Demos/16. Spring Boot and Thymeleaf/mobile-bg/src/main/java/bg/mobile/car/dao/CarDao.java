package bg.mobile.car.dao;

import bg.mobile.car.model.Car;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CarDao extends NamedParameterJdbcTemplate {

    @Autowired
    public CarDao(DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    public void add(Car car) {
        String sql = "INSERT INTO car (brand_id, model_id, `condition`, " +
                "year, horse_power, price, category_id, max_mileage, engine_type, " +
                "color_id, gearbox, city_id, user_id) " +
                "VALUES ((SELECT id FROM brand WHERE brand.name = ?), " +
                "(SELECT id FROM model WHERE model.name = ?), " +
                "?, " +
                "?, " +
                "?, " +
                "?, " +
                "(SELECT id FROM category WHERE category.name = ?), " +
                "?, " +
                "?, " +
                "(SELECT id FROM color WHERE color.name = ?), " +
                "?, " +
                "(SELECT id FROM city WHERE city.name = ?), " +
                "?)";
        List<Object> params = new ArrayList<>(Arrays.asList(getParams(car)));
        params.add(car.getUserId());
        getJdbcOperations().update(sql, params.toArray());
    }

    public long getLastCarId() {
        String sql = "SELECT MAX(id) FROM car";
        return getJdbcOperations().queryForObject(sql, Long.class);
    }

    public void deleteById(long id) {
        String sql = "DELETE FROM car WHERE id = ?";
        getJdbcOperations().update(sql, id);
    }

    public List<Car> getCarsByCriteria(Car criteria) {
        String sql = "SELECT car.id, brand.name AS brand, model.name AS model, " +
                "`condition`, year, horse_power, price, category.name AS category, " +
                "city.name AS city, max_mileage, engine_type, color.name AS color, gearbox " +
                "FROM car " +
                "INNER JOIN brand ON brand.id = car.brand_id " +
                "INNER JOIN model ON model.id = car.model_id " +
                "INNER JOIN city ON city.id = car.city_id " +
                "INNER JOIN color ON color.id = car.color_id " +
                "INNER JOIN category ON category.id = car.category_id " +
                "WHERE 1 = 1 ";
        if (criteria.getId() != null) {
            sql += " AND car.id = " + criteria.getId();
        }
        if (criteria.getUserId() != null) {
            sql += " AND car.user_id = " + criteria.getUserId();
        }
        if (StringUtils.isNotEmpty(criteria.getBrand())) {
            sql += " AND brand.name = " + toSqlParam(criteria.getBrand());
        }
        if (StringUtils.isNotEmpty(criteria.getModel())) {
            sql += " AND model.name = " + toSqlParam(criteria.getModel());
        }
        if (criteria.getCondition() != null) {
            sql += " AND `condition` = " + toSqlParam(criteria.getCondition().toString());
        }
        if (criteria.getYear() != null) {
            sql += " AND year = " + criteria.getYear();
        }
        if (criteria.getHorsePower() != null) {
            sql += " AND horse_power = " + criteria.getHorsePower();
        }
        if (criteria.getPrice() != null) {
            sql += " AND price = " + criteria.getPrice();
        }
        if (StringUtils.isNotEmpty(criteria.getCategory())) {
            sql += " AND category.name = " + toSqlParam(criteria.getCategory());
        }
        if (StringUtils.isNotEmpty(criteria.getCity())) {
            sql += " AND city.name = " + toSqlParam(criteria.getCity());
        }
        if (criteria.getMaxMileage() != null) {
            sql += " AND max_mileage = " + criteria.getMaxMileage();
        }
        if (criteria.getEngineType() != null) {
            sql += " AND engine_type = " + toSqlParam(criteria.getEngineType().toString());
        }
        if (StringUtils.isNotEmpty(criteria.getColor())) {
            sql += " AND color.name = " + toSqlParam(criteria.getColor());
        }
        if (criteria.getGearbox() != null) {
            sql += " AND gearbox = " + toSqlParam(criteria.getGearbox().toString());
        }
        return getJdbcOperations().query(sql, new CarRowMapper());
    }

    private String toSqlParam(String param) {
        return "'" + param + "'";
    }

    public void edit(Car car) {
        String sql = "UPDATE car " +
                "SET brand_id = (SELECT id FROM brand WHERE brand.name = ?), " +
                "model_id = (SELECT id FROM model WHERE model.name = ?), " +
                "`condition` = ?, " +
                "year = ?, " +
                "horse_power = ?, " +
                "price = ?, " +
                "category_id = (SELECT id FROM category WHERE category.name = ?), " +
                "max_mileage = ?, " +
                "engine_type = ?, " +
                "color_id = (SELECT id FROM color WHERE color.name = ?), " +
                "gearbox = ?, " +
                "city_id = (SELECT id FROM city WHERE city.name = ?) " +
                "WHERE car.id = ?";
        List<Object> params = new ArrayList<>(Arrays.asList(getParams(car)));
        params.add(car.getId());
        getJdbcOperations().update(sql, params.toArray());
    }

    public List<String> getAvailableCities() {
        String sql = "SELECT name FROM city";
        return getJdbcOperations().queryForList(sql, String.class);
    }

    public List<String> getAvailableBrands() {
        String sql = "SELECT name FROM brand";
        return getJdbcOperations().queryForList(sql, String.class);
    }

    public List<String> getAvailableModels() {
        String sql = "SELECT name FROM model";
        return getJdbcOperations().queryForList(sql, String.class);
    }

    public List<String> getAvailableColors() {
        String sql = "SELECT name FROM color";
        return getJdbcOperations().queryForList(sql, String.class);
    }

    private Object[] getParams(Car car) {
        List<Object> params = new ArrayList<>();

        params.add(car.getBrand());
        params.add(car.getModel());
        params.add(car.getCondition().toString());
        params.add(car.getYear());
        params.add(car.getHorsePower());
        params.add(car.getPrice());
        params.add(car.getCategory());
        params.add(car.getMaxMileage());
        params.add(car.getEngineType().toString());
        params.add(car.getColor());
        params.add(car.getGearbox().toString());
        params.add(car.getCity());

        return params.toArray();
    }

}
