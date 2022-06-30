// package com.project.capstone.controller.JwtController;

// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @CrossOrigin(origins = "*", maxAge = 3600)
// @RestController
// @RequestMapping("/api/test")
// public class TestController {

//     /**
//      * Give public data
//      * @return public data
//      */
//     @GetMapping("/all")
//     public String allAccess() {
//         return "Public Data.";
//     }

//     /**
//      * Give data for user
//      * @return data for user
//      */
//     @GetMapping("/user")
//     @PreAuthorize("hasRole('ADMIN') or hasRole('DOKTER')")
//     public String userAccess() {
//         return "User Data.";
//     }

//     /**
//      * Give data for moderator
//      * @return data for moderator
//      */
//     @GetMapping("/dokter")
//     @PreAuthorize("hasRole('DOKTER')")
//     public String dokterAccess() {
//         return "Dokter Data.";
//     }

//     /**
//      * Give data for admin
//      * @return data for admin
//      */
//     @GetMapping("/admin")
//     @PreAuthorize("hasRole('ADMIN')")
//     public String adminAccess() {
//         return "Admin Data.";
//     }
// }