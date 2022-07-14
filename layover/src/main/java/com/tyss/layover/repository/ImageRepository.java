package com.tyss.layover.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.layover.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}
