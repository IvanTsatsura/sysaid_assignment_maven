package com.sysaid.assignment.controller.api;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistRestController {
    private final WishlistService wishlistService;

    public WishlistRestController(WishlistService wishlistService){
        this.wishlistService = wishlistService;
    }

    @GetMapping("/{user}")
    public ResponseEntity<Collection<Task>> getWishlist(@PathVariable("user") String user){
        Collection<Task> wishlist = wishlistService.findUserWishlist(user);
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }

    @GetMapping("/add-task/{user}/{taskKey}")
    public ResponseEntity<?> addTaskToWishList(@PathVariable ("user") String user,
                                               @PathVariable ("taskKey") String taskKey){
        if (wishlistService.addTaskToWishlist(user, taskKey)){
            return new ResponseEntity<>("Task successfully added to wishlist",
                    HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/delete-task/{user}/{taskKey}")
    public ResponseEntity<?> deleteTaskFromWishList(@PathVariable ("user") String user,
                                               @PathVariable ("taskKey") String taskKey){
        if (wishlistService.deleteTaskFromWishlist(user, taskKey)){
            return new ResponseEntity<>("Task successfully deleted from wishlist",
                    HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update-task/{user}/{taskKey}")
    public ResponseEntity<?> updateTaskInWishlist(@PathVariable ("user") String user,
                                                  @RequestBody Task updatedTask){
        if (wishlistService.updateTaskInWishlist(user, updatedTask)){
            return new ResponseEntity<>("Task successfully updated in wishlist",
                    HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }
}
