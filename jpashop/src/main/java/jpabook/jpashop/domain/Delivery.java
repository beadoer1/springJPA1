package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    private Address address;

    @Enumerated(EnumType.STRING) // ORDINARY(숫자로 enum 지정) 가 기본. 중간에 값 추가되면 망한다. STRING 지정해줄 것.
    private DeliveryStatus status; // READY, COMP

}
