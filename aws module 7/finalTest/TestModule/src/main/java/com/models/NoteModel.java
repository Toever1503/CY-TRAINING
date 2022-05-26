package com.models;

import com.entities.Note;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteModel {

    private Long id;
    private String title;
    private String content;
    private Date createdDate;
    private Date updatedDate;

    public static Note toEntity(NoteModel model) {
        if (model == null) throw new RuntimeException("Node model is null");
        return Note.builder()
                .id(model.id)
                .title(model.title)
                .content(model.content)
                .createdDate(Calendar.getInstance().getTime())
                .updatedDate(model.updatedDate)
                .build();
    }
}
