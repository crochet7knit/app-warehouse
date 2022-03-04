package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.User;
import uz.pdp.payload.Result;
import uz.pdp.payload.UserDto;
import uz.pdp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Result create(UserDto userDto) {
        boolean exists = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (exists) {
            return new Result("Bu raqamli user mavjud", false);
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setCode(userDto.getCode());
        user.setPassword(userDto.getPassword());
        user.setWarehouses(userDto.getWarehouses());
        userRepository.save(user);
        return new Result("User saqlandi", true);
    }

    public List<User> getAll() {
        List<User> all = userRepository.findAll();
        return all;
    }

    public User getOne(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new User();
        }
        User user = optionalUser.get();
        return user;
    }

    public Result edit(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new Result("Bu id mavjud", false);
        }
        User user = optionalUser.get();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setCode(userDto.getCode());
        user.setPassword(userDto.getPassword());
        user.setWarehouses(userDto.getWarehouses());
        userRepository.save(user);
        return new Result("User o'zgartirildi", true);
    }

    public Result delete(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new Result("User idisi topilmadi",false);
        }
        userRepository.deleteById(id);
            return new Result("User o'chirildi",false);
    }
}
