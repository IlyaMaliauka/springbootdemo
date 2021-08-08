package com.epam.training.springbootdemo.repos;

import com.epam.training.springbootdemo.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long>,
        JpaSpecificationExecutor<User> {

    User findByUsername(String username);
}
