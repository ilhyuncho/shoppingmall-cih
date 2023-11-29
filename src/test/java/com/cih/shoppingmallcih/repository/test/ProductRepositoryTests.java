package com.cih.shoppingmallcih.repository.test;

import com.cih.shoppingmallcih.domain.test.product.Product;
import com.cih.shoppingmallcih.domain.test.product.ProductRepository;
import com.cih.shoppingmallcih.domain.test.product.QProduct;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@DataJpaTest    // 기본값으로 임베디드 데이터베이스를 사용(h2), 자동 롤백이 기본 정책이다
// or
//@SpringBootTest // 기본적으로 자동 커밋으로 설정되어 있다, 스프링의 모든 설정을 가져오고 빈 객체도 전체를 스캔함
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // app설정인 mariaDB를 사용하도록
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext //EntityManager를 빈으로 주입할 때 사용하는 어노테이션
    EntityManager entityManager;

    @Test
    void saveTest(){
        //given
        Product product = Product.builder()
                .name("펜2")
                .price(100)
                .stock(99).build();
        
        //when
        Product saveProduct = productRepository.save(product);

        //then
        Assertions.assertEquals(product.getName(), saveProduct.getName());
    }

    @Test
   // @Rollback(value = false)    // 롤백 하고 싶지 않다면
    void selectTest(){
        //given
        Product product = Product.builder()
                .name("펜3")
                .price(100)
                .stock(99).build();
        Product saveProduct = productRepository.saveAndFlush(product);
        
        //when
        Product foundProduct = productRepository.findById(saveProduct.getNumber()).get();

        //then
        Assertions.assertEquals(product.getName(), foundProduct.getName());
    }

    @Test
    void queryDslTest(){
        // JPAQuery 객체 활용
        JPAQuery<Product> query = new JPAQuery<>(entityManager);

        QProduct qProduct = QProduct.product;

        List<Product> listProduct = query.from(qProduct)
                .where(qProduct.name.eq("펜2"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : listProduct) {
            System.out.println(product.getName());
            System.out.println(product.getNumber());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }
    }

    @Test
    public void queryDSLTest2(){

        // JPAQueryFactory 활용
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QProduct qProduct = QProduct.product;

//        List<Product> listProduct = jpaQueryFactory.selectFrom(qProduct)
//                .where(qProduct.name.contains("펜"))
//                .orderBy(qProduct.price.asc())
//                .fetch();

        List<Tuple> result = jpaQueryFactory.select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.contains("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Tuple tuple : result) {
            System.out.println(tuple.get(qProduct.name));
            System.out.println(tuple.get(qProduct.price));
        }
    }
    

}
