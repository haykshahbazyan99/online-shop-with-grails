package online.shop

import grails.gorm.annotation.Entity

@Entity
class Product {

    String code
    String name
    BigDecimal price
    Date productionDate
    Date deadline

    static constraints = {
        code unique: true, blank: false
        name blank: false
        price min: 0.0
        productionDate nullable: false
        deadline nullable: true
    }

    boolean isExpired() {
        return deadline ? deadline.before(new Date()) : false
    }

}
