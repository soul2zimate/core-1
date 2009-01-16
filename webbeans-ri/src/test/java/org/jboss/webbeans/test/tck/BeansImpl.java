package org.jboss.webbeans.test.tck;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.webbeans.manager.Bean;

import org.jboss.webbeans.CurrentManager;
import org.jboss.webbeans.bean.AbstractClassBean;
import org.jboss.webbeans.bean.EnterpriseBean;
import org.jboss.webbeans.bean.ProducerFieldBean;
import org.jboss.webbeans.bean.ProducerMethodBean;
import org.jboss.webbeans.bean.SimpleBean;
import org.jboss.webbeans.tck.api.Beans;

/**
 * Implements the Beans SPI for the TCK specifically for the JBoss RI.
 * 
 * @author Shane Bryzak
 * @author Pete Muir
 * @author David Allen
 * 
 */
public class BeansImpl implements Beans
{

   public <T> Bean<T> createSimpleBean(Class<T> clazz)
   {
      return SimpleBean.of(clazz, CurrentManager.rootManager());
   }

   public <T> Bean<T> createProducerMethodBean(Method method, Bean<?> declaringBean)
   {
      if (declaringBean instanceof AbstractClassBean)
      {
         return ProducerMethodBean.of(method, (AbstractClassBean<?>) declaringBean, CurrentManager.rootManager());
      }
      else
      {
         throw new IllegalStateException("Cannot create a producer method from a bean that wasn't created by the RI " + declaringBean);
      }
   }
   
   public <T> Bean<T> createProducerFieldBean(Field field, Bean<?> declaringBean)
   {
      if (declaringBean instanceof AbstractClassBean)
      {
         return ProducerFieldBean.of(field, (AbstractClassBean<?>) declaringBean, CurrentManager.rootManager());
      }
      else
      {
         throw new IllegalStateException("Cannot create a producer field from a bean that wasn't created by the RI " + declaringBean);
      }
   }

   public <T> Bean<T> createEnterpriseBean(Class<T> clazz)
   {
      return EnterpriseBean.of(clazz, CurrentManager.rootManager());
   }

   public boolean isEnterpriseBean(Class<?> clazz)
   {
      return CurrentManager.rootManager().getEjbDescriptorCache().containsKey(clazz);
   }

   public boolean isEntityBean(Class<?> clazz)
   {
      // TODO Auto-generated method stub
      return false;
   }

   public boolean isStatefulBean(Class<?> clazz)
   {
      // TODO Auto-generated method stub
      return false;
   }

   public boolean isStatelessBean(Class<?> clazz)
   {
      // TODO Auto-generated method stub
      return false;
   }

}
