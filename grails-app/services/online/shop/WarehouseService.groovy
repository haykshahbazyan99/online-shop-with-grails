package online.shop

import grails.gorm.transactions.Transactional

@Transactional
class WarehouseService {

    def get(Long id) {
        Warehouse.findById(id)
    }

    def list() {
        Warehouse.list()
    }

    boolean saveWarehouse(Warehouse warehouse) {
        warehouse.save(flush: true)
    }
    def save(Warehouse warehouse) {
        warehouse.save(flush: true)
    }

    def deleteWarehouse(Long id) {
        def warehouse = Warehouse.get(id)
        if (!warehouse) {
            return [success: false, message: "Warehouse not found"]
        }

        try {
            warehouse.delete(flush: true)
            return [success: true]
        } catch (Exception e) {
            return [success: false, message: e.message]
        }
    }

    def addToWarehouse(Warehouse warehouse, Product product, int amount) {
        WarehouseProduct warehouseProduct = WarehouseProduct.findByWarehouseAndProduct(warehouse, product)
        if (warehouseProduct) {
            warehouseProduct.amount += amount
        } else {
            new WarehouseProduct(warehouse: warehouse, product: product, amount: amount).save(flush: true)
        }
    }

    def removeFromWarehouse(Warehouse warehouse, Product product, int amount) {
        WarehouseProduct warehouseProduct = WarehouseProduct.findByWarehouseAndProduct(warehouse, product)
        if (warehouseProduct) {
            warehouseProduct.amount -= amount
            if (warehouseProduct.amount <= 0) {
                warehouseProduct.delete(flush: true)
            } else {
                warehouseProduct.save(flush: true)
            }
        }
    }

    def deliverToStore(Warehouse warehouse, Store store, Product product, int amount) {
        removeFromWarehouse(warehouse, product, amount)
        store.addToStore(product, amount)
    }
}
