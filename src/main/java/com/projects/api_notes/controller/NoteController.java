package com.projects.api_notes.controller;

import com.projects.api_notes.dto.request.NoteDTO;
import com.projects.api_notes.exception.NoteNotFoundException;
import com.projects.api_notes.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Nota", description = "Operação com anotações")
@RestController
@RequestMapping("/api/notes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NoteController {
    private NoteService service;

    @Operation(summary = "Criar Anotação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Anotação criada!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NoteDTO.class))})
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDTO createNote(@RequestBody @Valid NoteDTO note) {
        return service.createNote(note);
    }

    @Operation(summary = "Busca todas as anotações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrou todas as anotações.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NoteDTO.class))})
    })
    @GetMapping
    public ResponseEntity<Object> listAll() {
        List<NoteDTO> notes = service.listAll();

        if (notes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of("Mensagem", "Nenhuma anotação localizada."));
        }

        return ResponseEntity.ok(notes);
    }

    @Operation(summary = "Busca a anotação pela PK")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrou a anotação.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NoteDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Anotação não encontrada.", content = @Content)
    })
    @GetMapping("/{id}")
    public NoteDTO findByPk(@PathVariable("id") Long id) throws NoteNotFoundException {
        return service.findByPk(id);
    }

    @Operation(summary = "Atualiza a anotação pela PK")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anotação atualizada!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NoteDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Anotação não encontrada.", content = @Content)
    })
    @PutMapping("/{id}")
    public NoteDTO updatebByPk(@PathVariable("id") Long id, @RequestBody NoteDTO note) throws NoteNotFoundException {
        return service.updateNote(id, note);
    }

    @Operation(summary = "Deleta a anotação pela PK")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Anotação deletada.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NoteDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Anotação não encontrada.", content = @Content)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByPk(@PathVariable("id") Long id) throws NoteNotFoundException {
        service.deleteNote(id);
    }

}