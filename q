[1mdiff --git a/src/main/java/com/CaridadMichael/WallPaperWorld/controller/UserController.java b/src/main/java/com/CaridadMichael/WallPaperWorld/controller/UserController.java[m
[1mindex 5d4affc..b12df1f 100644[m
[1m--- a/src/main/java/com/CaridadMichael/WallPaperWorld/controller/UserController.java[m
[1m+++ b/src/main/java/com/CaridadMichael/WallPaperWorld/controller/UserController.java[m
[36m@@ -1,15 +1,22 @@[m
 package com.CaridadMichael.WallPaperWorld.controller;[m
 [m
 import org.springframework.beans.factory.annotation.Autowired;[m
[32m+[m[32mimport org.springframework.http.HttpStatus;[m
 import org.springframework.http.ResponseEntity;[m
[32m+[m[32mimport org.springframework.web.bind.annotation.GetMapping;[m
 import org.springframework.web.bind.annotation.PostMapping;[m
[32m+[m[32mimport org.springframework.web.bind.annotation.PutMapping;[m
 import org.springframework.web.bind.annotation.RequestBody;[m
 import org.springframework.web.bind.annotation.RequestMapping;[m
[32m+[m[32mimport org.springframework.web.bind.annotation.RequestParam;[m
[32m+[m[32mimport org.springframework.web.bind.annotation.ResponseBody;[m
[32m+[m[32mimport org.springframework.web.bind.annotation.ResponseStatus;[m
 import org.springframework.web.bind.annotation.RestController;[m
 [m
 import com.CaridadMichael.WallPaperWorld.Service.UserService;[m
 import com.CaridadMichael.WallPaperWorld.dto.UserDTO;[m
[31m-[m
[32m+[m[32mimport com.CaridadMichael.WallPaperWorld.model.AppUser;[m
[32m+[m[32mimport com.CaridadMichael.WallPaperWorld.model.Picture;[m
 import com.CaridadMichael.WallPaperWorld.utils.AuthenticationResponse;[m
 [m
 import lombok.RequiredArgsConstructor;[m
[36m@@ -20,22 +27,47 @@[m [mimport lombok.RequiredArgsConstructor;[m
 @RestController[m
 @RequestMapping("/api/user")[m
 @RequiredArgsConstructor[m
[31m-[m
 public class UserController {[m
 	[m
 	@Autowired[m
 	private UserService userService;[m
 [m
 	@PostMapping("/register")[m
[31m-	public ResponseEntity<String> saveUser(@RequestBody UserDTO user) {[m
[31m-	[m
[32m+[m	[32m@ResponseStatus(HttpStatus.CREATED)[m
[32m+[m	[32mpublic ResponseEntity<String> saveUser(@RequestBody UserDTO user) {[m[41m	[m
 		return userService.RegisterUser(user);[m
 		[m
 	}[m
 	[m
[31m-	@PostMapping({"/authenticate"})[m
[32m+[m	[32m@PostMapping("/authenticate")[m
 	public AuthenticationResponse authenticate(@RequestBody UserDTO user) throws Exception {[m
 	       return userService.createJwtToken(user);[m
[31m-	   }[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
[32m+[m	[32m@PutMapping("/AddToFavorites")[m
[32m+[m	[32mpublic void addToFavorites(@RequestParam String username , @RequestParam long pictureID) {[m
[32m+[m		[32muserService.addToFavorites(username,pictureID);[m
[32m+[m[41m		[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
[32m+[m	[32m@PutMapping("/removeFromFavorites")[m
[32m+[m	[32mpublic void removeFromFavorites(@RequestParam String username , @RequestParam long pictureID) {[m
[32m+[m		[32muserService.removeFromFavorites(username, pictureID);[m
[32m+[m[41m		[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
[32m+[m	[32m@GetMapping("/getUser")[m
[32m+[m	[32mpublic AppUser getUser(@RequestParam String username) {[m
[32m+[m		[32mreturn userService.getUserByUsername(username);[m
[32m+[m[41m		[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
[32m+[m	[32m@GetMapping("/getFavorites")[m
[32m+[m	[32mpublic @ResponseBody Iterable<Picture> getFavorites(@RequestParam String username) {[m
[32m+[m		[32mreturn userService.getFavorites(username);[m
[32m+[m[41m		[m
[32m+[m	[32m}[m
[32m+[m[41m	[m
[32m+[m[41m	[m
 	[m
 }[m
