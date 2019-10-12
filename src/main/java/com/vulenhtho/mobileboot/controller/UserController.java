package com.vulenhtho.mobileboot.controller;

import com.vulenhtho.mobileboot.model.request.UserRequest;
import com.vulenhtho.mobileboot.model.respone.RegisterResponse;
import com.vulenhtho.mobileboot.model.respone.RoleResponse;
import com.vulenhtho.mobileboot.model.respone.UserResponse;
import com.vulenhtho.mobileboot.service.RoleService;
import com.vulenhtho.mobileboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController extends BaseController{
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/user")
    public ResponseEntity<Void> insert(@Valid @RequestBody UserRequest userRequest){
        userService.insert(userRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                @Valid @RequestBody UserRequest userRequest){
        userService.update(id,userRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<RegisterResponse> getUserByUserName(@RequestParam String userName){
        return ResponseEntity.ok(userService.findUserByUserName(userName));
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleResponse>> getRoles(){
        return ResponseEntity.ok(roleService.findAll());
    }

}
