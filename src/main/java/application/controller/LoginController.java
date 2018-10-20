package application.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

//    @RequestMapping("/login")
//    public String getLogin(@RequestParam(value = "error", required = false) String error,
//                           @RequestParam(value = "logout", required = false) String logout,
//                           HttpSession session,
//                           Model model) {
//        model.addAttribute("error", error != null);
//        model.addAttribute("logout", logout != null);
//        return "login";
//    }


    @RequestMapping("/welcome")
    public String login(Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int role = -1;
        if("[ROLE_ADMIN]".equals(auth.getAuthorities().toString())){
            role = 1;
        }else if("[ROLE_USER]".equals(auth.getAuthorities().toString())){
            role = 0;
        }
        request.getSession().setAttribute("options", role);
        return "redirect:/tours";
    }
}
