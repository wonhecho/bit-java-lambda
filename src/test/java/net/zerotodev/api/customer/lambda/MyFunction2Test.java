package net.zerotodev.api.customer.lambda;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class MyFunction2Test {

    @Test
    void main() {
        MyFunction2 main = (int a,int b) -> {return a+b;};
        assertThat(main.main(1,2),is(equalTo(3)));
    }

}