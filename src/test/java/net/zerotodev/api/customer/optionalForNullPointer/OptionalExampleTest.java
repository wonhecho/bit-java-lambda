package net.zerotodev.api.customer.optionalForNullPointer;

import lombok.Data;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import java.util.Optional;
import java.util.Date;

public class OptionalExampleTest {
    @Data class Address{
        private final String street;
        private final String city;
        private final String zipcode;
    }
    @Data class Member{
        private final Long id;
        private final String name;
        private final Address address;
    }
    @Data class Order{
        private final Long id;
        private final Date date;
        private final Member member;
    }
    /*주문을 한 회원이 살고 있는 도시를 반환한다*/
    public String getCityOfMemberFromOrder(Order order){
        return Optional.ofNullable(order)
                .map(Order::getMember)
                .map(Member::getAddress)
                .map(Address::getCity)
                .orElse("Seoul");
    }
    /*주어진 시간(분) 내에 생성된 주문을 한 경우에만 해당 회원 정보를 반환한다*/
    public Optional<Member> getMemberIfOrderWithin(Order order, int min){
        return Optional.ofNullable(order)
                .filter(o -> o.getDate().getTime() > System.currentTimeMillis() - min * 1000)
                .map(Order::getMember);
    }
    @Test
    void optionalMethodTest(){
        Integer a = 10;
        Optional<Integer> optional = Optional.of(a);
        Integer integerValue = optional.get();
        assertThat(integerValue, is(equalTo(10)));
        Optional<Integer> emptyOptioal = Optional.empty();
        Integer val = emptyOptioal.isPresent() ? emptyOptioal.get() : 0;
        assertThat(val, is(equalTo(0)));
        Integer orElse = emptyOptioal.orElse(0);
        assertThat(orElse, is(equalTo(0)));
        Integer orElseGet = emptyOptioal.orElseGet(() -> 0);
        assertThat(orElseGet, is(equalTo(0)));
    }
}
