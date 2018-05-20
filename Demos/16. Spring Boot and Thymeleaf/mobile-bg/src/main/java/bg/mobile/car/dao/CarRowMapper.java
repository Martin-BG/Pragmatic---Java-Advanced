package bg.mobile.car.dao;

import bg.mobile.car.model.Car;
import bg.mobile.car.model.Condition;
import bg.mobile.car.model.EngineType;
import bg.mobile.car.model.Gearbox;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {

    public Car mapRow(ResultSet rs, int i) throws SQLException {
        Car car = new Car();
        car.setId(rs.getLong("id"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));
        car.setCondition(Condition.valueOf(rs.getString("condition")));
        car.setYear(rs.getInt("year"));
        car.setHorsePower(rs.getInt("horse_power"));
        car.setPrice(rs.getBigDecimal("price"));
        car.setCategory(rs.getString("category"));
        car.setCity(rs.getString("city"));
        car.setMaxMileage(rs.getInt("max_mileage"));
        car.setEngineType(EngineType.valueOf(rs.getString("engine_type")));
        car.setColor(rs.getString("color"));
        car.setGearbox(Gearbox.valueOf(rs.getString("gearbox")));
        return car;
    }
}
