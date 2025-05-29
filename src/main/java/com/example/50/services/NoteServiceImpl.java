package com.example.W50.services;

import org.springframework.stereotype.Service;
import com.example.W50.models.Note;
import com.example.W50.repositories.NoteRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
  
    @Override
    public Note getById(long id) {
        return noteRepository.findById(id).orElse(null);
    }

    @Override
    public Note create(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Optional<Note> editNote(Long id, Note note) {
        Optional<Note> existingNote = noteRepository.findById(id);
        if (existingNote.isPresent()) {
            note.setId(id);
            return Optional.of(noteRepository.save(note));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void delete(long id) {
        noteRepository.deleteById(id);
    }
}
