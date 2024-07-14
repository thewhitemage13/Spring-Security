package org.thewhitemage13.springsecurityexample.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.thewhitemage13.springsecurityexample.model.Application;
import org.thewhitemage13.springsecurityexample.model.MyUser;
import org.thewhitemage13.springsecurityexample.services.AppService;

import java.util.List;

@RestController
@RequestMapping("/apps")
public class AppController {
    private AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the unprotected page";
    }

    @GetMapping("/all-app")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public List<Application> applicationList() {
        return appService.getApplications();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Application applicationById(@PathVariable int id) {
        return appService.applicationById(id);
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody MyUser myUser) {
        appService.addUser(myUser);
        return "User is saved";
    }
}











