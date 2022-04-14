package com.example.practice_2022_04_08.domain.dto;

import com.example.practice_2022_04_08.domain.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinalPointDTO { // tạo đối tượng thông tin điểm để in ra file csv
    private Long subjectId;
    private Long studentId;
    private String studentName;
    private String subjectName;
    private String pointType;
    private Float point;
    private Date pointDate;

    // map dto
    public static FinalPointDTO toFinalPointDTO(Point point) {
        FinalPointDTO dto = new FinalPointDTO();
        dto.setSubjectId(point.getRegisteredSubject().getAvailSubject().getSubject().getId());
        dto.setSubjectName(point.getRegisteredSubject().getAvailSubject().getSubject().getSubjectName());
        dto.setPoint(point.getPoint());
        dto.setPointType(point.getPointType());
        dto.setPointDate(point.getPointDate());
        return dto;
    }
}
