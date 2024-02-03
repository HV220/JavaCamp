package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {
    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    boolean authenticate(String login, String password) {
        try {
            User user = usersRepository.findByLogin(login);
            if (user.isAuthStatus()) {
                throw new AlreadyAuthenticatedException("User is already authenticated.");
            }
            if (user.getPassword().equals(password)) {
                user.setAuthStatus(true);
                usersRepository.update(user);
                return true;
            } else {
                return false;
            }
        } catch (EntityNotFoundException e) {
            return false;
        }


    }
}
