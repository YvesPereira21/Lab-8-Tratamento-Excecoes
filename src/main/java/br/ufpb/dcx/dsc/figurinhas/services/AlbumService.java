package br.ufpb.dcx.dsc.figurinhas.services;

import br.ufpb.dcx.dsc.figurinhas.models.Album;
import br.ufpb.dcx.dsc.figurinhas.models.User;
import br.ufpb.dcx.dsc.figurinhas.repository.AlbumRepository;
import br.ufpb.dcx.dsc.figurinhas.repository.UserRepository;
import br.ufpb.dcx.dsc.figurinhas.validation.ItemNotFoundException;
import br.ufpb.dcx.dsc.figurinhas.validation.UsuarioPossuiAlbum;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlbumService {
    private AlbumRepository albumRepository;
    private UserRepository userRepository;

    public AlbumService(AlbumRepository albumRepository, UserRepository userRepository)
    {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
    }

    public Album getAlbum(Long id){
        return albumRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Álbum não encontrado"));
    }

    public List<Album> listAlbuns() {
        return albumRepository.findAll();
    }

    public Album saveAlbum(Album a, Long userId) {
        User userOpt = userRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("Usuário não encontrado"));
        List<Album> albums = userOpt.getAlbuns();
        for (Album album : albums) {
            if (album.getNome().equals(a.getNome())) {
                throw new UsuarioPossuiAlbum("Usuário já possui esse álbum");
            }
        }
        a.setUser(userOpt);
        return albumRepository.save(a);
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    public Album updateAlbum(Long id, Album f) {
        Album figOpt = albumRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Álbum não encontrado"));
        figOpt.setNome(f.getNome());
        return albumRepository.save(figOpt);
    }
}
