package com.example.mywork.ui.fragments.single_chat

import android.view.View
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mywork.R
import com.example.mywork.models.CommonModel
import com.example.mywork.models.UserModel
import com.example.mywork.ui.fragments.BaseFragment
import com.example.mywork.utilits.*
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_single_chat.*
import kotlinx.android.synthetic.main.toolbar_info.view.*


class SingleChatFragment(private val model: CommonModel) :
    BaseFragment(R.layout.fragment_single_chat) {

    private lateinit var listenerInfoToolbar: AppValueEventListener
    private lateinit var receivingUser: UserModel
    private lateinit var toolbarInfo: View
    private lateinit var refUser: DatabaseReference
    private lateinit var refMessages: DatabaseReference
    private lateinit var adapter: SingleChatAdapter
    private lateinit var recycleView: RecyclerView
    private lateinit var messagesListener: AppChildEventListener
    private var countMessages = 14
    private var isScrolling = false
    private var smoothScrollToPosition = true
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var layoutManager: LinearLayoutManager

    override fun onResume() {
        super.onResume()
        initFields()
        initToolbar()
        initRecycleView()

    }

    private fun initFields() {
        swipeRefreshLayout = chat_swipe_refresh
        layoutManager = LinearLayoutManager(this.context)
    }

    private fun initRecycleView() {
        recycleView = chat_recycle_view
        adapter = SingleChatAdapter()
        refMessages = REF_DATABASE_ROOT.child(NODE_MESSAGES)
            .child(CURRENT_UID)
            .child(model.id)
        recycleView.adapter = adapter

        //стандартные ускорители
        recycleView.setHasFixedSize(true)
        recycleView.isNestedScrollingEnabled = false

        recycleView.layoutManager = layoutManager
        messagesListener = AppChildEventListener {
            val message = it.getUserModel()

            if (smoothScrollToPosition){
                adapter.addItemToBottom(message){
                    //опускае recycleView вниз на крайний элемент
                    recycleView.smoothScrollToPosition(adapter.itemCount)
                }
            } else {
                adapter.addItemToTop(message){
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        }

        //limitToLast - ограничить последними countMessages(12) сообщениями
        refMessages.limitToLast(countMessages).addChildEventListener(messagesListener)

        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            //принимает состояние
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //класс AbsListView показывает какое состояние
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // когда активируется скроллинг и листают на верх
                if (isScrolling && dy < 0 && layoutManager.findFirstVisibleItemPosition() <= 3) {
                    updateData()
                }
            }
        })
        swipeRefreshLayout.setOnRefreshListener { updateData() }
    }

    private fun updateData() {
        smoothScrollToPosition = false
        isScrolling = false
        countMessages += 14
        refMessages.removeEventListener(messagesListener)
        refMessages.limitToLast(countMessages).addChildEventListener(messagesListener)
    }

    private fun initToolbar() {
        toolbarInfo = APP_ACTIVITY.toolbar.toolbar_info
        APP_ACTIVITY.toolbar.toolbar_info.visibility = View.VISIBLE
        listenerInfoToolbar = AppValueEventListener {
            receivingUser = it.getUserModel()
            initInfoToolbar()
        }


        refUser = REF_DATABASE_ROOT.child(NODE_USERS).child(model.id)
        refUser.addValueEventListener(listenerInfoToolbar)
        //кнопка льправки сообщения
        chat_btn_send_massage.setOnClickListener {
            smoothScrollToPosition = true
            val message = chat_input_message.text.toString()
            if (message.isEmpty()) {
                showToast("Enter message")
            } else sendMessage(message, model.id, TYPE_TEXT) {
                chat_input_message.setText("")
            }
        }
    }


    private fun initInfoToolbar() {
        toolbarInfo.toolbar_chat_fullname.text = receivingUser.fullname
        toolbarInfo.toolbar_chat_status.text = receivingUser.state
    }

    override fun onPause() {
        super.onPause()
        APP_ACTIVITY.toolbar.toolbar_info.visibility = View.GONE
        //отключаем слушатель во избежании утечек памяти
        refUser.removeEventListener(listenerInfoToolbar)

        refMessages.removeEventListener(messagesListener)
    }
}