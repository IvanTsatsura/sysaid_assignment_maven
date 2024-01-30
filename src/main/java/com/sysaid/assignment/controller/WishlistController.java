package com.sysaid.assignment.controller;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WishlistController {
    private WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService){
        this.wishlistService = wishlistService;
    }

    @GetMapping("/wishlist/{user}")
    public ResponseEntity<List<Task>> getWishlist(@PathVariable ("user") String user){
        return wishlistService.findUserWishlist(user);
    }

    @GetMapping("/wishlist/add-task/{user}/{taskKey}")
    public ResponseEntity<?> addTaskToWishList(@PathVariable ("user") String user,
                                               @PathVariable ("taskKey") String taskKey){
        return wishlistService.addTaskToWishlist(user, taskKey);
    }
}
