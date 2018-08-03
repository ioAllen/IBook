package com.common.data.test

import com.common.data.bean.BookNative

/**
 * author：WangLei
 * date:2018/7/31.
 * QQ:619321796
 */
object TestData {

    fun loadBookData(): ArrayList<BookNative> {
        val list = ArrayList<BookNative>()

        var book = BookNative()
        book.author = "辰东"
        book.bookName = "圣墟"
        book.coverPath = "https://static.kuaiyankanshu.net/public/cover/26/86/13/268613464bfa861a139219e53df5b8e2.jpg"
        book.bookDescription = "在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角…… "
        list.add(book)

        book = BookNative()
        book.author = "烟雨江南"
        book.bookName = "永夜君王"
        book.coverPath = "https://static.kuaiyankanshu.net/public/cover/80/e3/7e/80e37eebd8292793873fb830e435a3a2.jpg"
        book.bookDescription = "千夜自困苦中崛起，在背叛中坠落。自此一个人，一把枪，行在永夜与黎明之间，却走出一段传奇。若永夜注定是他的命运，那他也要成为主宰的王。 "
        list.add(book)

        book = BookNative()
        book.author = "忆流年"
        book.bookName = "萌妻出没，请注意！"
        book.coverPath = "https://qidian.qpic.cn/qdbimg/349573/c_5969520903659001/180"
        book.bookDescription = "醉酒的安小虞霸气地双手叉腰：“嗨，帅哥，本姑娘是来打劫的。此路是我开，此树是我栽，要想从此过，留下买路财！” \n" +
                "男人欺身而上，一个壁咚，让安小虞无路可逃。 \n" +
                "他覆唇在她耳边：“钱，没有。要不，以身相许如何？” "
        list.add(book)

        book = BookNative()
        book.author = "恍若晨曦"
        book.bookName = "韩先生，情谋已久"
        book.coverPath = "https://qidian.qpic.cn/qdbimg/349573/c_8766036503130303/180"
        book.bookDescription = "【绝宠爽文，双洁1v1】“收留我，让我做什么都行！” \n" +
                "前世她被继妹和渣男陷害入狱，出狱后留给她的只剩亲生母亲的墓碑。看着渣男贱女和亲爹后妈一家团圆，她一把大火与渣男和继妹同归于尽。 \n" +
                "再醒来，重新回到被陷害的那天，她果断跳窗爬到隔壁，抱紧隔壁男人的大长腿。 \n" +
                "却没想到，大长腿的主人竟是上一世那让她遥不可及的绝色男神。 \n" +
                "这一次，她一定擦亮眼睛，让那些欠了她的，统统都还回来！ \n" +
                "“韩少，另一条大腿也能给我抱一抱吗？” \n" +
                "“其实我还有一只你要不要？” \n" +
                "“……” \n" +
                "“韩少，来人自称是夫人的妹妹。” \n" +
                "“打出去。” \n" +
                "“韩少，听说夫人这部剧的男二号是她前男友，夫人要毁约。” \n" +
                "“撤资，不拍了。” \n" +
                "* \n" +
                "《七公子》系列之第四篇：韩卓厉篇~"
        list.add(book)

        book = BookNative()
        book.author = "我吃西红柿"
        book.bookName = "雪鹰领主"
        book.coverPath = "https://static.kuaiyankanshu.net/public/cover/87/7c/ea/877cea1382b36a700c199e13d76bda07.jpg"
        book.bookDescription = "在夏族的安阳行省，有一个很小很不起眼的领地，叫——雪鹰领！故事，就从这里开始！**继《莽荒纪》《吞噬星空》《九鼎记》《盘龙》《星辰变》《寸芒》《星峰传说》后，番茄的第八本小说！ "
        list.add(book)

        book = BookNative()
        book.author = "天蚕土豆"
        book.bookName = "元尊"
        book.coverPath = "https://www.zwdu.com/files/article/image/30/30241/30241s.jpg"
        book.bookDescription = "吾有一口玄黄气，可吞天地日月星。天蚕土豆最新鼎力大作，2017年度必看玄幻小说。 "
        list.add(book)

        book = BookNative()
        book.author = "天蚕土豆"
        book.bookName = "大主宰"
        book.coverPath = "https://static.kuaiyankanshu.net/public/cover/6a/5e/e8/6a5ee813041e5c9654155c09b198237d.jpg"
        book.bookDescription = "大千世界，位面交汇，万族林立，群雄荟萃，一位位来自下位面的天之至尊，在这无尽世界，演绎着令人向往的传奇，追求着那主宰之路。无尽火域，炎帝执掌，万火焚苍穹。武境之内，武祖之威，震慑乾坤。西天之殿，百战之皇，战威无可敌。北荒之丘，万墓之地，不死之主镇天地。......少年自北灵境而出，骑九幽冥雀，闯向了那精彩绝伦的纷纭世界，主宰之路，谁主沉浮？大千世界，万道争锋，吾为大主宰。"
        list.add(book)

        book = BookNative()
        book.author = "梦入神机"
        book.bookName = "点道为止"
        book.coverPath = "https://www.zwdu.com/files/article/image/16/16664/16664s.jpg"
        book.bookDescription = "功夫究竟是什么？ 花架子还是杀人技？ 三千年冷兵器战争和无数民间私斗酝酿出来的把式，究竟是不是骗局？ 国术流开创者，功夫小说第一人梦入神机，在本书中为您揭秘。 止戈为武，点到为止。 "
        list.add(book)

        return list
    }

    fun loadBannerData(): ArrayList<String> {
        val bannerList = ArrayList<String>()
        bannerList.add("http://img3.imgtn.bdimg.com/it/u=2469519129,2498536804&fm=27&gp=0.jpg")
        bannerList.add("http://img5.imgtn.bdimg.com/it/u=3149275935,1854108006&fm=27&gp=0.jpg")
        bannerList.add("http://img0.imgtn.bdimg.com/it/u=3151700851,890731343&fm=27&gp=0.jpg")
        return bannerList
    }

    fun loadTestData(): ArrayList<TestBean> {
        val list = ArrayList<TestBean>()
        list.add(TestBean("都市", "https://static.kuaiyankanshu.net/public/cover/80/e3/7e/80e37eebd8292793873fb830e435a3a2.jpg"))
        list.add(TestBean("玄幻", "https://static.kuaiyankanshu.net/public/cover/87/7c/ea/877cea1382b36a700c199e13d76bda07.jpg"))
        list.add(TestBean("仙侠", "https://read.fmx.cn/files/article/image/127608/127608s.jpg"))
        list.add(TestBean("武侠", "https://www.zwdu.com/files/article/image/16/16664/16664s.jpg"))
        list.add(TestBean("奇幻", "https://read.fmx.cn/files/article/image/127126/127126s.jpg"))
        list.add(TestBean("历史", "https://read.fmx.cn/files/article/image/125621/125621s.jpg"))
        list.add(TestBean("军事", "https://read.fmx.cn/files/article/image/126888/126888s.jpg"))
        list.add(TestBean("校园", "https://read.fmx.cn/files/article/image/127139/127139s.jpg"))
        list.add(TestBean("悬疑", "https://read.fmx.cn/files/article/image/110095/110095s.jpg"))
        list.add(TestBean("科幻", "https://read.fmx.cn/files/article/image/127107/127107s.jpg"))
        list.add(TestBean("游戏", "https://read.fmx.cn/files/article/image/127540/127540s.jpg"))
        list.add(TestBean("竞技", "https://read.fmx.cn/files/article/image/126780/126780s.jpg"))
        return list
    }
}