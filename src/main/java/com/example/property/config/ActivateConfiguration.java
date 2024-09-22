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
     log.info("entity is {}",activateEntity);
      System.out.println("salammmm");
    }
  public void setDeactivateEntity(T deactivateEntity){
      deactivateEntity.setActivate(false);
      log.info("entity is {}",deactivateEntity);
      System.out.println("salammmm");
  }
}
