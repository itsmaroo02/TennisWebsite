package ie.dbs.tennisclubsite.member;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


// Tells spring this class handles website requests
@Controller
@RequestMapping("/members") // base URL
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //*** Handles GET requests to /members ***//
    @GetMapping
    public String listMembers(Model model) {

        model.addAttribute(
                "members",
                memberService.getMembers()
        );

        return "member/list";
    }

    //*** Handles GET requests to /members/new ***//
    @GetMapping("/new")
    public String showMemberForm(Model model) {

        model.addAttribute(
                "memberForm",
                new MemberForm()
        );

        return "member/form";
    }

    //*** Handles POST requests to /members ***//
    @PostMapping
    public String createMember(
            @Valid
            @ModelAttribute("memberForm")
            MemberForm memberForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "member/form";
        }

        memberService.createMember(memberForm);

        return "redirect:/members";

    }

    //*** Handles requests to /members/{id}/edit ***//
    @GetMapping("/{id}/edit")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Member member =
                memberService.getMemberById(id);

        if (member == null) {
            return "redirect:/members";
        }

        MemberForm memberForm =
                new MemberForm(
                        member.getMemberName(),
                        member.getMemberEmail()
                );

        model.addAttribute(
                "memberForm",
                memberForm
        );

        model.addAttribute(
                "memberId",
                id
        );

        return "member/edit";

    }

    //*** Handles POST requests to update a specific member ***//
    @PostMapping("/{id}")
    public String updateMember(
            @PathVariable Long id,
            @Valid
            @ModelAttribute("memberForm")
            MemberForm memberForm,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("memberId", id);

            return "member/edit";
        }

        Member updatedMember =
                memberService.updateMember(
                        id,
                        memberForm
                );

        if (updatedMember == null) {
            return "redirect:/members";
        }

        return "redirect:/members";
    }

    //*** Handles POST requests to delete a specific member ***//
    @PostMapping("/{id}/delete")
    public String deleteMember(
            @PathVariable Long id) {

        memberService.deleteMember(id);

        return "redirect:/members";
    }
}
