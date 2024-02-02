package com.sysaid.assignment.controller;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.service.CompleteTaskService;
import com.sysaid.assignment.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;
    private final CompleteTaskService completeTaskService;

    public WishlistController(WishlistService wishlistService,
                              CompleteTaskService completeTaskService){
        this.wishlistService = wishlistService;
        this.completeTaskService = completeTaskService;
    }

    @GetMapping("/{user}")
    public String getWishlist(@PathVariable ("user") String user, Model model){
        List<Task> wishlist = wishlistService.findUserWishlist(user)
                .stream()
                .collect(Collectors.toList());
        model.addAttribute("userName", user);
        model.addAttribute("wishlist", wishlist);
        return "wishlist.html";
    }

    @PostMapping("/{user}")
    public String fromWishlistToCompleted(@PathVariable("user") String user,
                                @RequestParam(name = "taskKey") String taskKey,
                                @RequestParam(name = "buttonType") String buttonType,
                                Model model) {
        if (buttonType.equals("complete")){
            completeTaskService.addTaskToCompleted(user, taskKey);
        }
        wishlistService.deleteTaskFromWishlist(user, taskKey);

        List<Task> wishlist = wishlistService.findUserWishlist(user)
                .stream()
                .collect(Collectors.toList());
        model.addAttribute("userName", user);
        model.addAttribute("wishlist", wishlist);
        return "wishlist.html";
    }
}
