package com.moduleCommon.ui.bookDetails

import android.widget.ImageView
import android.widget.RatingBar
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.common.library.adapter.base.BaseQuickAdapter
import com.common.library.adapter.base.BaseViewHolder
import com.common.utils.IStringUtils
import com.moduleCommon.R
import com.moduleCommon.data.bean.CommonComment
import javax.inject.Inject

/**
 * author：WangLei
 * date:2018/8/3.
 * QQ:619321796
 */
class CommonBookCommentAdapter @Inject
constructor() : BaseQuickAdapter<CommonComment, BaseViewHolder>((R.layout.common_item_book_comment)) {
    override fun onCreateLayoutHelper(): LayoutHelper? {
        return LinearLayoutHelper()
    }

    override fun convert(helper: BaseViewHolder?, item: CommonComment?) {
        if (helper != null && item != null) {
            val avatarIv = helper.getView<ImageView>(R.id.itemCommentAvatarIv)
            IStringUtils.displayAvatarImage(mContext, item.userAvatar, avatarIv)
            helper.setText(R.id.itemCommentNameTv, item.userName)
            helper.setText(R.id.itemCommentContentTv, item.content)
            val rating = helper.getView<RatingBar>(R.id.itemCommentRatingTv)
            rating.rating = item.grade / 2f
        }
    }

}