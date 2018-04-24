package library.persistance.service.api;

import library.exceptions.InvalidDtoException;
import library.model.dto.binding.UserRegistrationDto;
import library.model.dto.view.UserViewDto;

import java.util.List;

public interface UserService {

    boolean isValid(final String name);

    boolean registerUser(final UserRegistrationDto dto) throws InvalidDtoException;

    List<UserViewDto> getAllUsers();
}
