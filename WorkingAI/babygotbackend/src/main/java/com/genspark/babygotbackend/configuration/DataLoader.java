package com.genspark.babygotbackend.configuration;

import com.genspark.babygotbackend.entity.Group;
import com.genspark.babygotbackend.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private GroupRepository groupRepository;

    @Autowired
    public DataLoader(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
        createRoles();
    }

    private void createRoles() {
        try {
            //check for empty table, add group/roles
            if (groupRepository.count() == 0) {
                groupRepository.save(new Group("administrator", "ROLE_ADMINISTRATOR"));
                groupRepository.save(new Group("user", "ROLE_USER"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
