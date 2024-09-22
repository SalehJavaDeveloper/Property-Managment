package com.example.property.service.impl.role_impl;

import com.example.property.dto.role_request.PermissionRequestDto;
import com.example.property.dto.role_response.PermissionResponseDto;
import com.example.property.entity.companyRoles.Permission;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.mapper.role.PermissionMapper;
import com.example.property.repository.role.PacketRepository;
import com.example.property.repository.role.PermissionRepository;
import com.example.property.service.role.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionsServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;
    private final PacketRepository packetRepository;
    @Override
    public void savePermission(List<PermissionRequestDto> permissionRequestDto) {
        if(permissionRequestDto == null){
            throw new MethodArgumentNotValidException(("Data Arguments is not valid!"));
        }
        for (PermissionRequestDto permissionRequestDto1 : permissionRequestDto) {
            Permission permission = permissionMapper.fromDTO(permissionRequestDto1);
            permission.setPacket(packetRepository.getReferenceById(permissionRequestDto1.getPacketId()));
            permissionRepository.save(permission);
        }

    }

    @Override
    public PermissionResponseDto findPermissionsById(Long id) {
        if(permissionRepository.findById(id).isEmpty()){
            throw new MethodArgumentNotValidException("Data not found with specified Permission!");
        }
        return permissionMapper.toDTO(permissionRepository.getReferenceById(id));
    }

    @Override
    public List<PermissionResponseDto> findAllPermissions() {
        return permissionMapper.toDTOList(permissionRepository.findAll());
    }
}
