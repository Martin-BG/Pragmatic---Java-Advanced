package bg.pragmatic.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = {"/", "/all"})
    public String hello() {
        return "Hello";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/secured/all")
    public String securedHello() {
        return "Secured Hello";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/secured/admin/all")
    public String securedHelloForAdminsOnly() {
        return "Secured Hello for admins only";
    }

}
