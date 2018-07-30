package com.common.core

/**
 * author：WangLei
 * date:2018/7/28.
 * QQ:619321796
 */
interface RouterHub {

    companion object {
        /*******************************************组名**************************************************/
        /**
         * 宿主 App 组件
         */
        private const val APP = "/app"

        /**
         * 书架组件
         */
        private const val BOOKSHELF = "/bookshelf"

        /**
         * 书城组件
         */
        private const val BOOK_Mall = "/book_mall"

        /*****************************************宿主App分组***********************************************/
        /**
         * 启动页
         */
        const val APP_LAUNCH_ACTIVITY = "$APP/LaunchActivity"
        /**
         * 主界面
         */
        const val APP_MAIN_ACTIVITY = "$APP/MainActivity"


        /*****************************************书架分组**************************************************/

        /**
         * 书架fragment
         */
        const val BOOKSHELF_MAIN_FRAGMENT = "$BOOKSHELF/BookshelfFragment"

        /**
         * 读书界面
         */
        const val BOOKSHELF_READ_BOOK_ACTIVITY = "$BOOKSHELF/ReadBookActivity"

        /*****************************************书城分组**************************************************/

        /**
         * 书城fragment
         */
        const val BOOKMall_MAIN_FRAGMENT = "$BOOK_Mall/BookMallFragment"
    }
}