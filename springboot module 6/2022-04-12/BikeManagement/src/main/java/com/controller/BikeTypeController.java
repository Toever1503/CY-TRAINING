package com.controller;


import com.domain.model.BikeTypeMODEL;
import com.service.IBikeTypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/bikeType")
public class BikeTypeController {

    private final IBikeTypeService iBikeTypeService;

    public BikeTypeController(IBikeTypeService bikeTypeService) {
        this.iBikeTypeService = bikeTypeService;
    }


    @GetMapping
    public ResponseEntity<?> getAll(Pageable page) {
        return ResponseEntity.ok(this.iBikeTypeService.findAll(page));
    }

    @GetMapping("ticketTypes")
    public ResponseEntity<?> getAllTicketTypes(Pageable page) {
        return ResponseEntity.ok(this.iBikeTypeService.findAllTickets(page));
    }

    @GetMapping("bikeTypes")
    public ResponseEntity<?> getAllBikeTypes(Pageable page) {
        return ResponseEntity.ok(this.iBikeTypeService.findAllBikeTypes(page));
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getBikeType(@PathVariable Long id) {
        return ResponseEntity.ok(this.iBikeTypeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody BikeTypeMODEL bikeType) {
        System.out.println("save-> " + bikeType);
        bikeType.setId(null);
        return ResponseEntity.ok(this.iBikeTypeService.save(bikeType));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable Long id, @RequestBody BikeTypeMODEL bikeType) {
        System.out.println("update-> " + bikeType);
        bikeType.setId(id);
        return ResponseEntity.ok(this.iBikeTypeService.save(bikeType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Long id) {
        return ResponseEntity.ok(this.iBikeTypeService.deleteById(id));
    }

    @GetMapping("changeToTicketType/{id}")
    public ResponseEntity<?> changeToTicketType(@PathVariable Long id) {
        return ResponseEntity.ok(this.iBikeTypeService.changeToTicketType(id));
    }
}
