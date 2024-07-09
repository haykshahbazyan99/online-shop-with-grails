package online.shop

import grails.gorm.annotation.Entity

@Entity
class Warehouse {

    String code
    String name
    static hasMany = [products: WarehouseProduct]

    static constraints = {
        code unique: true, blank: false
        name blank: false
    }
}

@Entity
class WarehouseProduct {

    Warehouse warehouse
    Product product
    int amount

    static belongsTo = [warehouse: Warehouse, product: Product]

    static constraints = {
        amount min: 0
    }
}
