package com.skynet.standard.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDaoImpl <T, PK extends Serializable> implements IBaseDao<T, PK>{  
    
  private Class<T> entityClass;  
  protected SessionFactory sessionFactory;  
    
  public BaseDaoImpl() {  
      this.entityClass = null;  
      Class<?> c = getClass();  
      Type type = c.getGenericSuperclass();  
      if (type instanceof ParameterizedType) {  
          Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();  
          this.entityClass = (Class<T>) parameterizedType[0];  
      }  
  }  
    
  @Resource  
  public void setSessionFactory(SessionFactory sessionFactory) {  
      this.sessionFactory = sessionFactory;  
  }  

  protected Session getSession() {  
      return sessionFactory.getCurrentSession();  
  }  

  public T get(PK id) {  
//      Assert.assertNotNull( "id is required", id);  
      return (T) getSession().get(entityClass, id);  
  }  

  public PK save(T entity) {  
//      Assert.assertNotNull("entity is required", entity);  
      return (PK) getSession().save(entity);  
  }  

}
