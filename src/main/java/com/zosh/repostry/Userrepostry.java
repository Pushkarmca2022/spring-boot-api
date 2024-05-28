package com.zosh.repostry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.models.User;

public interface Userrepostry extends JpaRepository<User,Integer>{

}
