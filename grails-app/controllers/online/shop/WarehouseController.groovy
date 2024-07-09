package online.shop

import grails.gorm.transactions.Transactional

class WarehouseController {

    def warehouseService

    def index() {
        def warehouses = Warehouse.list()
        render view: 'index', model: [warehouses: warehouses]
    }

    def show(Long id) {
        def warehouse = Warehouse.get(id)
        if (!warehouse) {
            render(status: 404, text: "Warehouse not found")
            return
        }
        render view: 'show', model: [warehouse: warehouse]
    }

    def create() {
        def warehouse = new Warehouse()
        render view: 'create', model: [warehouse: warehouse]
    }


    def save() {
        def warehouse = new Warehouse(params)
        if (warehouseService.saveWarehouse(warehouse)) {
            flash.message = "Warehouse saved successfully"
            redirect action: "show", id: warehouse.id
        } else {
            flash.message = "Failed to save warehouse"
            render view: "create", model: [warehouse: warehouse]
        }
    }

    def edit(Long id) {
        def warehouse = Warehouse.get(id)
        if (!warehouse) {
            render(status: 404, text: "Warehouse not found")
            return
        }
        render view: 'edit', model: [warehouse: warehouse]
    }

    def update() {
        def warehouse = Warehouse.get(params.id)
        if (warehouse) {
            warehouse.properties = params
            if (warehouse.save(flush: true)) {
                redirect(action: 'show', id: warehouse.id)
            } else {
                render view: 'edit', model: [warehouse: warehouse, errors: warehouse.errors]
            }
        } else {
            render(status: 404, text: "Warehouse not found")
        }
    }

    def delete(Long id) {
        def result = warehouseService.deleteWarehouse(id)
        if (!result.success) {
            render(status: 404, text: result.message ?: "Warehouse not found")
            return
        }

        flash.message = "Warehouse deleted successfully"
        redirect(action: 'index')
    }

    @Transactional
    def addProduct(Long warehouseId, Long productId, int amount) {
        def warehouse = Warehouse.get(warehouseId)
        def product = Product.get(productId)
        if (!warehouse || !product) {
            render(status: 404, text: "Warehouse or Product not found")
            return
        }
        def warehouseProduct = WarehouseProduct.findByWarehouseAndProduct(warehouse, product)
        if (warehouseProduct) {
            warehouseProduct.amount += amount
        } else {
            new WarehouseProduct(warehouse: warehouse, product: product, amount: amount).save()
        }
        render "Product added to warehouse successfully"
    }

    @Transactional
    def removeProduct(Long warehouseId, Long productId) {
        def warehouse = Warehouse.get(warehouseId)
        def product = Product.get(productId)
        if (!warehouse || !product) {
            render(status: 404, text: "Warehouse or Product not found")
            return
        }
        def warehouseProduct = WarehouseProduct.findByWarehouseAndProduct(warehouse, product)
        if (warehouseProduct) {
            warehouseProduct.delete()
            render "Product removed from warehouse successfully"
        } else {
            render(status: 404, text: "Product not found in warehouse")
        }
    }

    @Transactional
    def deliverProduct(Long warehouseId, Long productId, Long storeId, int amount) {
        def warehouse = Warehouse.get(warehouseId)
        def product = Product.get(productId)
        def store = Store.get(storeId)
        if (!warehouse || !product || !store) {
            render(status: 404, text: "Warehouse, Product, or Store not found")
            return
        }
        def warehouseProduct = WarehouseProduct.findByWarehouseAndProduct(warehouse, product)
        if (!warehouseProduct || warehouseProduct.amount < amount) {
            render(status: 400, text: "Insufficient product amount in warehouse")
            return
        }
        warehouseProduct.amount -= amount
        if (warehouseProduct.amount == 0) {
            warehouseProduct.delete()
        }
        def storeProduct = StoreProduct.findByStoreAndProduct(store, product)
        if (storeProduct) {
            storeProduct.amount += amount
        } else {
            new StoreProduct(store: store, product: product, amount: amount).save()
        }
        render "Product delivered to store successfully"
    }
}
