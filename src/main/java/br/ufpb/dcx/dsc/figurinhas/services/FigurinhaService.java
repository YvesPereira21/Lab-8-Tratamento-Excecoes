package br.ufpb.dcx.dsc.figurinhas.services;

import br.ufpb.dcx.dsc.figurinhas.models.Figurinha;
import br.ufpb.dcx.dsc.figurinhas.repository.FigurinhaRepository;
import br.ufpb.dcx.dsc.figurinhas.validation.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FigurinhaService {
    private final ArrayList<Figurinha> figurinhaList= new ArrayList<>();
    private FigurinhaRepository figurinhaRepository;

    public FigurinhaService(FigurinhaRepository figurinhaRepository){
        this.figurinhaRepository = figurinhaRepository;
    }

    public Figurinha getFigurinha(Long id){
        Figurinha figurinha = figurinhaRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Figurinha com id " + id + " não encontrada"));
        return figurinha;
    }

    public List<Figurinha> listFigurinhas() {
        return figurinhaRepository.findAll();
    }

    public Figurinha saveFigurinha(Figurinha f) {
        return figurinhaRepository.save(f);
    }

    public void deleteFigurinha(Long id) {
        figurinhaRepository.deleteById(id);
    }

    public Figurinha updateFigurinha(Long id, Figurinha f) {
        Figurinha figOpt = figurinhaRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Figurinha não encontrada"));
        figOpt.setSelecao(f.getSelecao());
        figOpt.setNome(f.getNome());
        return figurinhaRepository.save(figOpt);
    }
}
