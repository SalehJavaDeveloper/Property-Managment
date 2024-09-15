package com.example.property.config;

import com.example.property.abstraction.AuditAble;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Slf4j
@Transactional
public class ActivateConfiguration<T extends AuditAble> {

  public void setActivateEntity(T activateEntity){
     activateEntity.setActivate(true);
    }
  public void setDeactivateEntity(T deactivateEntity){
      deactivateEntity.setActivate(false);
  }
}
