package eddie.payment.authsdk;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

class Repo {
	Repo() {
		System.out.println("Repo created " + this);
	}
	String findGreeting() {
		return "Hello from Repo";
	}
}

class Service {
	private final Repo repo;
	
	Service(Repo repo) {
		this.repo = repo;
		System.out.println("Service created: " + this);
	}
	
	String greet() {
		return repo.findGreeting() + " -> used by Service";
	}
}

class EagerBean {
	EagerBean() {  System.out.println("Eager Bean is created"); }
}

class LazyBean {
	LazyBean() { System.out.println("Lazy Bean is created"); }
}

@Configuration
class AppConfig {

	@Bean
	Repo repo() { return new Repo(); }

	@Bean
	Service service(Repo repo) { return new Service(repo); }
	
	@Bean
	EagerBean eagerBean() { return new EagerBean(); }

	@Bean
	@Lazy
	LazyBean lazyBean() { return new LazyBean(); }
}

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println("------ Context Created ------");

		Service svc1 = ctx.getBean(Service.class);
		Service svc2 = ctx.getBean(Service.class);

		System.out.println("greet(): " + svc1.greet());
		System.out.println("Same service instance? " + (svc1 == svc2));

		System.out.println("----- Request EagerBean ------");
		ctx.getBean(EagerBean.class);

		System.out.println("------ Request LazyBean ------");
		ctx.getBean(LazyBean.class);

		ctx.close();
	}
}
