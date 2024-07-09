package online.shop

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ProductSpec extends Specification implements DomainUnitTest<Product> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        given:
        Product product = new Product(code: "P001", name: "Product 1", price: 50.0, productionDate: new Date())

        when:
        product.save(flush: true)

        then:
        Product.findByCode("P001") != null
        Product.findByCode("P001").name == "Product 1"
        Product.findByCode("P001").price == 50.0
    }
}
