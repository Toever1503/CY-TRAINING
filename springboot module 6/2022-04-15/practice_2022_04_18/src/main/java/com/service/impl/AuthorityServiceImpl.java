package com.service.impl;

import com.domain.Authority;
import com.domain.model.AuthorityModel;
import com.repository.AuthorityRepository;
import com.service.AuthorityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    AuthorityModel toModel(Authority obj) {
        if (obj == null) return null;
        return AuthorityModel.builder()
                .id(obj.getId())
                .roleName(obj.getRoleName())
                .build();
    }

    Authority toEntity(AuthorityModel model) {
        if (model == null) return null;
        return Authority.builder()
                .id(model.getId())
                .roleName(model.getRoleName())
                .build();
    }

    @Override
    public AuthorityModel saveOrUpdate(AuthorityModel model) {
        return this.toModel(this.authorityRepository.save(this.toEntity(model)));
    }

    @Override
    public AuthorityModel findById(Integer id) {
        return toModel(authorityRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteById(Integer id) {
        this.authorityRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<AuthorityModel> findAll(Pageable pageable) {
        return this.authorityRepository.findAll(pageable).map(this::toModel);
    }

    @Override
    public Page<AuthorityModel> search(String q, Pageable pageable) {
        return this.authorityRepository.findAllByRoleNameLike("%".concat(q).concat("%"), pageable).map(this::toModel);
    }
}
