package com.projects.api_notes.service;

import com.projects.api_notes.dto.mapper.NoteMapper;
import com.projects.api_notes.dto.request.NoteDTO;
import com.projects.api_notes.entity.Note;
import com.projects.api_notes.exception.NoteNotFoundException;
import com.projects.api_notes.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NoteService {
    private final NoteRepository repository;
    private final NoteMapper noteMapper = NoteMapper.INSTANCE;

    public NoteDTO createNote(NoteDTO dto) {
        dto.setData_criacao(new Date());
        Note noteToSave = noteMapper.toModel(dto);
        Note newNote = repository.save(noteToSave);

        return noteMapper.toDTO(newNote);
    }

    public List<NoteDTO> listAll() {
        List<Note> allNotes = repository.findAll();
        return allNotes.stream().map(noteMapper::toDTO).collect(Collectors.toList());
    }

    public NoteDTO findByPk(Long id) throws NoteNotFoundException {
        Note note = verifyExists(id);
        return noteMapper.toDTO(note);
    }

    public NoteDTO updateNote(Long id, NoteDTO dto) throws NoteNotFoundException {
        Note notaAtual = verifyExists(id);

        notaAtual.setTitulo(dto.getTitulo());
        notaAtual.setConteudo(dto.getConteudo());
        notaAtual.setData_atualizacao(new Date());

        Note updNote = repository.save(notaAtual);
        return noteMapper.toDTO(updNote);
    }

    public void deleteNote(Long id) throws NoteNotFoundException {
        verifyExists(id);
        repository.deleteById(id);
    }

    private Note verifyExists(Long id) throws NoteNotFoundException {
        return  repository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
    }

}