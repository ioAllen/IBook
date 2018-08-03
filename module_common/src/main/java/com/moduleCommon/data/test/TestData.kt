package com.moduleCommon.data.test

import com.moduleCommon.data.bean.CommonComment
import java.util.*

/**
 * author：WangLei
 * date:2018/8/3.
 * QQ:619321796
 */
object TestData {
    fun loadCommentData(): List<CommonComment> {
        val list = ArrayList<CommonComment>()
        list.add(CommonComment("貂蝉","http://img2.imgtn.bdimg.com/it/u=422194175,2057763909&fm=27&gp=0.jpg",Date().time,10,30,"第一次写书评，写的不好请见谅"))
        list.add(CommonComment("芈月","http://img4.imgtn.bdimg.com/it/u=2052043224,1194365110&fm=27&gp=0.jpg",Date().time,6,30,"岁月静好"))
        list.add(CommonComment("红颜","http://img2.imgtn.bdimg.com/it/u=3487099875,4182892485&fm=27&gp=0.jpg",Date().time,7,30,"总体来说这本书还是不错的"))
        list.add(CommonComment("霸王","http://img4.imgtn.bdimg.com/it/u=3877886750,2053033068&fm=27&gp=0.jpg",Date().time,8,30,"对于此书的感概"))
        list.add(CommonComment("流言","http://img2.imgtn.bdimg.com/it/u=2570368962,3531989844&fm=27&gp=0.jpg",Date().time,5,30,"作者很给力，加油！"))
        list.add(CommonComment("天下第一","http://img1.imgtn.bdimg.com/it/u=3744905557,21188004&fm=27&gp=0.jpg",Date().time,3,30,"书很好，但是不适合我。"))
        list.add(CommonComment("至尊","http://img1.imgtn.bdimg.com/it/u=677283392,596450933&fm=27&gp=0.jpg",Date().time,7,30,"还可以"))
        list.add(CommonComment("二哈","http://img1.imgtn.bdimg.com/it/u=3153322237,2645908702&fm=27&gp=0.jpg",Date().time,1,30,"不死的情话"))
        list.add(CommonComment("丑丑的","http://img1.imgtn.bdimg.com/it/u=3246547180,553247337&fm=27&gp=0.jpg",Date().time,2,30,"那天下第一血"))
        list.add(CommonComment("大王","http://img1.imgtn.bdimg.com/it/u=185239249,3442367358&fm=27&gp=0.jpg",Date().time,9,30,"加快更新速度啊啊啊！"))
        return list
    }
}