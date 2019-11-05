package com.vulenhtho.clothesboot.controller;

import com.vulenhtho.clothesboot.model.request.UserFilterRequest;
import com.vulenhtho.clothesboot.model.request.IdsRequest;
import com.vulenhtho.clothesboot.model.request.UserRequest;
import com.vulenhtho.clothesboot.model.respone.*;
import com.vulenhtho.clothesboot.service.RoleService;
import com.vulenhtho.clothesboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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

    @GetMapping("/users")
    public ResponseEntity<UserFilterResponse> getUsers(@RequestParam Integer page
            ,@RequestParam Integer size, @RequestParam @Nullable String search
            ,@RequestParam @Nullable Boolean status,@RequestParam @Nullable String sort
            ,@RequestParam @Nullable Boolean sex, @RequestParam @Nullable Long role){

        UserFilterRequest userFilterRequest = new UserFilterRequest(sort,status,sex,search,role,page,size);
        return ResponseEntity.ok(userService.findAllWithFilter(userFilterRequest));
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

    @DeleteMapping("/user")
    public ResponseEntity<Void> delete(@RequestBody IdsRequest ids){
        userService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<RegisterResponse> getUserByUserName(@RequestParam String userName){
        return ResponseEntity.ok(userService.findUserByUserName(userName));
    }


    @GetMapping("/users-all")
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
