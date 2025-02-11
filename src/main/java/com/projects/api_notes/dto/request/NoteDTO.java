package com.projects.api_notes.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {

    @Schema(hidden = true)
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 50)
    @Schema(description = "Título da anotação", example = "Minha Nota")
    private String titulo;

    @NotEmpty
    @Size(min = 2, max = 1000)
    @Schema(description = "Conteúdo da anotação", example = "Conteúdo da minha anotação.")
    private String conteudo;

    @JsonProperty("dataCriacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    @Schema(hidden = true)
    private Date data_criacao;

    @JsonProperty("dataAtualizacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    @Schema(hidden = true)
    private Date data_atualizacao;

}