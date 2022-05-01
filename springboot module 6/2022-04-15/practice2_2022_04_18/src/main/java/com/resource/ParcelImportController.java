package com.resource;

import com.entity.dto.ResponseData;
import com.entity.model.ParcelImportModel;
import com.service.ParcelImportService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/parcelImport")
public class ParcelImportController {

    private final ParcelImportService parcelImportService;

    public ParcelImportController(ParcelImportService parcelImportService) {
        this.parcelImportService = parcelImportService;
    }

    @GetMapping
    public ResponseEntity getAllParcelsImport(@RequestParam(name = "q", required = false, defaultValue = "") String q, Pageable page) {
        if (q != null || !q.isBlank()) {
            return ResponseData.get(parcelImportService.search(q, page));
        }
        return ResponseData.get(parcelImportService.findAll(page));
    }

    @GetMapping("{id}")
    public ResponseEntity getParcelImportById(@PathVariable("id") Long id) {
        return ResponseData.get(parcelImportService.findById(id));
    }

    @PostMapping
    public ResponseEntity createParcel(@RequestBody ParcelImportModel parcelImportModel) {
        return ResponseData.get(parcelImportService.saveOrUpdate(parcelImportModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteParcelImportById(@PathVariable("id") Long id) {
        return ResponseData.get(parcelImportService.deleteBYId(id));
    }
}
