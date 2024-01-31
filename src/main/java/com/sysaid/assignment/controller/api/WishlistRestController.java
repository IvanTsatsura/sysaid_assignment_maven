package com.sysaid.assignment.controller.api;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class WishlistRestController {
    private final WishlistService wishlistService;

    public WishlistRestController(WishlistService wishlistService){
        this.wishlistService = wishlistService;
    }

    @GetMapping("/api/wishlist/{user}")
    public ResponseEntity<Collection<Task>> getWishlist(@PathVariable("user") String user){
        Collection<Task> wishlist = wishlistService.findUserWishlist(user);
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }

    @GetMapping("/api/wishlist/add-task/{user}/{taskKey}")
    public ResponseEntity<?> addTaskToWishList(@PathVariable ("user") String user,
                                               @PathVariable ("taskKey") String taskKey){
        wishlistService.addTaskToWishlist(user, taskKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
