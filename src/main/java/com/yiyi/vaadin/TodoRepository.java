package com.yiyi.vaadin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zgx on 2017/4/27.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Transactional
    void deleteByDone(boolean isDone);
}
