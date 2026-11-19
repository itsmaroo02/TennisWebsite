package ie.dbs.tennisclubsite.preference;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequestMapping("/preferences")
public class PreferenceController {

    private static final Set<String> ALLOWED_PAGE_SIZES =
            Set.of("10", "20", "50");

    @GetMapping
    public String showPreferences(
            @CookieValue(
                    value = "pageSize",
                    defaultValue = "10"
            ) String pageSize,
            Model model) {

        if (!ALLOWED_PAGE_SIZES.contains(pageSize)) {
            pageSize = "10";
        }

        model.addAttribute("pageSize", pageSize);

        return "preference/view";
    }

    @PostMapping("/page-size")
    public String setPageSize(
            @RequestParam String pageSize,
            HttpServletResponse response) {

        if (!ALLOWED_PAGE_SIZES.contains(pageSize)) {
            return "redirect:/preferences";
        }

        Cookie cookie = new Cookie("pageSize", pageSize);

        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30);

        response.addCookie(cookie);

        return "redirect:/preferences";
    }
}
