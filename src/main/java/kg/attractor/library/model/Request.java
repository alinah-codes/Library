package kg.attractor.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDate created;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @Column(name = "real_return_date")
    private LocalDate realReturnDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;
}
