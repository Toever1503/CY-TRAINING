package com.service.impl;

import com.entities.Note;
import com.models.NoteModel;
import com.repository.NoteRepository;
import com.service.INoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class NoteServiceImpl implements INoteService {
    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note findById(Long id) {
        return this.noteRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Note> findAll(Pageable page) {
        return this.noteRepository.findAll(page);
    }

    @Override
    public Page<Note> search(String q, Pageable page) {
        return this.noteRepository.search(q, page);
    }

    @Override
    public List<Note> findAll() {
        return this.noteRepository.findAll();
    }

    @Override
    public Note add(NoteModel noteModel) {
        Note entity = NoteModel.toEntity(noteModel);
        entity.setUpdatedDate(entity.getCreatedDate());
        return this.noteRepository.save(entity);
    }

    @Override
    public Note update(NoteModel noteModel) {
        Note original = findById(noteModel.getId());
        if (original == null)
            return null;
        original.setTitle(noteModel.getTitle());
        original.setContent(noteModel.getContent());
        original.setUpdatedDate(Calendar.getInstance().getTime());
        return this.noteRepository.save(original);
    }

    @Override
    public boolean deleteById(Long id) {
        this.noteRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAllByIds(List<Long> ids) {
        ids.forEach(this::deleteById);
        return true;
    }
}
