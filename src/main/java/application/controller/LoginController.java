package application.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            model.addAttribute("logout", true);
        }
        return "loginEvent";
    }

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

    @RequestMapping("/403")
    public String error403(Model model) {
        model.addAttribute("denied", true);
        return "/loginEvent";
    }

    @RequestMapping("/welcome")
    public String login(Model model, HttpServletRequest request) {
        // set user role to session
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        request.getSession().setAttribute("role", auth.getAuthorities().toString());
        return "redirect:/tours";
    }
}
