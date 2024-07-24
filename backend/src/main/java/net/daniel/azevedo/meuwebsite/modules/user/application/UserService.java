package net.daniel.azevedo.meuwebsite.modules.user.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.daniel.azevedo.meuwebsite.modules.user.dto.UpdateUserDTO;
import net.daniel.azevedo.meuwebsite.modules.user.domain.entities.User;
import net.daniel.azevedo.meuwebsite.modules.user.domain.exceptions.UserNotFoundException;
import net.daniel.azevedo.meuwebsite.modules.user.domain.exceptions.UsernameUniqueViolationException;
import net.daniel.azevedo.meuwebsite.modules.user.domain.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();

    }

    @Transactional
    public User create(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Username %s already exists", user.getUsername()), ex);
        }
    }


    @Transactional(readOnly = true)
    public User findById(UUID userId) {

        return userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException(String.format("User %s not found", userId))
        );

    }

    @Transactional
    public void deleteById(UUID userId) {

        User user = findById(userId);

        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(userId);
        }
    }

    @Transactional
    public User update(UUID userId, UpdateUserDTO updateUserDTO) {

        User user = findById(userId);
        user.setAddress(updateUserDTO.getAddress());
        user.setPassword(updateUserDTO.getPassword());
        user.setEmail(updateUserDTO.getEmail());

        return userRepository.save(user);

    }


}
