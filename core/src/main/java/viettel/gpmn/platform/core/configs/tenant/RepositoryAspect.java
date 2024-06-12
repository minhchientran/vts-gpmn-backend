package viettel.gpmn.platform.core.configs.tenant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Aspect
@Component
public class RepositoryAspect {
    @PersistenceContext
    private EntityManager entityManager;

    @Pointcut("execution(* org.springframework.data.jpa.repository.JpaRepository+.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object pointCutHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        Session session = entityManager.unwrap(Session.class);
        String supplierId = TenantContext.getSupplierId();
        if (supplierId != null) {
            Filter filter = session.enableFilter("supplierFilter");
            filter.setParameter("supplierId", supplierId);
        }
        Object result = joinPoint.proceed();
        session.disableFilter("supplierFilter");
        return result;
    }
}
