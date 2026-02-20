package com.abdulkarim.notification

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.notification.databinding.FragmentNotificationBinding
import com.abdulkarim.ui.extfun.setUpVerticalRecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : BaseFragment<FragmentNotificationBinding>() {

    override fun viewBindingLayout() = FragmentNotificationBinding.inflate(layoutInflater)

    private lateinit var adapter: NotificationAdapter
    private val viewModel by viewModels<NotificationViewModel>()

    override fun initializeView(savedInstanceState: Bundle?) {

        viewModel.uiState bindTo :: handleUiState

        adapter = NotificationAdapter()
        requireActivity().setUpVerticalRecyclerView(binding.notificationListRv, adapter)
        adapterDataObserver()

    }

    private fun adapterDataObserver(){
        adapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                binding.notificationListRv.scrollToPosition(0)
            }
            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                binding.notificationListRv.scrollToPosition(0)
            }
            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                binding.notificationListRv.scrollToPosition(0)
            }
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                binding.notificationListRv.scrollToPosition(0)
            }
            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                binding.notificationListRv.scrollToPosition(0)
            }
            override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                binding.notificationListRv.scrollToPosition(0)
            }
        })
    }

    private fun handleUiState(state: NotificationUiState<*>){
        when(state) {
            is NotificationUiState.AllNotification -> {
                adapter.submitList(state.data)
            }
            is NotificationUiState.NotificationEmpty -> {

            }
        }
    }



}