package br.ufpb.dcx.dsc.figurinhas.services;

import br.ufpb.dcx.dsc.figurinhas.models.Photo;
import br.ufpb.dcx.dsc.figurinhas.models.User;
import br.ufpb.dcx.dsc.figurinhas.repository.AlbumRepository;
import br.ufpb.dcx.dsc.figurinhas.repository.FigurinhaRepository;
import br.ufpb.dcx.dsc.figurinhas.repository.PhotoRepository;
import br.ufpb.dcx.dsc.figurinhas.repository.UserRepository;
import br.ufpb.dcx.dsc.figurinhas.validation.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private PhotoRepository photoRepository;

    private AlbumRepository albumRepository;
    private FigurinhaRepository figurinhaRepository;

    public UserService(FigurinhaRepository figurinhaRepository, AlbumRepository albumRepository, UserRepository userRepository, PhotoRepository photoRepository){
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
        this.albumRepository = albumRepository;
        this.figurinhaRepository = figurinhaRepository;
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("Usuário não encontrado"));
    }

    public User createUser(User user){
        Photo photo = new Photo("www.exemplo.com/foto.png");
        photoRepository.save(photo);
        user.setPhoto(photo);
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User u) {
        User userOpt = userRepository.findById(userId).
                orElseThrow(() -> new ItemNotFoundException("User with " + userId + " not found"));
        userOpt.setEmail(u.getEmail());
        userOpt.setNome(u.getNome());
        return userRepository.save(userOpt);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new ItemNotFoundException("User with " + userId + " not found"));
        userRepository.delete(user);
    }

//    public Album share(Long albumId, Long userId, Long figId){
//        Optional<User> uOpt = userRepository.findById(userId);
//        Optional<Album> aOpt = albumRepository.findById(albumId);
//        Optional<Figurinha> fOpt = figurinhaRepository.findById(albumId);
//
//        if(uOpt.isPresent() && aOpt.isPresent() && fOpt.isPresent()){
//            if(aOpt.get().getUser().getUserId() == uOpt.get().getUserId()){
//                Album a = aOpt.get();
//                a.getFigurinhas().add(fOpt.get());
//                return albumRepository.save(a);
//            }
//        }
//
//        return null;
//    }
/*

    public User unshare(Long boardId, Long userId) {
        Optional<User> uOpt = userRepository.findById(userId);
        Optional<Board> bOpt = boardRepository.findById(boardId);

        if(uOpt.isPresent() && bOpt.isPresent()){
            User u = uOpt.get();
            u.getBoardsShared().remove(bOpt.get());
            return userRepository.save(u);
        }
        return null;
    }

*/

}