package com.yiyi.vaadin;

import com.vaadin.annotations.Title;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zgx on 2017/4/26.
 */
@SpringUI
@Title("第一个页面")
public class TodoUi extends UI {

    private VerticalLayout layout;

    @Autowired
    TodoList todoList;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
        addForm();
        addTodoList();
        addDeleteButton();
    }

    private void setupLayout() {
        layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    private void addHeader() {
        Label header = new Label("TODOS");
        header.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(header);
    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");

        TextField task = new TextField();
        Button add = new Button("");
        add.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        add.setIcon(VaadinIcons.PLUS);

        formLayout.addComponentsAndExpand(task);
        formLayout.addComponents(add);

        add.addClickListener(click-> {
            if (task.getValue().isEmpty()){
                Notification.show("内容为空！", "描述", Notification.Type.WARNING_MESSAGE);
                return;
            }
           todoList.add(new Todo(task.getValue()));
            task.clear();
        });
        task.clear();
        task.focus();
        add.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        layout.addComponent(formLayout);
    }

    private void addTodoList() {
        todoList.setWidth("80%");
        layout.addComponent(todoList);
    }

    private void addDeleteButton() {
        Button delete = new Button("delete");
        layout.addComponent(delete);
        delete.addClickListener(event -> {
            todoList.delSelect();
        });
    }
}
