package org.launchcode.techjobs.persistent.controllers;


import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillController {
    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";

    }
@GetMapping(value="/")
    public String index(Model model) {
        List<Skill> skills = skillRepository.findAll();
        model.addAttribute("skills", skills);
        return "skills/index";
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                      Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "skills/add";
        }
        skillRepository.save(newSkill);

        return "redirect:";
    }

    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {
        Optional optionalSkill = skillRepository.findById(skillId);
        if (optionalSkill.isPresent()) {
            Skill skill = (Skill) optionalSkill.get();
            model.addAttribute("skill", skill);
            return "skill/view";
        } else {
            return "redirect:../";

        }
    }
}
