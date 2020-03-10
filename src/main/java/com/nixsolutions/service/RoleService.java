package com.nixsolutions.service;

import com.nixsolutions.domain.Role;
import com.nixsolutions.repository.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@EnableTransactionManagement
public class RoleService implements RoleDao{

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void create(Role role) {
        roleRepository.saveAndFlush(role);
    }

    @Transactional
    public void update(Role role) {
        roleRepository.saveAndFlush(role);
    }

    @Transactional
    public void remove(Role role) {
        roleRepository.delete(role);
    }

    public Role findByName(String name){
        return roleRepository.findByName(name);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(Long id){
        return roleRepository.findByRoleId(id);
    }

}
