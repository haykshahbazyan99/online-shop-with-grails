package online.shop

import grails.gorm.transactions.Transactional

@Transactional
class ProductService {

    WarehouseService warehouseService
    StoreService storeService


    Product get(Long id) {
        Product.findById(id)
    }

    List<Product> list(Map params) {
        Product.list(params)
    }

    Long count() {
        Product.count()
    }

    void save(Product product) {
        product.save(flush: true)
    }

    void delete(Long id) {
        Product product = Product.findById(id)
        if (product) {
            product.delete(flush: true)
        }
    }

    List<Product> getExpiredProducts() {
        return Product.findAllByDeadlineLessThan(new Date())
    }

//    def returnExpiredProductsToWarehouse() {
//        def expiredProducts = getExpiredProducts()
//        expiredProducts.each { product ->
//            // Find all StoreProducts with the expired product
//            StoreProduct.findAllByProduct(product).each { storeProduct ->
//                def store = storeProduct.store
//                def warehouse = Warehouse.findByCode(storeProduct.product.code) // Assuming warehouse code matches product code
//
//                if (warehouse) {
//                    // Update the warehouse inventory
//                    def warehouseProduct = WarehouseProduct.findByWarehouseAndProduct(warehouse, product)
//                    if (warehouseProduct) {
//                        warehouseProduct.amount += storeProduct.amount
//                    } else {
//                        new WarehouseProduct(warehouse: warehouse, product: product, amount: storeProduct.amount).save(flush: true)
//                    }
//
//                    // Remove the product from the store's inventory
//                    store.removeFromProducts(storeProduct)
//                    storeProduct.delete(flush: true)
//                    store.save(flush: true)
//                }
//            }
//        }
//    }

}
