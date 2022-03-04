package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.User;
import uz.pdp.entity.Warehouse;
import uz.pdp.payload.Result;
import uz.pdp.payload.UserDto;
import uz.pdp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public Result create(@RequestBody UserDto userDto) {
        Result result = userService.create(userDto);
        return result;
    }

    @GetMapping
    public List<User> getAll() {
        List<User> all = userService.getAll();
        return all;
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable Integer id) {
        User one = userService.getOne(id);
        return one;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody UserDto userDto) {
        Result edit = userService.edit(id, userDto);
        return edit;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Result delete = userService.delete(id);
        return delete;
    }
}

