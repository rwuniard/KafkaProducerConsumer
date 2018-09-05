import org.junit.Assert;


class KProducerTest {

    private KProducer kprod;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        kprod = new KProducer("test2");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void produce() {
        Assert.assertNotNull(kprod);
        kprod.produce();


    }
}