package com.moduleBookMall.data.test

import com.moduleBookMall.data.bean.BookRecommendBean

/**
 * author：WangLei
 * date:2018/7/31.
 * QQ:619321796
 */
object TestData {

    fun loadBookshelfData(): ArrayList<BookRecommendBean> {
        val list = ArrayList<BookRecommendBean>()
        list.add(BookRecommendBean("圣墟", "https://static.kuaiyankanshu.net/public/cover/26/86/13/268613464bfa861a139219e53df5b8e2.jpg",
                "在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角…… ", "", "辰东"))

        list.add(BookRecommendBean("永夜君王", "https://static.kuaiyankanshu.net/public/cover/80/e3/7e/80e37eebd8292793873fb830e435a3a2.jpg",
                "千夜自困苦中崛起，在背叛中坠落。自此一个人，一把枪，行在永夜与黎明之间，却走出一段传奇。若永夜注定是他的命运，那他也要成为主宰的王。 ", "", "烟雨江南"))

        list.add(BookRecommendBean("雪鹰领主", "https://static.kuaiyankanshu.net/public/cover/87/7c/ea/877cea1382b36a700c199e13d76bda07.jpg",
                "在夏族的安阳行省，有一个很小很不起眼的领地，叫——雪鹰领！故事，就从这里开始！**继《莽荒纪》《吞噬星空》《九鼎记》《盘龙》《星辰变》《寸芒》《星峰传说》后，番茄的第八本小说！  ", "", "我吃西红柿"))

        list.add(BookRecommendBean("大主宰", "https://static.kuaiyankanshu.net/public/cover/6a/5e/e8/6a5ee813041e5c9654155c09b198237d.jpg",
                "大千世界，位面交汇，万族林立，群雄荟萃，一位位来自下位面的天之至尊，在这无尽世界，演绎着令人向往的传奇，追求着那主宰之路。无尽火域，炎帝执掌，万火焚苍穹。武境之内，武祖之威，震慑乾坤。西天之殿，百战之皇，战威无可敌。北荒之丘，万墓之地，不死之主镇天地。......少年自北灵境而出，骑九幽冥雀，闯向了那精彩绝伦的纷纭世界，主宰之路，谁主沉浮？大千世界，万道争锋，吾为大主宰。  ", "", "天蚕土豆"))
        return list
    }

    fun loadBannerData(): ArrayList<String>{
        val bannerList = ArrayList<String>()
        bannerList.add("http://img3.imgtn.bdimg.com/it/u=2469519129,2498536804&fm=27&gp=0.jpg")
        bannerList.add("http://img5.imgtn.bdimg.com/it/u=3149275935,1854108006&fm=27&gp=0.jpg")
        bannerList.add("http://img0.imgtn.bdimg.com/it/u=3151700851,890731343&fm=27&gp=0.jpg")
        return bannerList
    }

}