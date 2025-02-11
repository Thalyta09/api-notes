package com.projects.api_notes.dto.mapper;

import com.projects.api_notes.dto.request.NoteDTO;
import com.projects.api_notes.entity.Note;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoteMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    Note toModel(NoteDTO noteDTO);

    NoteDTO toDTO(Note note);
}