package com.service.impl;

import com.entity.AuthorityEntity;
import com.entity.dto.AuthorityDto;
import com.entity.model.AuthorityModel;
import com.repository.AuthorityRepository;
import com.security.SecurityUtils;
import com.service.AuthorityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
        if (this.authorityRepository.count() == 0) {
            this.authorityRepository.save(new AuthorityEntity(null, SecurityUtils.ROLE_USER));
            this.authorityRepository.save(new AuthorityEntity(null, SecurityUtils.ROLE_ADMIN));
            this.authorityRepository.save(new AuthorityEntity(null, SecurityUtils.ROLE_EDITOR));
        }
    }

    AuthorityEntity toEntity(AuthorityModel authorityModel) {
        if (authorityModel == null) return null;
        return new AuthorityEntity(authorityModel.getId(), authorityModel.getAuthority());
    }

    @Override
    public AuthorityDto findById(Integer integer) {
        return AuthorityDto.toDto(authorityRepository.findById(integer).orElse(null));
    }

    @Override
    public Page<AuthorityDto> findAll(Pageable page) {
        return this.authorityRepository.findAll(page).map(AuthorityDto::toDto);
    }

    @Override
    public Page<AuthorityDto> search(String keyword, Pageable page) {
        return this.authorityRepository.findAllByAuthorityLike(keyword, page).map(AuthorityDto::toDto);
    }

    @Override
    public AuthorityDto add(AuthorityModel authorityModel) {
        return AuthorityDto.toDto(this.authorityRepository.save(toEntity(authorityModel)));
    }

    @Override
    public AuthorityDto update(AuthorityModel authorityModel) {
        return AuthorityDto.toDto(this.authorityRepository.save(toEntity(authorityModel)));
    }

    @Override
    public Boolean deleteById(Integer integer) {
        this.authorityRepository.deleteById(integer);
        return true;
    }
}
