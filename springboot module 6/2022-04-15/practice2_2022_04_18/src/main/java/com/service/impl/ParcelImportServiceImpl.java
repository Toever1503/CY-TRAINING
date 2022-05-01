package com.service.impl;

import com.entity.ParcelImport;
import com.entity.ParcelImportDetail;
import com.entity.dto.ParcelImportDetailDto;
import com.entity.dto.ParcelImportDto;
import com.entity.model.ParcelImportDetailModel;
import com.entity.model.ParcelImportModel;
import com.repository.ParcelImportRepository;
import com.service.ParcelImportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ParcelImportServiceImpl implements ParcelImportService {

    private final ParcelImportRepository parcelImportRepository;

    public ParcelImportServiceImpl(ParcelImportRepository parcelImportRepository) {
        this.parcelImportRepository = parcelImportRepository;
    }

    ParcelImportDetail toEntity(ParcelImportDetailModel model) {
        if (model == null) return null;
        return ParcelImportDetail.builder()
                .id(model.getId())
                .price(model.getPrice())
                .quantity(model.getQuantity())
                .build();
    }

    ParcelImportDetailDto toDto(ParcelImportDetail entity) {
        if (entity == null) return null;
        return ParcelImportDetailDto.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .productImport(entity.getProductImport() != null ? entity.getProductImport().getName() : null)
                .parcelImport(entity.getParcelImport() != null ? entity.getParcelImport().getId() : null)
                .build();
    }


    ParcelImport toEntity(ParcelImportModel model) {
        if (model == null) return null;
        return ParcelImport.builder()
                .id(model.getId())
                .build();
    }

    ParcelImportDto toDto(ParcelImport entity) {
        if (entity == null) return null;
        return ParcelImportDto.builder()
                .id(entity.getId())
                .importedBy(entity.getImportedBy() != null ? entity.getImportedBy().getUsername() : null)
                .parcelDetailDtos(entity.getParcelImportDetails() != null ? entity.getParcelImportDetails().stream().map(this::toDto).collect(java.util.stream.Collectors.toList()) : null)
                .build();
    }


    @Override
    public ParcelImportDto saveOrUpdate(ParcelImportModel parcelImportModel) {
        ParcelImport entity = toEntity(parcelImportModel);
        if (parcelImportModel.getId() != null) {
            entity.setParcelImportDetails(parcelImportModel.getParcelDetails().stream().map(dt -> {
                ParcelImportDetail detail = toEntity(dt);
                detail.setParcelImport(entity);
                return detail;
            }).collect(java.util.stream.Collectors.toList()));
        }
        return toDto(parcelImportRepository.save(entity));
    }

    @Override
    public ParcelImportDto findById(Long aLong) {
        return toDto(parcelImportRepository.findById(aLong).orElse(null));
    }

    @Override
    public boolean deleteBYId(Long aLong) {
        this.parcelImportRepository.deleteById(aLong);
        return true;
    }

    @Override
    public Page<ParcelImportDto> findAll(Pageable pageable) {
        return this.parcelImportRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    public Page<ParcelImportDto> search(String q, Pageable pageable) {
        return null;
    }
}
