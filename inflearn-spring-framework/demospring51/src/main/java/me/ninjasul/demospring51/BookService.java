package me.ninjasul.demospring51;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class BookService implements InitializingBean {

    @Autowired(required = false)
    BookRepository bookRepository;

    // 같은 타입의 빈이 여러 개 등록되어 있는 경우에는 @Primary 어노테이션을 붙여서 우선 적용할 빈을 선정함.
    // 혹은 @Qualifier 어노테이션에서 등록할 빈의 이름을 명시함.
    /*
    @Autowired @Qualifier("myBookRepository")
    BookRepositoryInterface bookRepositoryInterface;
    */

    @Autowired
    List<BookRepositoryInterface> bookRepositoryInterfaces;

    // BookRepository 클래스가 빈으로 등록되지 않은 상태에서는 생성자에 @Autowired 어노테이션을 붙여도 오류가 발생함.
    /*
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    */

    // 생성자는 BookService 인스턴스 생성 시에 BookRepository 빈을 주입받지 못했으므로 실패가 당연하다는 것을 직관적으로
    // 이해할 수 있지만 setter 역시도 실패함. 그러나 required=false 로 설정 해 주면 오류를 막을 수 있음.
    /*
    @Autowired(required = false)
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    */

    /*
    public void printBookRepositoryInterface() {
        System.out.println(bookRepositoryInterface.getClass());
    }
    */

    public void printBookRepositoryInterfaces() {
        System.out.println("printBookRepositoryInterfaces");
        bookRepositoryInterfaces.forEach(System.out::println);
    }

    @PostConstruct
    public void setUp() {
        System.out.println("PostConstruct");
        bookRepositoryInterfaces.forEach(System.out::println);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
        bookRepositoryInterfaces.forEach(System.out::println);
    }
}
