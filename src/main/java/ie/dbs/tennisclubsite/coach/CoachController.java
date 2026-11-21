package ie.dbs.tennisclubsite.coach;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


// Tells spring this class handles website requests
@Controller
@RequestMapping("/coaches") // base URL
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    //*** Handles GET requests to /coaches ***//
    @GetMapping
    public String listCoaches(Model model) {

        model.addAttribute(
                "coaches",
                coachService.getCoaches()
        );

        return "coach/list";
    }

    //*** Handles GET requests to /coaches/new ***//
    @GetMapping("/new")
    public String showCoachForm(Model model) {

        model.addAttribute(
                "coachForm",
                new CoachForm()
        );

        return "coach/form";
    }

    //*** Handles POST requests to /coaches ***//
    @PostMapping
    public String createCoach(
            @Valid
            @ModelAttribute("coachForm")
            CoachForm coachForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "coach/form";
        }

        coachService.createCoach(coachForm);

        return "redirect:/coaches";

    }

    //*** Handles requests to /coaches/{id}/edit ***//
    @GetMapping("/{id}/edit")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Coach coach =
                coachService.getCoachById(id);

        if (coach == null) {
            return "redirect:/coaches";
        }

        CoachForm coachForm =
                new CoachForm(
                        coach.getCoachName(),
                        coach.getCoachEmail()
                );

        model.addAttribute(
                "coachForm",
                coachForm
        );

        model.addAttribute(
                "coachId",
                id
        );

        return "coach/edit";

    }

    //*** Handles POST requests to update a specific coach ***//
    @PostMapping("/{id}")
    public String updateCoach(
            @PathVariable Long id,
            @Valid
            @ModelAttribute("coachForm")
            CoachForm coachForm,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("coachId", id);

            return "coach/edit";
        }

        Coach updatedCoach =
                coachService.updateCoach(
                        id,
                        coachForm
                );

        if (updatedCoach == null) {
            return "redirect:/coaches";
        }

        return "redirect:/coaches";
    }

    //*** Handles POST requests to delete a specific coach ***//
    @PostMapping("/{id}/delete")
    public String deleteCoach(
            @PathVariable Long id) {

        coachService.deleteCoach(id);

        return "redirect:/coaches";
    }
}
