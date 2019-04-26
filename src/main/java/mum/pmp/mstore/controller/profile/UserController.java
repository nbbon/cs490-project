package mum.pmp.mstore.controller.profile;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mum.pmp.mstore.model.Role;
import mum.pmp.mstore.model.User;
import mum.pmp.mstore.service.security.RoleService;
import mum.pmp.mstore.service.security.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView doUserList(Model model) {
        ModelAndView mv = new ModelAndView("user/index");
        System.out.println("In controller");
        model.addAttribute("userList", userService.getAllUsers());
        System.out.println(userService.getAllUsers().toString());
        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView doEdit(Model model, @ModelAttribute("user") User user,
                            @RequestParam(value = "id", required = false) Long id) {
        ModelAndView mv = new ModelAndView("admin/user/create");
        if (id != null) {
            User updatedUser = userService.getUserById(id);
            if (updatedUser.getRoles().size() == 1) {
                updatedUser.setRole(updatedUser.getRoles().get(0));
            }
            model.addAttribute("user", updatedUser);
        }
        return mv;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView doCreate(Model model, @ModelAttribute("user") User user) {
        ModelAndView mv = new ModelAndView("redirect:/admin/user/");
        User existingUser = userService.getUserByEmail(user.getUserId());
        if(existingUser != null && user.getId() == 0) {
            ModelAndView mv2 = new ModelAndView("admin/user/create");
            return mv2;
        } else {
            if (user.getId() != 0 && user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            }
            user.clearRoles();
            user.setRole(roleService.getRole("ADMIN"));
           // user.addRole(roleService.findOne(user.getRole()));
            userService.save(user);
        }
        return mv;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ModelAndView doDelete(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("redirect:/admin/user/");
        userService.delete(id);
        return mv;
    }

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return roleService.getAll();
    }
}
