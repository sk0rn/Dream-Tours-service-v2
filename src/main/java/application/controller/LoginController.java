package application.controller;

import application.domain.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class LoginController extends ProtoController {

    @RequestMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                           Model  model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // if user already logged in
        if (!auth.getAuthorities().toString().equals("[ROLE_ANONYMOUS]")) {
            return "redirect:/tours";
        }
        model.addAttribute("error", error != null);
        return "loginEvent";
    }

    @RequestMapping("/welcome")
    public String welcome(Model model, HttpServletRequest request) {
        // set user role to session
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Set<Role> authorities = new HashSet<>((Collection<? extends Role>) auth.getAuthorities());
        Set<String> roles = authorities.stream().map(Role::getAuthority).collect(Collectors.toSet());
        request.getSession().setAttribute("roles", roles);
        return "redirect:/tours?welcome";
    }

    @RequestMapping("/loginevents")
    public String handleLoginEvents(@RequestParam(value = "logout", required = false) String logout,
                                    @RequestParam(value = "denied", required = false) String denied,
                                    Model model) {
        model.addAttribute("logout", logout != null);
        model.addAttribute("denied", denied != null);
        return "loginEvent";
    }
}
