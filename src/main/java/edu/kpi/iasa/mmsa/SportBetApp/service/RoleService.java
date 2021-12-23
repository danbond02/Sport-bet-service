package edu.kpi.iasa.mmsa.SportBetApp.service;

import edu.kpi.iasa.mmsa.SportBetApp.exceptions.RoleNotFoundException;
import edu.kpi.iasa.mmsa.SportBetApp.model.Role;
import edu.kpi.iasa.mmsa.SportBetApp.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElseThrow(RoleNotFoundException::new);
    }
}
