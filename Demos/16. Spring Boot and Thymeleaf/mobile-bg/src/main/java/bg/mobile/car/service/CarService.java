package bg.mobile.car.service;

import bg.mobile.car.dao.CarDao;
import bg.mobile.car.model.Car;
import bg.mobile.carextra.service.CarExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarDao carDao;
    private final CarExtraService carExtraService;

    @Autowired
    public CarService(CarDao carDao, CarExtraService carExtraService) {
        this.carDao = carDao;
        this.carExtraService = carExtraService;
    }

    public void add(Car car) {
        carDao.add(car);
        long carId = carDao.getLastCarId();
        car.getCarExtras().forEach(e -> carExtraService.add(carId, e));
    }

    public void deleteById(long id) {
        carDao.deleteById(id);
    }

    public List<Car> getCarsByCriteria(Car criteria) {
        List<Car> cars = carDao.getCarsByCriteria(criteria);
        cars.forEach(car -> car.setCarExtras(carExtraService.getExtrasForCar(car.getId())));
        if (hasCarExtraCriteria(criteria)) {
            cars = cars
                    .stream()
                    .filter(car -> car.getCarExtras().containsAll(criteria.getCarExtras()))
                    .collect(Collectors.toList());
        }
        return cars;
    }

    public List<String> getAvailableCities() {
        return carDao.getAvailableCities();
    }

    public List<String> getAvailableBrands() {
        return carDao.getAvailableBrands();
    }

    public List<String> getAvailableModels() {
        return carDao.getAvailableModels();
    }

    public List<String> getAvailableColors() {
        return carDao.getAvailableColors();
    }

    private boolean hasCarExtraCriteria(Car car) {
        return car.getCarExtras() != null && !car.getCarExtras().isEmpty();
    }

    public void edit(Car car) {
        carDao.edit(car);
        Long carId = car.getId();
        Set<String> extrasForCar = carExtraService.getExtrasForCar(carId);
        if (!areCarExtrasTheSame(extrasForCar, car.getCarExtras())) {
            carExtraService.deleteExtrasForCar(carId);
            car.getCarExtras().forEach(extra -> carExtraService.add(carId, extra));
        }
    }

    private boolean areCarExtrasTheSame(Set<String> oldCarExtras, Set<String> newCarExtras) {
        return oldCarExtras.size() == newCarExtras.size() && oldCarExtras.containsAll(newCarExtras);
    }

}
