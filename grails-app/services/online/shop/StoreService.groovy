package online.shop

import grails.gorm.transactions.Transactional

@Transactional
class StoreService {

    //doesn't work IDK why
    List<Store> getAllStores() {
        Store.list()
    }

    Store getStoreById(Long id) {
        Store.get(id)
    }

    Store saveOrUpdateStore(Store store) {
        try {
            if (store.validate()) {
                store.save(flush: true)
                return store
            } else {
                log.error("Validation errors occurred: ${store.errors}")
                return null
            }
        } catch (Exception e) {
            log.error("Error saving/updating store: ${e.message}")
            return null
        }
    }

    void deleteStore(Long id) {
        Store store = Store.get(id)
        if (store) {
            if (store.products.isEmpty()) {
                store.delete(flush: true)
            } else {
                throw new IllegalArgumentException("Store cannot be deleted as it contains products.")
            }
        } else {
            throw new IllegalArgumentException("Store not found.")
        }
    }

    void returnProductToWarehouse(Store store, Product product, int quantity) {
        StoreProduct storeProduct = StoreProduct.findByStoreAndProduct(store, product)
        if (storeProduct) {
            if (storeProduct.amount >= quantity) {
                WarehouseProduct warehouseProduct = WarehouseProduct.findByWarehouseAndProduct(store.warehouse, product)
                if (warehouseProduct) {
                    warehouseProduct.amount += quantity
                } else {
                    warehouseProduct = new WarehouseProduct(warehouse: store.warehouse, product: product, amount: quantity)
                }
                warehouseProduct.save(flush: true)
                storeProduct.amount -= quantity
                if (storeProduct.amount <= 0) {
                    storeProduct.delete(flush: true)
                } else {
                    storeProduct.save(flush: true)
                }
            } else {
                throw new IllegalArgumentException("Not enough quantity of ${product.name} in stock at ${store.name}.")
            }
        } else {
            throw new IllegalArgumentException("${product.name} is not available at ${store.name}.")
        }
    }

    void sellProductFromStore(Store store, Product product, int quantity) {
        StoreProduct storeProduct = StoreProduct.findByStoreAndProduct(store, product)
        if (storeProduct) {
            if (storeProduct.amount >= quantity) {
                storeProduct.amount -= quantity
                storeProduct.save(flush: true)
            } else {
                throw new IllegalArgumentException("Not enough amount of ${product.name} in stock at ${store.name}.")
            }
        } else {
            throw new IllegalArgumentException("${product.name} is not available at ${store.name}.")
        }
    }

    void returnExpiredProducts(Store store) {
        store.products.each { storeProduct ->
            if (storeProduct.product.isExpired()) {
                WarehouseProduct warehouseProduct = WarehouseProduct.findByWarehouseAndProduct(store.warehouse, storeProduct.product)
                if (warehouseProduct) {
                    warehouseProduct.amount += storeProduct.amount
                } else {
                    warehouseProduct = new WarehouseProduct(warehouse: store.warehouse, product: storeProduct.product, amount: storeProduct.amount)
                }
                warehouseProduct.save(flush: true)
                storeProduct.delete(flush: true)
            }
        }
    }
}
