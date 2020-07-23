package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private PublisherRepository publisherRepository;
    
    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        final Author julesVerne = new Author("Jules", "Vernes");
        final Book voyage = new Book("Voyage au centre de la Terre", "4994369043");
        final Book tour = new Book("Le Tour du Monde en 80 Jours", "4994368443");
        
        julesVerne.getBooks().add(voyage);
        julesVerne.getBooks().add(tour);
        voyage.getAuthors().add(julesVerne);
        tour.getAuthors().add(julesVerne);
        
        this.authorRepository.save(julesVerne);
        this.bookRepository.save(voyage);
        this.bookRepository.save(tour);
        
        System.out.println("Books count = " + this.bookRepository.count());
        
        final Publisher oreilly = new Publisher("Freedom Ave 55", "87711", "Garhill", "Insburrough");
        
        publisherRepository.save(oreilly);
        
        System.out.println("Publishers count = " + this.publisherRepository.count());
    }

}
