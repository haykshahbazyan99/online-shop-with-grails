package online.shop

import grails.gorm.transactions.Transactional

@Transactional
class StoreController {

    static allowedMethods = [index: 'GET', show: 'GET', create: 'GET', edit: ['GET', 'POST'], delete: 'POST']
    StoreService storeService


    @Transactional(readOnly = true)
    def index() {
        def storeList = Store.list()
        [storeList: storeList]
    }

    def show(Long id) {
        def store = storeService.getStoreById(id)
        if (!store) {
            flash.error = "Store not found."
            redirect(action: 'index')
            return
        }
        [store: store]
    }

    def create() {
        [store: new Store()]
    }

    @Transactional
    def save(Store store) {
        def selectedWarehouseId = params['warehouse.id']
        if (selectedWarehouseId) {
            def warehouse = Warehouse.get(selectedWarehouseId as Long)
            store.warehouse = warehouse
        }
        if (store.save()) {
            redirect(action: 'index')
        } else {
            log.error("Validation errors: ${store.errors}")
            render(view: 'create', model: [store: store])
        }
    }

    def edit(Long id) {
        def store = storeService.getStoreById(id)
        if (!store) {
            flash.error = "Store not found."
            redirect(action: 'index')
            return
        }
        [store: store]
    }

    def update(Store store) {
        if (storeService.saveOrUpdateStore(store)) {
            flash.message = "Store '${store.name}' updated."
            redirect(action: 'show', id: store.id)
        } else {
            render(view: 'edit', model: [store: store])
        }
    }

    def delete(Long id) {
        try {
            storeService.deleteStore(id)
            flash.message = "Store deleted."
        } catch (Exception ex) {
            flash.error = ex.message
        }
        redirect(action: 'index')
    }

    def returnProduct(Long storeId, Long productId, int quantity) {
        def store = Store.get(storeId)
        def product = Product.get(productId)
        try {
            storeService.returnProductToWarehouse(store, product, quantity)
            flash.message = "Returned ${quantity} ${product.name}(s) to warehouse."
        } catch (IllegalArgumentException ex) {
            flash.error = ex.message
        }
        redirect(action: 'show', id: storeId)
    }


    def sellProduct(Long storeId, Long productId, int quantity) {
        def store = Store.get(storeId)
        def product = Product.get(productId)
        try {
            storeService.sellProductFromStore(store, product, quantity)
            flash.message = "Sold ${quantity} ${product.name}(s) from ${store.name}."
        } catch (IllegalArgumentException ex) {
            flash.error = ex.message
        }
        redirect(action: 'show', id: storeId)
    }


    def returnExpiredProducts(Long storeId) {
        def store = Store.get(storeId)
        try {
            storeService.returnExpiredProducts(store)
            flash.message = "Expired products returned to warehouse."
        } catch (Exception ex) {
            flash.error = ex.message
        }
        redirect(action: 'show', id: storeId)
    }

}
