package library.persistance.service.impl;

import library.config.Messages;
import library.exceptions.InvalidDtoException;
import library.model.dto.binding.UserRegistrationDto;
import library.model.dto.view.UserViewDto;
import library.model.entity.User;
import library.parser.api.ModelParser;
import library.persistance.repository.UserRepository;
import library.persistance.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelParser mapper;
    private final Messages messages;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository,
                           final ModelParser mapper,
                           final Messages messages) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.messages = messages;
    }

    @Override
    public boolean isValid(final String name) {
        return this.findOneByName(name) != null;
    }

    @Override
    public boolean registerUser(@Valid final UserRegistrationDto dto) throws InvalidDtoException {

        if (this.findOneByName(dto.getName()) != null) {
            throw new InvalidDtoException(this.messages.get("error.username"));
        }
        if (this.findOneByEmail(dto.getEmail()) != null) {
            throw new InvalidDtoException(this.messages.get("error.email"));
        }

        try {
            this.userRepository.saveAndFlush(this.mapper.convert(dto, User.class));
        } catch (Throwable e) {
            throw new InvalidDtoException(this.messages.get("error.persist.user") + e.getMessage());
        }

        return true;
    }

    @Override
    public User findOneByName(final String name) {
        return this.userRepository.findOneByName(name);
    }

    @Override
    public User findOneByEmail(final String email) {
        return this.userRepository.findOneByEmail(email);
    }

    @Override
    public List<UserViewDto> getAllUsers() {
        return this.userRepository
                .findAll()
                .stream()
                .map(user -> {
                    UserViewDto dto = this.mapper.convert(user, UserViewDto.class);
                    dto.setCurrentBooks(user.getBooks().size());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
