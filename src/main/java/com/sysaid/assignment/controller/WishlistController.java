package com.sysaid.assignment.controller;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.service.UserService;
import com.sysaid.assignment.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WishlistController {
    private final WishlistService wishlistService;
    private final UserService userService;

    public WishlistController(WishlistService wishlistService, UserService userService){
        this.wishlistService = wishlistService;
        this.userService = userService;
    }

    @GetMapping("/wishlist/{user}")
    public String getWishlist(@PathVariable ("user") String user, Model model){
        List<Task> wishlist = wishlistService.findUserWishlist(user)
                .stream()
                .collect(Collectors.toList());
        model.addAttribute("userName", user);
        model.addAttribute("wishlist", wishlist);
        return "wishlist.html";
    }

    @GetMapping("/wishlist/add-task/{user}/{taskKey}")
    public ResponseEntity<?> addTaskToWishList(@PathVariable ("user") String user,
                                               @PathVariable ("taskKey") String taskKey){
        wishlistService.addTaskToWishlist(user, taskKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
