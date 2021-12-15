package com.mateuswmachado.blog.repository;

import com.mateuswmachado.blog.model.Topic;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Topic, Long> {

}
