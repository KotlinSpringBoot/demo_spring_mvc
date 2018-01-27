package com.easy.springboot.demo_spring_mvc.entity

import java.util.*
import javax.persistence.*


/**
 * Created by Kor on 2017-12-24 14:04:20.
 */
@Entity
@Table(indexes = [
    Index(columnList = "code", unique = false),
    Index(columnList = "name", unique = true),
    Index(columnList = "type", unique = false)
])
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1
    var gmtCreate = Date()
    var gmtModify = Date()
    var isDeleted = 0

    @Column(length = 200)
    var name = ""
    @Column(length = 2000)
    var detail = ""

    /**
     * 1 学科分类
     * 2 图书分类
     * 3 行业分类
     * 4 中国产业分页
     * 5 国家分类
     * 6 语言分类
     * 7 宗教分类
     * 8 组织机构分类
     * 9 音乐分类
     * 10 文学分类
     * 11 计算机编程语言分类
     * 12 动植物分类
     *
     */
    var type = 1
    // 编码
    @Column(length = 200)
    var code = "001"

}
