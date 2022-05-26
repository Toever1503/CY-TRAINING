package com.controller;

import com.entities.Note;
import com.models.NoteModel;
import com.service.INoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NoteController {
    private final INoteService iNoteService;

    public NoteController(INoteService iNoteService) {
        this.iNoteService = iNoteService;
    }

    @GetMapping
    public String home(@RequestParam(name = "q", defaultValue = "") String q, Pageable page, Model model) {
        String message = null;
        Page<Note> notePages = null;

        if (q.isEmpty())
            notePages = this.iNoteService.findAll(page);
        else {
            notePages = this.iNoteService.search(q, page);
            if (notePages.getTotalElements() == 0)
                model.addAttribute("search_", q);
        }
        model.addAttribute("notes", notePages);
        return "home";
    }

    @PostMapping("note")
    public String add(NoteModel model, RedirectAttributes redirectAttributes) {
        this.iNoteService.add(model);
        redirectAttributes.addFlashAttribute("message", "Add successfully!");
        return "redirect:/";
    }

    @PostMapping("note/update/{id}")
    public String update(@PathVariable Long id, NoteModel model, RedirectAttributes redirectAttributes) {
        model.setId(id);
        this.iNoteService.update(model);
        redirectAttributes.addFlashAttribute("message", "Update not successfully!");
        return "redirect:/";
    }

    @GetMapping("note/delete/{id}")
    public String deleteNote(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        this.iNoteService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Delete successfully!");
        return "redirect:/";
    }
}
