package com.controller;

import com.domain.model.BikeTicketSearchModel;
import com.domain.model.BikeTicketMODEL;
import com.service.IBikeTicketService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/ticket")
public class TicketController {
    private final IBikeTicketService iBikeTicketService;

    public TicketController(IBikeTicketService iBikeTicketService) {
        this.iBikeTicketService = iBikeTicketService;
    }

    @GetMapping
    public ResponseEntity<?> getTickets(Pageable page) {
        return ResponseEntity.ok(this.iBikeTicketService.findAll(page));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTicket(@PathVariable Long id) {
        return ResponseEntity.ok(this.iBikeTicketService.findById(id));
    }


    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody BikeTicketMODEL ticket) {
        ticket.setId(null);
        System.out.println("save ticket_> " + ticket);
        return ResponseEntity.ok(this.iBikeTicketService.save(ticket));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable Long id, @RequestBody BikeTicketMODEL ticket) {
        ticket.setId(id);
        System.out.println("update ticket_> " + ticket);
        return ResponseEntity.ok(this.iBikeTicketService.save(ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Long id) {
        return ResponseEntity.ok(this.iBikeTicketService.deleteById(id));
    }

    @GetMapping("search")
    public Object search(BikeTicketSearchModel search, Pageable page) {
        System.out.println("search_> " + search);
        return this.iBikeTicketService.search(search, page);
    }
}
