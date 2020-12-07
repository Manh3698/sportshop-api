package com.manh.doantotnghiep.controller;

import com.manh.doantotnghiep.bean.ResultBean;
import com.manh.doantotnghiep.bean.entity.ContactEntity;
import com.manh.doantotnghiep.bean.entity.FeedbackEntity;
import com.manh.doantotnghiep.config.LogExecutionTime;
import com.manh.doantotnghiep.service.ContactService;
import com.manh.doantotnghiep.service.FeedbackService;
import com.manh.doantotnghiep.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@LogExecutionTime
@RequestMapping("api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getAllcontacts() throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = contactService.getAll();
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getContactById(@RequestParam Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = contactService.getById(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addContact(@RequestBody String json) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = contactService.addContact(json);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

//    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
//    public ResponseEntity<ResultBean> updateFeedback(@RequestParam FeedbackEntity feedback) throws Exception {
//        ResultBean resultBean = null;
//        try {
//            resultBean = feedbackService.updateFeedback(feedback);
//        } catch (Exception e) {
//            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
//            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
//    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> deleteContact(@PathVariable Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = contactService.deleteById(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}