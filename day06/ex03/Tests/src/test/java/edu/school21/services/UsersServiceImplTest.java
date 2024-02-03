package edu.school21.services;

import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UsersServiceImplTest {

    @Test
    void authenticateWithCorrectLoginAndPassword() {
        UsersRepository mockRepository = Mockito.mock(UsersRepository.class);
        User existsUser = new User(1L, "andru", "querty", false);
        Mockito.when(mockRepository.findByLogin("andru")).thenReturn(existsUser);

        UsersServiceImpl usersService = new UsersServiceImpl(mockRepository);
        boolean authenticate = usersService.authenticate("andru", "querty");
        assertTrue(authenticate);
        assertTrue(existsUser.isAuthStatus());
        Mockito.verify(mockRepository, Mockito.times(1)).update(existsUser);
    }

    @Test
    void authenticateInvalidLogin() {
        UsersRepository mockRepository = Mockito.mock(UsersRepository.class);
        Mockito.when(mockRepository.findByLogin("notExistsUser")).thenThrow(EntityNotFoundException.class);
        UsersServiceImpl usersService = new UsersServiceImpl(mockRepository);
        boolean authenticate = usersService.authenticate("notExistsUser", "user");
        Mockito.verify(mockRepository, Mockito.never()).update(Mockito.any());
    }

    @Test
    void authenticateInvalidPassword() {
        UsersRepository mockRepository = Mockito.mock(UsersRepository.class);
        User existsUser = new User(1L, "andru", "querty", false);
        Mockito.when(mockRepository.findByLogin("andru")).thenReturn(existsUser);
        UsersServiceImpl usersService = new UsersServiceImpl(mockRepository);

        boolean authenticate = usersService.authenticate("andru", "notValidPassword");
        assertFalse(authenticate);
        assertFalse(existsUser.isAuthStatus());
        Mockito.verify(mockRepository, Mockito.never()).update(Mockito.any());
    }
}