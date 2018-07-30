package com.moduleBookshelf.data.test

import com.moduleBookshelf.data.bean.BookshelfBean

/**
 * author：WangLei
 * date:2018/7/30.
 * QQ:619321796
 */
object TestData {
    fun loadBookshelfData(): ArrayList<BookshelfBean> {
        val list = ArrayList<BookshelfBean>()
        list.add(BookshelfBean("特种教师", "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3053098540,3259949006&fm=58"))
        list.add(BookshelfBean("永夜君王", "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1437878960,2074520116&fm=58"))
        list.add(BookshelfBean("武道至尊", "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2872437631,1333487821&fm=58"))
        list.add(BookshelfBean("神控天下", "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3808850794,3472738932&fm=58"))
        list.add(BookshelfBean("火爆天王", "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2398216293,342802380&fm=58"))
        list.add(BookshelfBean("唐砖", "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3502713572,936455556&fm=58"))
        return list
    }
}