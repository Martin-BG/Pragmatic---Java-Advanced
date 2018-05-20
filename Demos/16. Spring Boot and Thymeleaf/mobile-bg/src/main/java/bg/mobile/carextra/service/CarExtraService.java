package bg.mobile.carextra.service;

import bg.mobile.carextra.dao.CarExtraDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CarExtraService {

    private final CarExtraDao dao;

    @Autowired
    public CarExtraService(CarExtraDao dao) {
        this.dao = dao;
    }

    public void add(long carId, String extra) {
        dao.add(carId, extra);
    }

    public List<String> getAvailableExtras() {
        return dao.getAvailableExtras();
    }

    public Set<String> getExtrasForCar(long carId) {
        return dao.getExtrasForCar(carId);
    }

    public void deleteExtrasForCar(long carId) {
        dao.deleteExtrasForCar(carId);
    }

}