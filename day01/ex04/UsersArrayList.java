package day01.ex04;

import java.util.ArrayList;

public class UsersArrayList implements UsersList {

    public UsersArrayList() {
        users_ = new ArrayList<User>();
    }

    @Override
    public void addUser(User user) {
        users_.add(user);
    }

    @Override
    public int getNumberOfUsers() {
        return users_.size();
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        try {
            for (User user : users_) {
                if (user.getIdentifier() == id) {
                    return user;
                }
            }
            throw new UserNotFoundException("User not found with ID: " + id);
        } catch (Exception e) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }

    @Override
    public User getUserByIndex(int index) throws UserNotFoundException {
        try {
            return users_.get(index);
        } catch (Exception e) {
            throw new UserNotFoundException("User not found with index: " + index);
        }
    }

    private ArrayList<User> users_;
}
