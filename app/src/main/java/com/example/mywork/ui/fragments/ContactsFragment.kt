package com.example.mywork.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mywork.R
import com.example.mywork.models.CommonModel
import com.example.mywork.ui.fragments.single_chat.SingleChatFragment
import com.example.mywork.utilits.*
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.contact_item.view.*
import kotlinx.android.synthetic.main.fragment_contacts.*


class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:FirebaseRecyclerAdapter<CommonModel,ContactsHolder>
    private lateinit var refContacts:DatabaseReference

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Workers"
        initRecycleView()
    }

    private fun initRecycleView() {
        recyclerView = contact_recycle_view
        refContacts = REF_DATABASE_ROOT.child(NODE_USERS)

        val options = FirebaseRecyclerOptions.Builder<CommonModel>()
            .setQuery(refContacts,CommonModel::class.java)
            .build()
        adapter = object :FirebaseRecyclerAdapter<CommonModel,ContactsHolder>(options){
            //работает когда adapter получает доступ к view
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false)
                return ContactsHolder(view)
            }

            // получает доступ и заполнякт модель
            override fun onBindViewHolder(
                holder: ContactsHolder,
                position: Int,
                model: CommonModel
            ) {
                if(model.fullname.isEmpty()){
                    holder.name.text = "no name"
                }else holder.name.text = model.fullname
                holder.status.text = model.state
                holder.itemView.setOnClickListener{ replaceFragment(SingleChatFragment(model)) }
            }
        }

        recyclerView.adapter = adapter
        adapter.startListening()
    }

    class ContactsHolder(view: View):RecyclerView.ViewHolder(view){
        val name:TextView = view.contact_fullname
        val status:TextView = view.contact_status
    }

    override fun onPause() {
        super.onPause()
        adapter.stopListening()
    }
}