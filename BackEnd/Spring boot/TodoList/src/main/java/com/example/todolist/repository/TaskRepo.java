package com.example.todolist.repository;

import com.example.todolist.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepo extends PagingAndSortingRepository<Task, Long> {
    List<Task> findByStatusId(Long statusId);

    List<Task> findByTagId(Long id);

    @Query("select t " +
            "from Task t " +
            "where t.taskDescription=:taskDescription " +
            "and t.status.id=:statusId " +
            "and t.tag.id=:tagId")
    Optional<Task> findDuplicateTask(@Param("taskDescription") String taskDescription,
                                     @Param("statusId") Long statusId,
                                     @Param("tagId") Long tagId);
}
