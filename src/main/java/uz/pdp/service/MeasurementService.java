package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Measurement;
import uz.pdp.payload.Result;
import uz.pdp.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement) {
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName) {
            Result result = new Result();
            result.setMessage("Bunday o'lchov bilrligi mavjud!");
            result.setSuccess(false);
//        return new Result("Bunday o'lchov bilrligi mavjud!",false);
            return result;
        }
        measurementRepository.save(measurement);

        Result result = new Result();
        result.setMessage("Muvaffaqiyatli saqlandi!");
        result.setSuccess(true);
//        return new Result("Muvaffaqiyatli saqlandi!!",true);
        return result;
    }

    public List<Measurement> getAll() {
        List<Measurement> list = measurementRepository.findAll();
        return list;
    }

    public Measurement getOne(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) {
            return new Measurement();
        }
        Measurement measurement = optionalMeasurement.get();
        return measurement;
    }

    public Result edit(Integer id, Measurement measurement) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) {
            return new Result("Berilgan id topilmadi!", false);
        }
        Measurement editedMeasurement = optionalMeasurement.get();
        editedMeasurement.setName(measurement.getName());
        measurementRepository.save(editedMeasurement);
        return new Result("Berilgan o'lchov birligi o'zgartirildi!", true);
    }

    public Result deleted(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) {
            return new Result("Bu id topilmadi!", false);
        }
        measurementRepository.deleteById(id);
        return new Result("Shu ididagi o'lchov birlik o'chirildi!", true);
    }
}
