package com.yiyi.vaadin;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by zgx on 2017/4/27.
 */
@SpringComponent
public class TodoList extends VerticalLayout implements TodoChangeListener{

    @Autowired
    TodoRepository repository;

    @PostConstruct
    void init() {
        update();
    }

    private void update() {
        setTodos(repository.findAll());
    }

    private void setTodos(List<Todo> todos) {
        removeAllComponents();

        todos.forEach( todo -> addComponent(new TodoItemLayout(todo, this)));
    }

    public void add(Todo todo) {
        repository.save(todo);
        update();
    }

    public void delSelect() {
        repository.deleteByDone(true);
        update();
    }

    @Override
    public void todoChanged(Todo todo) {
        add(todo);
    }
}
