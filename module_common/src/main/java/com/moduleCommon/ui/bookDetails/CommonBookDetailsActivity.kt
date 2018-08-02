package com.moduleCommon.ui.bookDetails

import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.common.core.RouterHub
import com.common.utils.DelegateAdapterUtil
import com.common.utils.StatusBarUtil
import com.moduleCommon.R
import com.moduleCommon.ui.base.CommonBaseActivity
import kotlinx.android.synthetic.main.common_book_details_activity.*

/**
 * author：WangLei
 * date:2018/8/2.
 * QQ:619321796
 * 书籍详情
 */
@Route(path = RouterHub.COMMON_BOOK_DETAILS_ACTIVITY)
class CommonBookDetailsActivity : CommonBaseActivity() {

    @Autowired
    @JvmField
    var bookName: String = ""

    private lateinit var mDelegateAdapter: DelegateAdapter

    private lateinit var commonBookDetailsItemInfoTop: CommonBookDetailsItemInfoTop
    private lateinit var commonBookDetailsItemInfoMenus: CommonBookDetailsItemInfoMenus
    private lateinit var commonBookDetailsItemInfo: CommonBookDetailsItemInfo
    private lateinit var commonBookDetailsItemInfoComment: CommonBookDetailsItemInfoComment

    override fun attachLayoutRes(): Int {
        return R.layout.common_book_details_activity
    }

    override fun initData() {
        StatusBarUtil.setTransparentForWindow(this)
        mTitleBar.setTitle(bookName)
        mTitleBar.showViewBreak(true)
        mTitleBar.alpha = 0f
        mTitleBar.getBackgroundImageView().alpha = 0f
        actionBarUtils.addTitleBarStatusBarHeight(mTitleBar)

        commonBookDetailsItemInfoTop = CommonBookDetailsItemInfoTop(this)
        commonBookDetailsItemInfoMenus = CommonBookDetailsItemInfoMenus(this)
        commonBookDetailsItemInfo = CommonBookDetailsItemInfo(this)
        commonBookDetailsItemInfoComment = CommonBookDetailsItemInfoComment(this)
        commonBookDetailsItemInfoTop.setTitleBar(mTitleBar)

        val layoutManager = VirtualLayoutManager(this)
        main_list.layoutManager = layoutManager

        val viewPool = RecyclerView.RecycledViewPool()
        main_list.recycledViewPool = viewPool
        viewPool.setMaxRecycledViews(0, 10)
        mDelegateAdapter = DelegateAdapter(layoutManager)
        main_list.adapter = mDelegateAdapter

        val adapters = DelegateAdapterUtil.getAdapterList()

        adapters.add(DelegateAdapter.simpleAdapter(commonBookDetailsItemInfoTop))
        adapters.add(DelegateAdapter.simpleAdapter(commonBookDetailsItemInfoMenus))
        adapters.add(DelegateAdapter.simpleAdapter(commonBookDetailsItemInfo))
        adapters.add(DelegateAdapter.simpleAdapter(commonBookDetailsItemInfoComment))

        val test = TextView(this)
        test.text = "葛羽修行至今，利用各种方法提升自己的修为，尤其是最近用成精尸炼化的尸丹，还有那洋楼建筑之中的无数鬼物炼化了不少鬼丹，修为已经直逼真人之境，一旦修为达到了某种境界，葛羽所修习的各种术法的威力也能够达到一种空前的提升。\n" +
                "\n" +
                "    比如这茅山七星剑，便有七个剑诀，葛羽下山之时，便已经掌握了茅山七星剑的两个剑诀，分别是七剑式和火离七剑，当自己的修为直逼真人之境的时候，葛羽已经可以修习茅山七星剑的第三个剑诀，叫做冰封七剑，这茅山七星剑的剑诀，每往上面修行一个层次，威力就强大数分，而葛羽的目前的修为，也仅仅只能掌握住第三个剑式，而且是堪堪能够施展出来。\n" +
                "\n" +
                "    七剑式就是让那茅山七星剑上挂着的七把小剑从主剑之上脱离出来，环绕在葛羽的周围，而那七把小剑会借助天地五行之力，变的跟主剑一般大小，这个剑诀一施展出来，可以给敌人以强大的震慑力和杀伤力。\n" +
                "\n" +
                "    而火离七剑，是在七剑式的基础之上，让那七把小剑周身布满火焰，那火焰并不是普通的凡火，而是离火之精，不光是能够对付敌人，即便是僵尸和鬼物，一旦沾染到那离火七剑上的火焰，也同样会受到重创，尤其是那些鬼物，被能够燃烧神魂的离火点燃，很快便会灰飞烟灭，魂飞魄散。\n" +
                "\n" +
                "    而冰封七剑，也同样是借助天地五行之力，吸取地煞之中的极寒极阴之力，汇聚于茅山七星剑之上，能够让方圆几十米的距离之内的温度瞬间下降几十度，如果修为再高一些的话，可以辐射到方圆几百米，葛羽听师父说，数百年前，茅山有一位大拿，修为绝高，使用这冰封七剑的时候，可以让方圆十里之内瞬间变成一片酷寒之地，石头都能冻住。\n" +
                "\n" +
                "    但是以葛羽现在的修为，还做不到那一点，但是这威力仍旧不可小觑。\n" +
                "\n" +
                "    其实，原本茅山七星剑是有九个剑诀的，数百年前，茅山七星剑也不叫七星剑，而是叫茅山九星剑，便是因为几百年前，茅山的一位师祖对付一个极为强悍的大魔头的时候，被其损坏了剑身上挂着的两把小剑，这九星剑就变成了七星剑，到现在为止，那剑身之上还空着两个孔洞。\n" +
                "\n" +
                "    话题有些扯远了，且说葛羽施展出了这一招冰封十里之后，四周的温度顿时变的一片酷寒，就连周身环绕的那些黑雾也被这阴寒之力为之冻结。\n" +
                "\n" +
                "    而那七把小剑同时变大，携带者阴寒之力朝着那些毒虫爬来的方向陡然间迸射而去。\n" +
                "\n" +
                "    凡是那七把小剑飞过的地方，地面之上顿时就凝结出了寒冰，原本悉悉索索的动静为之一顿，那些不断爬来的毒虫很快被这阴寒之力给凝固在了原地，冻成了一片片的冰坨子。\n" +
                "\n" +
                "    葛羽深吸了一口气，感觉这空气都是阴冷的，吐出来的气息都化作了霜，哗啦啦的掉在了地上。\n" +
                "\n" +
                "    真是冷啊，葛羽不由得也打了一个寒颤，没想到这冰封十里的剑招竟然这么强悍。"

        adapters.add(DelegateAdapter.simpleAdapter(test))

        mDelegateAdapter.setAdapters(adapters)

        main_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val topHeight = resources.getDimension(R.dimen.x600)
                val offset = main_list.computeVerticalScrollOffset()
                if (offset >= topHeight) {
                    mTitleBar.alpha = 1f;
                    mTitleBar.getBackgroundImageView().alpha = 1f
                } else {
                    val alpha = offset / topHeight
                    mTitleBar.alpha = alpha
                    mTitleBar.getBackgroundImageView().alpha = alpha
                }
            }
        })
    }

    override fun noStatusBar(): Boolean {
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        commonBookDetailsItemInfoTop.onDestroy()
    }
}