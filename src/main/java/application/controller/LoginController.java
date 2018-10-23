package application.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @RequestMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // if user already logged in
        if (!auth.getAuthorities().toString().equals("[ROLE_ANONYMOUS]")) {
            return "redirect:/tours";
        }
        model.addAttribute("error", error != null);
        return "loginEvent";
    }

    @RequestMapping("/welcome")
    public String login(Model model, HttpServletRequest request) {
        // set user role to session
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        request.getSession().setAttribute("role", auth.getAuthorities().toString());
        return "redirect:/tours";
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
