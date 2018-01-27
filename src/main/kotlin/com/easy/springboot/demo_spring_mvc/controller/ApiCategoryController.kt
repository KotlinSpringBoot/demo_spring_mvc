package com.easy.springboot.demo_spring_mvc.controller

import com.easy.springboot.demo_spring_mvc.dao.CategoryDao
import com.easy.springboot.demo_spring_mvc.entity.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

/**
 * Created by Kor on 2017-12-24 14:04:20.
 */
@RestController
@RequestMapping("/api/category")
class ApiCategoryController {

    @Autowired lateinit var categoryDao: CategoryDao

    @GetMapping(value = ["/"])
    fun category(request: HttpServletRequest): List<Category> {
        return categoryDao.findAll()
    }

    @GetMapping(value = ["/{id}"])
    fun getOne(@PathVariable("id") id: Long): Category {
        return categoryDao.getOne(id)
    }


    @GetMapping(value = ["/search"])
    fun page(
            @RequestParam(value = "pageNo", defaultValue = "0") pageNo: Int,// Spring Data JPA 的分页默认第一页是： pageNo = 0
            @RequestParam(value = "pageSize", defaultValue = "10") pageSize: Int,
            @RequestParam(value = "searchText", defaultValue = "") searchText: String,
            @RequestParam(value = "type", defaultValue = "1") type: Int
    ): Page<Category> {
        return categoryDao.page(
                searchText,
                type,
                PageRequest.of(pageNo, pageSize))
    }

}
