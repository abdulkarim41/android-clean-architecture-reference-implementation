package com.abdulkarim.notification

import com.abdulkarim.common.base.BaseViewModel
import com.abdulkarim.domain.notificationusecase.DeleteNotificationByIdUseCase
import com.abdulkarim.domain.notificationusecase.GetAllNotificationUseCase
import com.abdulkarim.entity.notification.NotificationEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getAllNotificationUseCase: GetAllNotificationUseCase,
    private val deleteNotificationByIdUseCase: DeleteNotificationByIdUseCase,
) : BaseViewModel(){

    val action: (NotificationUiAction) -> Unit = {
        when (it) {
            is NotificationUiAction.GetAllNotification -> getAllNotifications()
            is NotificationUiAction.DeleteSingleNotification -> deleteSingleNotification(it.params)
        }
    }

    private val _uiState = MutableStateFlow<NotificationUiState<Any>>(NotificationUiState.NotificationEmpty)
    val uiState get() = _uiState

    init { getAllNotifications() }

    private fun getAllNotifications(){
        execute {
            getAllNotificationUseCase.execute().collect {
                if (it.isEmpty()) {
                    _uiState.value = NotificationUiState.NotificationEmpty
                    return@collect
                }
                _uiState.value = NotificationUiState.AllNotification(it)
            }
        }
    }

    private fun deleteSingleNotification(params:DeleteNotificationByIdUseCase.Params) {
        execute {
            deleteNotificationByIdUseCase.execute(params)
        }
    }

}

sealed interface NotificationUiState<out ResultType> {
    data class AllNotification(val data : List<NotificationEntity>) : NotificationUiState<List<NotificationEntity>>
    data object NotificationEmpty : NotificationUiState<NotificationEmpty>
}

sealed interface NotificationUiAction {
    data object GetAllNotification : NotificationUiAction
    data class DeleteSingleNotification(val params: DeleteNotificationByIdUseCase.Params) : NotificationUiAction

}