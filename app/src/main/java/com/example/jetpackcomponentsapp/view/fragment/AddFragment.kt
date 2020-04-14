package com.example.jetpackcomponentsapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomponentsapp.MainViewModel
import com.example.jetpackcomponentsapp.databinding.AddBinder
import com.example.jetpackcomponentsapp.model.CustomModel
import com.example.jetpackcomponentsapp.view.MainActivity


class AddFragment : BaseFragment() {

    companion object {
        fun newInstance() : AddFragment = AddFragment()

        fun getTag() : String {
            return "AddFragment"
        }
    }

    private val activity by lazy { (getActivity() as MainActivity) }
    private lateinit var binding : AddBinder
    private lateinit var viewModel : MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(getTag(),"onCreateView()")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(getTag(),"onViewCreated()")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(activity).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.editText.requestFocus()
        showSoftKeyboard()

        binding.button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View) {
                viewModel.insertItem(CustomModel(binding.editText.text.toString()))
                hideSoftKeyboard()
                activity.supportFragmentManager.popBackStack()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkIfFragmentLoaded(this)
    }

    private fun showSoftKeyboard() {
        val inputMethodManager: InputMethodManager = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun hideSoftKeyboard() {
        val inputMethodManager: InputMethodManager = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}