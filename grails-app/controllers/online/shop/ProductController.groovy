package online.shop

import grails.gorm.transactions.Transactional
import grails.rest.RestfulController
import grails.validation.ValidationException

import javax.servlet.http.HttpServletResponse


@Transactional
class ProductController extends RestfulController<Product> {

    ProductService productService

    static final int NOT_FOUND = HttpServletResponse.SC_NOT_FOUND
    static responseFormats = ['json', 'xml', 'html']

    ProductController() {
        super(Product)
    }

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", returnExpiredProducts: "POST"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def products = productService.list(params)
        def productCount = productService.count()

        render(view: 'index', model: [productList: products, productCount: productCount])
    }


    def checkExpiredProducts() {
        def expiredProducts = Product.findAllByDeadlineLessThan(new Date())
        respond expiredProducts
    }

    def show(Long id) {
        def product = Product.get(id)
        if (!product) {
            notFound()
            return
        }
        [productInstance: product]
    }

    def create() {
        render(view: 'create', model: [productInstance: new Product()])
    }

    @Transactional
    def save(Product product) {
        if (!productService.save(product)) {
            flash.message = "Product saved successfully"
            redirect(action: "show", id: product.id)
        } else {
            render(view: "create", model: [productInstance: product])
        }
    }


    def edit(Long id) {
        def productInstance = productService.get(id)
        if (productInstance) {
            render(view: 'edit', model: [productInstance: productInstance])
        } else {
            notFound()
        }
    }


    @Transactional
    def update(Long id, Product product) {
        println("Received product ID: ${product.id}")
        println("Received product details: ${params}")
        if (!product || !product.id) {
            notFound()
            return
        }
        try {
            productService.save(product)
        } catch (ValidationException e) {
            render(view: 'edit', model: [productInstance: product, errors: product.errors])
            return
        }
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'product.label', default: 'Product'), product.id])
                redirect action: 'show', id: product.id
            }
            '*' {
                render(view: 'show', model: [productInstance: product])
            }
        }
    }


    def delete(Long id) {
        if (id == null || productService.delete(id) == null) {
            notFound()
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'product.label', default: 'Product'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }


    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

}