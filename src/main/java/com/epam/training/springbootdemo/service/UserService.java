package com.epam.training.springbootdemo.service;

import java.util.ArrayList;
import java.util.List;

import com.epam.training.springbootdemo.model.User;
import com.epam.training.springbootdemo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll(int pageNumber, int rowPerPage) {
        List<User> users = new ArrayList<>();
        Pageable sortedByLastUpdateDesc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("id").ascending());
        userRepository.findAll(sortedByLastUpdateDesc).forEach(users::add);
        return users;
    }

    public User save(User user) throws Exception {
        if (StringUtils.isEmpty(user.getName())) {
            throw new Exception("Name is required");
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            throw new Exception("Email is required");
        }
        if (user.getId() != null && existsById(user.getId())) {
            throw new Exception("User with id: " + user.getId() + " already exists");
        }
        return userRepository.save(user);
    }

    public void update(User user) throws Exception {
        if (StringUtils.isEmpty(user.getName())) {
            throw new Exception("Name is required");
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            throw new Exception("Email is required");
        }
        if (!existsById(user.getId())) {
            throw new Exception("Cannot find User with id: " + user.getId());
        }
        userRepository.save(user);
    }

    public void deleteById(Long id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Cannot find User with id: " + id);
        }
        else {
            userRepository.deleteById(id);
        }
    }

    public Long count() {
        return userRepository.count();
    }
}