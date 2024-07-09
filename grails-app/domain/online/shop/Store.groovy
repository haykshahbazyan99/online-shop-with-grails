package online.shop

import grails.gorm.annotation.Entity

import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Store {

    String code
    String name
    String address

    @OneToMany(mappedBy = "store")
    List<StoreProduct> products = new ArrayList<StoreProduct>()

    @ManyToOne
    Warehouse warehouse

    static constraints = {
        code unique: true, blank: false
        name blank: false
        address nullable: true
    }

    static mapping = {
        products cascade: 'all-delete-orphan'
    }
}

@Entity
class StoreProduct implements Serializable {
    @ManyToOne
    Store store
    @ManyToOne
    Product product
    int amount

    static constraints = {
        amount min: 0
    }

    static mapping = {
        id composite: ['store', 'product']
        version false
    }

}
