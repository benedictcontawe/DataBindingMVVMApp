package com.example.jetpackcomponentsapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackcomponentsapp.view.CustomListeners
import com.example.jetpackcomponentsapp.model.CustomModel
import com.example.jetpackcomponentsapp.MainViewModel
import com.example.jetpackcomponentsapp.R
import com.example.jetpackcomponentsapp.databinding.MainBinder
import com.example.jetpackcomponentsapp.view.MainActivity
import com.example.jetpackcomponentsapp.view.adapter.CustomAdapter

class MainFragment : BaseFragment(), CustomListeners {

    companion object {
        fun newInstance() : MainFragment = MainFragment()
    }

    private val activity by lazy { (getActivity() as MainActivity) }
    private lateinit var binding: MainBinder
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter : CustomAdapter
    //private lateinit var itemDecorationHelper: BottomOffsetDecorationHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //viewModel = ViewModelProviders.of(activity).get(MainViewModel::class.java)
        viewModel = ViewModelProvider(activity).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setRecyclerView()
        setFloatingActionButton()
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkIfFragmentLoaded(this)
    }

    private fun setRecyclerView() {
        adapter = CustomAdapter(context!!, this)
        //itemDecorationHelper = BottomOffsetDecorationHelper(context!!,R.dimen.extra_scroll)

        binding.recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        //binding.recyclerView.removeItemDecoration(itemDecorationHelper)
        binding.recyclerView.adapter = adapter

        viewModel.getItems().observe(viewLifecycleOwner, object : Observer<List<CustomModel>> {
            override fun onChanged(list : List<CustomModel>) {
                Log.d("MainFragment","ID ${list.map { it.id }}, Name ${list.map { it.name }}")
                binding.recyclerView.removeAllViews()
                adapter.setItems(list)
                adapter.notifyDataSetChanged()
            }
        })
        //binding.recyclerView.scrollToPosition(0)
        //binding.recyclerView.addItemDecoration(itemDecorationHelper)
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun setFloatingActionButton() {
        binding.floatingActionButtonAdd.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View?) {
                onAdd()
            }
        })
        binding.floatingActionButtonDelete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View?) {
                viewModel.deleteAll()
                Toast.makeText(context,"deleteAll()",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onAdd() {
        activity.callAddFragment()
        //viewModel.setItems()
    }

    override fun onUpdate(item : CustomModel, position : Int) {
        viewModel.setUpdate(item)
        activity.callUpdateFragment()
    }

    override fun onDelete(item : CustomModel, position : Int) {
        viewModel.deleteItem(item)
    }
}