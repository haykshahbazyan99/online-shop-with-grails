package online.shop

class UrlMappings {

    static mappings = {
        // Product mappings
        "/product/index" {
            controller = "product"
            action = "index"
        }
        "/product/show/$id" {
            controller = "product"
            action = "show"
        }
        "/product/create" {
            controller = "product"
            action = "create"
        }
        "/product/save" {
            controller = "product"
            action = "save"
        }
        "/product/edit/$id" {
            controller = "product"
            action = "edit"
        }
        "/product/update" {
            controller = "product"
            action = "update"
        }
        "/product/delete/$id" {
            controller = "product"
            action = "delete"
        }
        "/product/checkExpiredProducts" {
            controller = "product"
            action = "checkExpiredProducts"
        }


        // Warehouse mappings
        "/warehouse/$action?/$id?(.${format})?" {
            controller = "warehouse"
        }

        "/product/$id?"(controller: "product") {
            action = [GET: "show", POST: "save", PUT: "update", DELETE: "delete"]
        }

        "/product"(controller: "product", action: "index")

        "/warehouse/$id?"(controller: "warehouse") {
            action = [GET: "show", POST: "save", PUT: "update", DELETE: "delete"]
        }

        "/warehouse/addProduct"(controller: "warehouse") {
            action = "addProduct"
            method = "POST"
        }

        "/warehouse/removeProduct"(controller: "warehouse") {
            action = "removeProduct"
            method = "POST"
        }

        "/warehouse/deliverProduct"(controller: "warehouse") {
            action = "deliverProduct"
            method = "POST"
        }

        "/$controller/$action?/$id?(.$format)?" {
            constraints {
            }
        }

        "/api/$controller/$action?/$id?(.$format)?"(parseRequest: true) {
            constraints {
            }
        }
        "/api/warehouse"(resources: 'warehouse') {
            "/addProduct"(controller: 'warehouse', action: 'addProduct')
            "/removeProduct"(controller: 'warehouse', action: 'removeProduct')
            "/deliverProduct"(controller: 'warehouse', action: 'deliverProduct')
        }
        "/warehouse"(controller: "warehouse", action: "index")


        // Store mappings
        "/store/$action?/$id?"(controller: "store")

        "/$controller/$action?/$id?" {
            constraints {
            }
        }

        "/warehouse/$action?/$id?" {
            controller = "warehouse"
        }

        "/"(controller: "store", action: "index")
        "500"(view: '/error')
        "404"(view: '/notFound')

        "/warehouses"(resources: "warehouse")
        "/stores"(resources: "store")


        "/api/products"(resources: 'product')
        "/api/stores"(resources: 'store')
        "/api/warehouses"(resources: 'warehouse')


        "/"(controller: 'home', action: 'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
