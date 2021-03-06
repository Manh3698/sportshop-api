package com.manh.doantotnghiep.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.ProductEntity;
import com.manh.doantotnghiep.config.LogExecutionTime;
import com.manh.doantotnghiep.service.ProductService;
import com.manh.doantotnghiep.utils.Constants;

/**
 * The Class ProductController.
 */
@RestController
@LogExecutionTime
@RequestMapping(value = "/api/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    /** The product service. */
    @Autowired
    private ProductService productService;
    

    /**
     * Gets the all products.
     *
     * @return the all products
     * @throws Exception the exception
     */
//    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getAllProducts() throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productService.getAll();
        } catch (Exception e) {
            log.info(e.getMessage());
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Gets the product by id.
     *
     * @param id the id
     * @return the product by id
     * @throws Exception the exception
     */
    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getProductById(@PathVariable Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productService.getById(id);
        } catch (Exception e) {
            log.info(e.getMessage());
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Gets the products by cate id.
     *
     * @param cateId the cate id
     * @return the products by cate id
     * @throws Exception the exception
     */
    @RequestMapping(value = "/getByCateId/{cateId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getProductsByCateId(@PathVariable Integer cateId) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productService.getProductsByCateId(cateId);
        } catch (Exception e) {
            log.info(e.getMessage());
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Delete id.
     *
     * @param id the id
     * @return the response entity
     * @throws Exception the exception
     */
//    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> deleteId(@PathVariable Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productService.deleteById(id);
        } catch (Exception e) {
            log.info(e.getMessage());
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Adds the product.
     *
     * @param json the json
     * @return the response entity
     * @throws Exception the exception
     */
//    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addProduct(@RequestParam("files") MultipartFile[] files, @RequestParam("json") String json) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productService.addProduct(json, files);
        } catch (Exception e) {
            log.info(e.getMessage());
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.CREATED);
    }

    /**
     * Update.
     *
     * @param json the json
     * @return the response entity
     * @throws Exception the exception
     */
//    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> updateProduct(@RequestParam("files") MultipartFile[] files, @RequestParam("json") String json) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productService.updateProduct(json, files);
        } catch (Exception e) {
            log.info(e.getMessage());
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getHot", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getHotProduct() throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productService.getHotProduct();
        } catch (Exception e) {
            log.info(e.getMessage());
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}
