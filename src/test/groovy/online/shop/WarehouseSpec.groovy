package online.shop

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class WarehouseSpec extends Specification implements DomainUnitTest<Warehouse> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        given:
        Warehouse warehouse = new Warehouse(code: "W001", name: "Warehouse 1")

        when:
        warehouse.save(flush: true)

        then:
        Warehouse.findByCode("W001") != null
        Warehouse.findByCode("W001").name == "Warehouse 1"
    }
}
